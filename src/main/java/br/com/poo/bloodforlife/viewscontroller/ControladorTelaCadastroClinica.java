package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.usuarios.Clinica;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaCadastroClinica {
    public static final String FXML_PATH = "tela-cadastro-clinica.fxml";

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoUsuario;

    @FXML
    private TextField campoSenha;

    @FXML
    private TextField campoCnpj;

    @FXML
    public void cadastrarClinica() throws IOException{
        Clinica clinica = new Clinica(campoNome.toString(), campoUsuario.toString(), campoSenha.toString(), campoCnpj.toString());
        Controlador controlador = new Controlador("Hemocentro");
        controlador.cadastrarClinica(clinica);
    }

    @FXML
    public void voltar() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }
}
