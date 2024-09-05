package br.com.poo.bloodforlife.doacao;

public class TipoSangue {
    private String tipo;
    private double quantidade;

    public TipoSangue(String tipo, double quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }
}
