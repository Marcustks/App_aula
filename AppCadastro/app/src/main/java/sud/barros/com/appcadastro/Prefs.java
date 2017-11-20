package sud.barros.com.appcadastro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aluno on 20/11/2017.
 */

public class Prefs {

    public static final String ARQUIVO_PREFS = "ExPrefs";
    private static SharedPreferences pref;

    public static void  salvar(Context ctx, String chave, String valor){
        pref = ctx.getSharedPreferences(ARQUIVO_PREFS, ctx.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putString(chave, valor);
        edt.commit();
    }


    public static String obter(Context ctx, String chave){
        pref = ctx.getSharedPreferences(ARQUIVO_PREFS, ctx.MODE_PRIVATE);
        String valor = pref.getString(chave,"");
        return valor ;

    }

}
