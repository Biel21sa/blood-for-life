package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.usuarios.Visualizador;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaCadastroVisualizador {
    public static final String FXML_PATH = "tela-cadastro-visualizador.fxml";

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoUsuario;

    @FXML
    private TextField campoSenha;


    @FXML
    public void cadastrarVisualizador() throws IOException {
        Visualizador visualizador = new Visualizador(campoNome.toString(), campoUsuario.toString(), campoSenha.toString());
        Controlador controlador = new Controlador("Hemocentro");
        controlador.cadastrarVisualizador(visualizador);
    }

    @FXML
    public void voltar() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }
}
