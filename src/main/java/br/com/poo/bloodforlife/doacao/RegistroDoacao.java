package br.com.poo.bloodforlife.doacao;

public class RegistroDoacao {
    private String data;
    private TipoSangue tipoSangue;
    private Doador doador;

    public RegistroDoacao(String data, TipoSangue tipoSangue, Doador doador) {
        this.data = data;
        this.tipoSangue = tipoSangue;
        this.doador = doador;
    }
}
