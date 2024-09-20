package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaPrincipalAdmin {

    public static final String FXML_PATH = "tela-principal-admin.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    protected void initialize(){
        boasVindas.setText("Usuario Logado: \n" + BloodForLive.getUsuarioLogado().getNome());
    }

    @FXML
    protected void cadastrarUsuario() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }

    @FXML
    protected void cadastrarDoador() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroDoador.FXML_PATH);
    }

    @FXML
    protected void registrarDoacao() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaRegistroDoacao.FXML_PATH);
    }

    @FXML
    protected void listarDoador() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaListaDoador.FXML_PATH);
    }

    @FXML
    protected void verificarDoador() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaVerificarDoador.FXML_PATH);
    }

    //@FXML
   // protected void gerirUsuarios() throws IOException {
    //    ControladorDeCena.trocarCena(ControladorTelaListaUsuarios.FXML_PATH);
   // }

    @FXML
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}

