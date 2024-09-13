package br.com.poo.bloodforlife.doacao;

public class Doador {
    private String nome;
    private String cpf;
    private String email;
    private int idade;
    private TipoSangue tipoSangue;
    private String status;

    public Doador(String nome, String cpf, String email, int idade, TipoSangue tipoSangue) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
        this.tipoSangue = tipoSangue;
        this.status = "inativo";
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
