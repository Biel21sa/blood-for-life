package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaVerificarDoador {

    public static final String FXML_PATH = "tela-verificar-doador.fxml";

    @FXML
    private TextField campoCpf;

    @FXML
    private Text nomeDoador;

    @FXML
    private Text cpfDoador;

    @FXML
    private Text statusDoaodor;

    @FXML
    public void verificarDoador() throws IOException {
        String cpf = campoCpf.getText();

        if (cpf == null || cpf.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do CPF está vazio!");
            return;
        }

        Controlador controlador = new Controlador("Hemocentro");
        Doador doador = controlador.buscarDoaodor(cpf);
        nomeDoador.setText(doador.getNome());
        cpfDoador.setText(doador.getCpf());
        statusDoaodor.setText(doador.getStatus());
    }

    @FXML
    public void voltar() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
