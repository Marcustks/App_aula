package model;

/**
 * Created by Aluno on 09/10/2017.
 */

public class Veiculo {
    private String desricao;
    private String placa;
    private String ano;
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Veiculo() {

    }

    public Veiculo(String desricao, String placa, String ano) {
        this.desricao = desricao;
        this.placa = placa;
        this.ano = ano;
    }


    public String getDesricao() {
        return desricao;
    }

    public void setDesricao(String desricao) {
        this.desricao = desricao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return desricao;
    }
}
