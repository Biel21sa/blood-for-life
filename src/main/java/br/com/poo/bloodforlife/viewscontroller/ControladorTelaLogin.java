package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaLogin {

    public static final String FXML_PATH = "tela-login.fxml";

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    protected void onLoginClick() throws IOException {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        BloodForLive.setUsuarioLogado(BloodForLive.getBank().login(usuario,senha));

        if (BloodForLive.getUsuarioLogado() != null){
            switch (BloodForLive.getUsuarioLogado().getTipo()){
                case "Administrador":
                    ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
                    break;
                case "Cliente":
                    ControladorDeCena.trocarCena(ControladorTelaPrincipalCliente.FXML_PATH);
                    break;
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao realizar login");
            alert.setContentText("Usuário e/ou senha inválidos");
            alert.show();
        }
    }
}
