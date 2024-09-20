package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.doacao.RegistroDoacao;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class ControladorTelaRegistroDoacao {

    public static final String FXML_PATH = "tela-registro-doacao.fxml";

    @FXML
    private TextField campoCpf;

    @FXML
    private DatePicker campoData;

    @FXML
    private ComboBox<String> campoTipo;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private void initialize() {
        campoTipo.getItems().addAll(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        );
    }

    @FXML
    public void registrarDoacao() throws IOException {
        String cpf = campoCpf.getText();
        String tipoSanguineo = campoTipo.getValue();
        LocalDate data = campoData.getValue();
        String quantidadeTexto = campoQuantidade.getText();
        Integer quantidade = null;

        if (cpf == null || cpf.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do CPF está vazio!");
            return;
        }

        if (data == null) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo da data está vazio!");
            return;
        }

        if (tipoSanguineo == null || tipoSanguineo.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do tipo sanguíneo está vazio!");
            return;
        }

        if (quantidadeTexto == null || quantidadeTexto.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo da quantidade está vazio!");
            return;
        }

        try {
            quantidade = Integer.parseInt(quantidadeTexto);
        } catch (NumberFormatException e) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Quantidade deve ser um número inteiro válido!");
            return;
        }

        Doador doador = BloodForLive.getBank().buscarDoaodor(cpf);
        RegistroDoacao registroDoacao = new RegistroDoacao(data, tipoSanguineo, quantidade, doador);
        doador.setStatus("ativo");
        BloodForLive.getBank().registrarDoacoes(registroDoacao);
        BloodForLive.getBank().cadastrarDoador(doador);

        ControladorAlerta.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Registro de doação realizado com sucesso!");
    }



    @FXML
    public void voltar() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
