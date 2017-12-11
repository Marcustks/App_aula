package sud.barros.com.appcadastro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

public class ListarActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    private ListView lvlistar;
    private List<Veiculo> veiculosFirebase;
    private List<Veiculo> veiculosSqlite;
    private VeiculosAdapter adapter;

    public void preencher()
    {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Veiculo p = dataSnapshot.getValue(Veiculo.class);
                p.setCodigoKey(dataSnapshot.getKey());
                HomeActivity.controleVeiculo.adicionar(p);

                veiculosFirebase.add(p);
                adapter.updateListaVeiculos(veiculosFirebase);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        database =  FirebaseDatabase.getInstance();
        myRef = database.getReference("veiculos");
        veiculosFirebase = new ArrayList<>();

        adapter = new VeiculosAdapter(this, veiculosFirebase);
        lvlistar = (ListView) findViewById(R.id.lvlistar);
        lvlistar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicado",Toast.LENGTH_LONG).show();

            }
        });
       // final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        lvlistar.setAdapter(adapter);

        preencher();

        if(veiculosFirebase.size()==0)
        {

            veiculosSqlite = HomeActivity.controleVeiculo.listar();

            Toast.makeText(getApplicationContext(),"DB ="+veiculosSqlite.size(),Toast.LENGTH_SHORT).show();

            adapter.updateListaVeiculos(veiculosSqlite);
            adapter.notifyDataSetChanged();
        }
    }


}