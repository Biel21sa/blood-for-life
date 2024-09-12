package br.com.poo.bloodforlife.viewscontroller;


import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControladorTelaCadastroUsuario {
    public static final String FXML_PATH = "tela-cadastro-usuario.fxml";

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
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
