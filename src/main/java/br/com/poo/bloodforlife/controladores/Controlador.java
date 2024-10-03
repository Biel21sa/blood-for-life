package br.com.poo.bloodforlife.controladores;

import br.com.poo.bloodforlife.bancodesangue.BancoSangue;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.doacao.RegistroDoacao;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoDoacao;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoDoador;
import br.com.poo.bloodforlife.usuarios.Administrador;
import br.com.poo.bloodforlife.usuarios.Clinica;
import br.com.poo.bloodforlife.usuarios.Usuario;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoUsuarios;
import br.com.poo.bloodforlife.usuarios.Visualizador;

import java.util.ArrayList;

public class Controlador {
    private String nome;
    private ArrayList<RegistroDoacao> doacoes;
    private ArrayList<Doador> doadores;
    private ArrayList<Usuario> usuarios;

    private ControladorArquivoUsuarios controladorArquivoUsuario = new ControladorArquivoUsuarios();
    private ControladorArquivoDoador controladorArquivoDoador = new ControladorArquivoDoador();
    private ControladorArquivoDoacao controladorArquivoDoacao = new ControladorArquivoDoacao();

    public Controlador(String nome) {
        this.nome = nome;
        this.doacoes = new ArrayList<>();
        this.doadores = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.criarUsuarioAdminPadrao();
    }

    public Usuario login(String usuario, String senha){
        this.usuarios = controladorArquivoUsuario.lerArquivoUsuarios();
        Usuario usuarioLogado = null;
        for (Usuario usuarioSalvo : this.usuarios) {
            if(usuarioSalvo != null && usuarioSalvo.getUsuario().equals(usuario) && usuarioSalvo.getSenha().equals(senha)) {
                    usuarioLogado = usuarioSalvo;
                }
        }
        return usuarioLogado;
    }

    public void criarUsuarioAdminPadrao(){
        //Cria o admin padrão se ele não existir
        if(!this.usuarioExiste("admin")){
            Administrador administradorPadrao = new Administrador("Administrador Padrão", "admin", "admin");
            this.usuarios.add(administradorPadrao);
            controladorArquivoUsuario.cadastrarUsuarioNoArquivo(administradorPadrao);
            this.usuarios = controladorArquivoUsuario.lerArquivoUsuarios();
        }
    }

    public boolean usuarioExiste(String usuario){
        boolean jaExiste = false;
        this.usuarios = controladorArquivoUsuario.lerArquivoUsuarios();
        for (Usuario usuarioLido : this.usuarios){
            if(usuarioLido.getUsuario().equals(usuario)){
                jaExiste = true;
                break;
            }
        }
        return jaExiste;
    }

    public void cadastrarAdministrador(Administrador administrador){
        this.getUsuarios().add(administrador);
        controladorArquivoUsuario.cadastrarUsuarioNoArquivo(administrador);
    }

    public void cadastrarVisualizador(Visualizador visualizador){
        this.getUsuarios().add(visualizador);
        controladorArquivoUsuario.cadastrarUsuarioNoArquivo(visualizador);
    }

    public void cadastrarClinica(Clinica clinica){
        this.getUsuarios().add(clinica);
        controladorArquivoUsuario.cadastrarUsuarioNoArquivo(clinica);
    }

    public void cadastrarDoador(Doador doador){
        this.getDoadores().add(doador);
        controladorArquivoDoador.cadastrarDoadorNoArquivo(doador);
    }

    public void excluirDoador(String cpf){
        this.doadores = this.getDoadores();
        this.doadores.removeIf(d -> d.getCpf().equals(cpf));
        controladorArquivoDoador.excluirDoadorNoArquivo(cpf);
    }

    public void atualizarDoador(String cpf, String novoStatus){
        this.doadores = this.getDoadores();
        for (Doador d : doadores) {
            if (d.getCpf().equals(cpf)) {
                d.setStatus(novoStatus);
                break;
            }
        }
        controladorArquivoDoador.atualizarStatusDoador(cpf, novoStatus);
    }

    public void registrarDoacoes(RegistroDoacao doacao){
        this.getDoacoes().add(doacao);
        controladorArquivoDoacao.cadastrarDoacaoNoArquivo(doacao);
    }

    public Doador buscarDoaodor(String cpf){
        Doador doador = null;
        this.doadores = this.getDoadores();
        for (Doador doador1 : this.doadores){
            if (doador1.getCpf().equals(cpf)){
                doador = doador1;
            }
        }
        return doador;
    }

    public ArrayList<Usuario> getUsuarios(){
        this.usuarios = controladorArquivoUsuario.lerArquivoUsuarios();
        return this.usuarios;
    }

    public ArrayList<Doador> getDoadores(){
        this.doadores = controladorArquivoDoador.lerArquivoDoadores();
        return this.doadores;
    }

    public ArrayList<RegistroDoacao> getDoacoes(){
        this.doacoes = controladorArquivoDoacao.lerArquivoDoacoes();
        return this.doacoes;
    }

    public String getNome() {
        return nome;
    }
}
