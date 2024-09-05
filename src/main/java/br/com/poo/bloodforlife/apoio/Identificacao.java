package br.com.poo.bloodforlife.apoio;

import java.io.Serializable;

public class Identificacao implements Serializable {
    private int ultimoIDUsuario;

    public Identificacao() {
        this.ultimoIDUsuario = 0;
    }

    public int gerarIDUsuario(){
        this.ultimoIDUsuario += 1;
        return this.ultimoIDUsuario;
    }
}
