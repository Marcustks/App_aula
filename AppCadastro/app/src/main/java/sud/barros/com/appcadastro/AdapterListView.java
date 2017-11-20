package sud.barros.com.appcadastro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Veiculo;

/**
 * Created by Aluno on 23/10/2017.
 */

public class AdapterListView extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ArrayList<Veiculo> itens;

    public AdapterListView(Context context, ArrayList<Veiculo> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount()
    {
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Veiculo getItem(int position)
    {
        return itens.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        //Pega o item de acordo com a posção.
        Veiculo item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_lista, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.descricao)).setText(item.getDesricao());
        ((TextView) view.findViewById(R.id.placa)).setText(item.getPlaca());
        ((TextView) view.findViewById(R.id.ano)).setText(item.getAno());

       // ((ImageView) view.findViewById(R.id.fotoVeiculo)).setImageResource(item.getIconeRid());

        return view;
    }
}