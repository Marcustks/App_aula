package model;

/**
 * Created by Aluno on 09/10/2017.
 */

public class Veiculo {
    private int codigo;
    private String codigoKey;
    private String desricao;
    private String modelo;
    private String placa;
    private String marca;
    private String ano;
    private String url;


    public String getCodigoKey() {
        return codigoKey;
    }

    public void setCodigoKey(String codigoKey) {
        this.codigoKey = codigoKey;
    }

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

    public Veiculo(String desricao, String modelo, String placa, String marca, String ano, String url) {
        this.desricao = desricao;
        this.modelo = modelo;
        this.placa = placa;
        this.marca = marca;
        this.ano = ano;
        this.url = url;
    }

    public String getDesricao() {
        return desricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
