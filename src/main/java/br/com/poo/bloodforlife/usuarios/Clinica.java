package br.com.poo.bloodforlife.usuarios;

public class Clinica extends Usuario{
    private String endereco;
    private String cnpj;

    public Clinica(String nome, String usuario, String tipo, String senha, String endereco, String cnpj) {
        super(nome, usuario, tipo, senha);
        this.endereco = endereco;
        this.cnpj = cnpj;
    }
}
