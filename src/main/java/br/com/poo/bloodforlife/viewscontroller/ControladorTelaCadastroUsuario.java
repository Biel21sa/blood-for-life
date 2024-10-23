package br.com.poo.bloodforlife.viewscontroller;


import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaCadastroUsuario {
    public static final String FXML_PATH = "tela-cadastro-usuario.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    public void initialize() throws IOException {
        boasVindas.setText(BloodForLive.getUsuarioLogado().getNome());
    }

    @FXML
    public void cadastrarClinica() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaCadastroClinica.FXML_PATH);
    }

    @FXML
    public void cadastrarVisualizador() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaCadastroVisualizador.FXML_PATH);
    }

    @FXML
    public void cadastrarAdministrador() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaCadastroAdmin.FXML_PATH);
    }

    @FXML
    public void voltar()  throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaListaUsuario.FXML_PATH);
    }
}
