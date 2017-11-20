package sud.barros.com.appcadastro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;

import model.Veiculo;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView fotosalvar;

    private StorageReference storageReference;

    private EditText eddescricao;
    private EditText edplaca;
    private EditText edano;
    private Button btsalvarcadastro;
    private String id;
    private Uri filePath;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final int PICK_IMAGE_REQUEST = 234;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();
        database =  FirebaseDatabase.getInstance();
        myRef = database.getReference("veiculos");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                   // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    id = user.getUid();
                } else {
                    // User is signed out
                   // Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        fotosalvar = (ImageView) findViewById(R.id.fotosalvar);

        eddescricao = (EditText) findViewById(R.id.eddescricao);
        edplaca = (EditText) findViewById(R.id.edplaca);
        edano = (EditText) findViewById(R.id.edano);
        btsalvarcadastro = (Button) findViewById(R.id.btsalvarcadastro);


        btsalvarcadastro.setOnClickListener(this);
        fotosalvar.setOnClickListener(this);

    }

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btsalvarcadastro:
                // Toast.makeText(getApplicationContext(),"Logado com sucesso!",Toast.LENGTH_LONG).show();

                String descricao=eddescricao.getText().toString().trim();
                String placa=edplaca.getText().toString().trim();
                String ano=edano.getText().toString().trim();

                Veiculo veiculo = new Veiculo(descricao,placa,ano);
                veiculo.setUrl("");


                myRef.push().setValue(veiculo, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                                final String key= databaseReference.getKey();

                                storageReference.child("veiculos").child(key).child("images/foto.jpg").putFile(filePath)
                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                // Get a URL to the uploaded content
                                                Uri url = taskSnapshot.getDownloadUrl();

                                               myRef = database.getReference("veiculos");

                                                myRef.child(key).child("url").setValue(url.toString());


                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception exception) {
                                                // Handle unsuccessful uploads
                                                // ...
                                            }
                                        });


                                Toast.makeText(getApplicationContext(),"salvo com sucesso!",Toast.LENGTH_LONG).show();
                            }
                        });





                break;
            case R.id.fotosalvar:
                showFileChooser();


                break;


        }
    }@Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

               /* int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                float scaleWidth = ((float) 176) / width;
                float scaleHeight = ((float) 144) / height;
                // create a matrix for the manipulation
                Matrix matrix = new Matrix();
                // resize the bit map
                matrix.postScale(scaleWidth, scaleHeight);
                // recreate the new Bitmap
                Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);*/

                fotosalvar.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
