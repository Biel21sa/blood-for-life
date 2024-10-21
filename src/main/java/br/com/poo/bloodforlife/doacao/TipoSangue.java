package br.com.poo.bloodforlife.doacao;

import java.io.Serializable;

public class TipoSangue implements Serializable {
    private String tipo;
    public TipoSangue(String tipo) {
        this.tipo = tipo;

    }
    public String getTipo() {
        return tipo;
    }
}
