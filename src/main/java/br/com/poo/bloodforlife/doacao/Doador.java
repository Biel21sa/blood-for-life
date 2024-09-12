package br.com.poo.bloodforlife.doacao;

public class Doador {
    private String nome;
    private String cpf;
    private String email;
    private int idade;
    private TipoSangue tipoSangue;

    public Doador(String nome, String cpf, String email, int idade, TipoSangue tipoSangue) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
        this.tipoSangue = tipoSangue;
    }


    public String getCpf() {
        return cpf;
    }
}
