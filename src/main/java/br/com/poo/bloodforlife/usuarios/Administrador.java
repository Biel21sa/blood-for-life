package br.com.poo.bloodforlife.usuarios;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable {

    public Administrador(String nome, String usuario, String senha) {
        super(nome, usuario, senha, "Administrador");
    }
}
