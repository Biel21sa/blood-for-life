package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.usuarios.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaCadastroAdmin {
    public static final String FXML_PATH = "tela-cadastro-admin.fxml";

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoUsuario;

    @FXML
    private TextField campoSenha;

    @FXML
    public void cadastrarAdministrador() throws IOException {
        Administrador administrador = new Administrador(campoNome.toString(), campoUsuario.toString(), campoSenha.toString());
        Controlador controlador = new Controlador("Hemocentro");
        controlador.cadastrarAdministrador(administrador);
    }

    @FXML
    public void voltar() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastro.FXML_PATH);
    }
}
