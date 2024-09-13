package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.usuarios.Clinica;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        String nome = campoNome.getText();
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();
        String cnpj = campoCnpj.getText();

        if (nome == null || nome.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do nome está vazio!");
            return;
        }

        if (usuario == null || usuario.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do usuário está vazio!");
            return;
        }

        if (senha == null || senha.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo da senha está vazio!");
            return;
        }

        if (cnpj == null || cnpj.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do CNPJ está vazio!");
            return;
        }

        Clinica clinica = new Clinica(nome, usuario, senha, cnpj);
        Controlador controlador = new Controlador("Hemocentro");
        controlador.cadastrarClinica(clinica);

        ControladorAlerta.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro da clínica realizado com sucesso!");
    }

    @FXML
    public void voltar() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }
}
