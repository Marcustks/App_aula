package sud.barros.com.appcadastro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.Veiculo;

/**
 * Created by Aluno on 13/11/2017.
 */

public class VeiculosAdapter extends BaseAdapter {

    private List<Veiculo> veiculos;
    private Context context;


    public VeiculosAdapter(Context c, List<Veiculo> veiculos)
    {
        this.context=c;
        this.veiculos=veiculos;
    }


    @Override
    public int getCount() {
        return veiculos.size();
    }

    @Override
    public Object getItem(int position) {

        return veiculos.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void updateListaVeiculos(List<Veiculo> veiculos)
    {
        this.veiculos=veiculos;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);

        }
        // get current item to be displayed
        Veiculo currentItem = (Veiculo) getItem(position);

        // get the TextView for item name and item description
        TextView descricao = (TextView)
                convertView.findViewById(R.id.descricao);

        TextView placa = (TextView)
                convertView.findViewById(R.id.placa);


        TextView ano = (TextView)
                convertView.findViewById(R.id.ano);

        ImageView fotoVeiculo = (ImageView)
                convertView.findViewById(R.id.fotoVeiculo);


        //sets the text for item name and item description from the current item object
        descricao.setText(currentItem.getDesricao());
        placa.setText(currentItem.getPlaca());
        ano.setText(currentItem.getAno());
        Picasso.with(context)
                .load(currentItem.getUrl())
                .resize(176, 144)
                .centerCrop()
                .into(fotoVeiculo);

        // returns the view for the current row
        return convertView;
    }
}
