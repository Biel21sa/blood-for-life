package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.doacao.TipoSangue;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaCadastroDoador {
    public static final String FXML_PATH = "tela-cadastro-doador.fxml";

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoIdade;

    @FXML
    private ComboBox<String> campoTipo;

    @FXML
    private void initialize() {
        campoTipo.getItems().addAll(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        );
    }


    @FXML
    public void cadastrarDoador() throws IOException {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String email = campoEmail.getText();
        String idadeTexto = campoIdade.getText();
        String tipoSanguineo = campoTipo.getValue();
        Integer idade = null;

        if (nome == null || nome.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do nome está vazio!");
            return;
        }

        if (cpf == null || cpf.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do CPF está vazio!");
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do email está vazio!");
            return;
        }

        if (idadeTexto == null || idadeTexto.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo da idade está vazio!");
            return;
        }

        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException e) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Idade deve ser um número inteiro válido!");
            return;
        }

        if (tipoSanguineo == null) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do tipo sanguíneo está vazio!");
            return;
        }

        TipoSangue tipoSangue = new TipoSangue(tipoSanguineo);
        Doador doador = new Doador(nome, cpf, email, idade, tipoSangue);
        BloodForLive.getBank().cadastrarDoador(doador);

        ControladorAlerta.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro do doador realizado com sucesso!");

        ControladorDeCena.trocarCena(ControladorTelaListaDoador.FXML_PATH);
    }

    @FXML
    public void voltar() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
