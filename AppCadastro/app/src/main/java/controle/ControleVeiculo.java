package controle;

/**
 * Created by Aluno on 04/12/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

/**
 * Created by carlosbarros on 27/11/2017.
 */

public class ControleVeiculo {

    VeiculoSqlite database;

    public ControleVeiculo(Context ctx)
    {
        database = new VeiculoSqlite(ctx);
    }

    public Veiculo buscar(String codigoKey)
    {
       // Cursor  cursor = database.getConexao().rawQuery("select * from "
         //     +VeiculoSqlite.TABLE_NAME+ " where codigokey like '"+codigoKey.trim()+"'",null);

        Cursor cursor = database.getConexao().rawQuery("SELECT * FROM " + VeiculoSqlite.TABLE_NAME + " WHERE " + VeiculoSqlite.codigoKey + "=?", new String[] { codigoKey + "" });
        if(cursor.getCount()==0){
            return null;
        }else {
            cursor.moveToFirst();
            Veiculo v = new Veiculo();
            v.setCodigo(cursor.getInt(cursor.getColumnIndex(VeiculoSqlite.codigo)));
            v.setCodigoKey(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.codigoKey)));
            v.setPlaca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.placa)));
            v.setModelo(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.modelo)));
            v.setMarca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.marca)));
            return v;
       }
    }
    public long adicionar(Veiculo v)
    {
        Veiculo aux = buscar(v.getCodigoKey());

        if(aux==null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(VeiculoSqlite.placa, v.getPlaca());
            contentValues.put(VeiculoSqlite.modelo, v.getModelo());
            contentValues.put(VeiculoSqlite.marca, v.getMarca());
            contentValues.put(VeiculoSqlite.codigoKey, v.getCodigoKey());
            long codigo = database.getConexao().insert(VeiculoSqlite.TABLE_NAME, null, contentValues);

            return codigo;
        }
        return -1;
    }
    public void editar(Veiculo v)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(VeiculoSqlite.placa,v.getPlaca());
        contentValues.put(VeiculoSqlite.modelo,v.getModelo());
        contentValues.put(VeiculoSqlite.marca,v.getMarca());

        long codigo = database.getConexao().update(VeiculoSqlite.TABLE_NAME,
                contentValues,"codigo=?",new String[]{Integer.toString(v.getCodigo())});
    }
    public Integer remover(int codigo)
    {
        return database.getConexao().delete(VeiculoSqlite.TABLE_NAME,"codigo=?",
                new String[]{Integer.toString(codigo)});
    }
    public List<Veiculo> listar()
    {
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select codigo, ");
        stringBuilder.append("codigoKey, ");
        stringBuilder.append("marca, ");
        stringBuilder.append("modelo, ");
        stringBuilder.append("placa ");
        stringBuilder.append(" from "+VeiculoSqlite.TABLE_NAME+ "");
        stringBuilder.append(" order by marca ");

        Cursor cursor = database.getConexao().rawQuery(stringBuilder.toString(),null);
        cursor.moveToFirst();
        Veiculo v;
        while (!cursor.isAfterLast())
        {
            v = new Veiculo();
            v.setCodigo(cursor.getInt(cursor.getColumnIndex(VeiculoSqlite.codigo)));
            v.setPlaca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.placa)));
            v.setModelo(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.modelo)));
            v.setMarca(cursor.getString(cursor.getColumnIndex(VeiculoSqlite.marca)));
            veiculos.add(v);
            cursor.moveToNext();
        }
        return veiculos;

    }

}
