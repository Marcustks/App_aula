package sud.barros.com.appcadastro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etSenha = (EditText)findViewById(R.id.etSenha);
        btLogar = (Button)findViewById(R.id.btLogar);
        mAuth = FirebaseAuth.getInstance();
        btLogar.setOnClickListener(this);

        //etEmail.
        String email = Prefs.obter(getApplicationContext(),"email");
        etEmail.setText(email);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btLogar:

                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                Prefs.salvar(getApplicationContext(),"email",email);

                mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"erro",Toast.LENGTH_LONG).show();

                                }
                                if (task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(),"Logado com sucesso!",Toast.LENGTH_LONG).show();
                                    Intent telahome = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(telahome);
                                    finish();

                                }

                                // ...
                            }
                        });
                break;
        }
    }
}
