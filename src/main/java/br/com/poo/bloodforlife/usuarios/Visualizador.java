package br.com.poo.bloodforlife.usuarios;

import java.io.Serializable;

public class Visualizador extends Usuario implements Serializable {

    public Visualizador(String nome, String usuario, String senha) {
        super(nome, usuario, senha, "Visualizador");
    }
}
