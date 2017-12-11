package sud.barros.com.appcadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import controle.ControleVeiculo;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btcadastrarveiculo;
    private Button btlistar;
    public static ControleVeiculo controleVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btcadastrarveiculo = (Button)findViewById(R.id.btcadastrarveiculo);
        btlistar = (Button)findViewById(R.id.btlistar);

        btcadastrarveiculo.setOnClickListener(this);
        btlistar.setOnClickListener(this);

        controleVeiculo = new ControleVeiculo(getApplicationContext());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btcadastrarveiculo:
               // Toast.makeText(getApplicationContext(),"Logado com sucesso!",Toast.LENGTH_LONG).show();
                Intent cadastro = new Intent(getApplicationContext(),CadastroActivity.class);
                startActivity(cadastro);
                break;
            case R.id.btlistar:
                Intent listar = new Intent(getApplicationContext(),ListarActivity.class);
                startActivity(listar);
                break;

        }
    }
}
