package br.com.poo.bloodforlife.controladores;

import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.doacao.RegistroDoacao;
import br.com.poo.bloodforlife.doacao.TipoSangue;
import br.com.poo.bloodforlife.usuarios.Usuario;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoUsuarios;

import java.util.ArrayList;

public class Controlador {
    private String nome;
    private ArrayList<RegistroDoacao> registroDoacoes;
    private ArrayList<Doador> doadores;
    private ArrayList<TipoSangue> tipoSangues;
    private ArrayList<Usuario> usuarios;

    private ControladorArquivoUsuarios controladorArquivoUsuario = new ControladorArquivoUsuarios();
   // private ControladorArquivoClientes controladorArquivoClientes = new ControladorArquivoClientes();
   // private ControladorArquivoContas controladorArquivoContas = new ControladorArquivoContas();

    public Controlador(String nome) {
        this.nome = nome;
        this.registroDoacoes = new ArrayList<>();
        this.doadores = new ArrayList<>();
        this.tipoSangues = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public Usuario login(String usuario, String senha){
        this.usuarios = controladorArquivoUsuario.lerArquivoUsuarios();
        Usuario usuarioLogado = null;
        for (Usuario usuarioSalvo : this.usuarios) {
            if(usuarioSalvo != null) {
                if (usuarioSalvo.getUsuario().equals(usuario) && usuarioSalvo.getSenha().equals(senha)) {
                    usuarioLogado = usuarioSalvo;
                }
            }
        }
        return usuarioLogado;
    }

    public String getNome() {
        return nome;
    }
}
