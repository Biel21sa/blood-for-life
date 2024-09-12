package br.com.poo.bloodforlife.usuarios;

import java.io.Serializable;

public class Clinica extends Usuario implements Serializable {
    private String cnpj;

    public Clinica(String nome, String usuario, String senha, String cnpj) {
        super(nome, usuario, senha, "Clinica");
        this.cnpj = cnpj;
    }
}
