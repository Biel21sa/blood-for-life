package br.com.poo.bloodforlife.doacao;

public class Doador {
    private String nome;
    private String cpf;
    private String email;
    private int idade;

    public Doador(String nome, String cof, String email, int idade) {
        this.nome = nome;
        this.cpf = cof;
        this.email = email;
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }
}
