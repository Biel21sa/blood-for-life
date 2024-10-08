package br.com.poo.bloodforlife.usuarios;

import br.com.poo.bloodforlife.apoio.Identificacao;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoIdentificacao;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String nome;
    protected String usuario;
    protected String tipo;
    protected String senha;
    protected int id;

    public Usuario(String nome, String usuario, String senha, String tipo) {
        this.tipo = tipo;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        Identificacao identificacao = ControladorArquivoIdentificacao.lerArquivoIdentificacao();
        this.id = identificacao.gerarIDUsuario();
        ControladorArquivoIdentificacao.salvarArquivoIdentificacao(identificacao);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

}
