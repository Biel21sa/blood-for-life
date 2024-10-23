package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.usuarios.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaCadastroAdmin {
    public static final String FXML_PATH = "tela-cadastro-admin.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoUsuario;

    @FXML
    private TextField campoSenha;

    @FXML
    public void initialize() throws IOException {
        boasVindas.setText(BloodForLive.getUsuarioLogado().getNome());
    }

    @FXML
    public void cadastrarAdministrador() throws IOException {
        String nome = campoNome.getText();
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

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

        Administrador administrador = new Administrador(nome, usuario, senha);
        BloodForLive.getBank().cadastrarAdministrador(administrador);

        ControladorAlerta.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro do admin realizado com sucesso!");
    }

    @FXML
    public void voltar() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }
}
