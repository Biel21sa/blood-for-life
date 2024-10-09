package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoBancoSangue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControladorTelaPrincipalClinica {

    public static final String FXML_PATH = "tela-principal-clinica.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    private TextField campoCpf;

    @FXML
    private Text nomeDoador;

    @FXML
    private Text cpfDoador;

    @FXML
    private Text statusDoador;

    @FXML
    private TextField descontoCpf;

    @FXML
    protected void initialize(){
        // Exibe o nome do usuário logado
        boasVindas.setText("Usuário Logado: \n" + BloodForLive.getUsuarioLogado().getNome());
    }

    @FXML
    public void verificar() throws IOException {
        String cpf = campoCpf.getText();

        if (cpf == null || cpf.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do CPF está vazio!");
            return;
        }

        Doador doador = BloodForLive.getBank().buscarDoaodor(cpf);
        nomeDoador.setText(doador.getNome());
        cpfDoador.setText(doador.getCpf());
        statusDoador.setText(doador.getStatus());
    }

    @FXML
    public void utilizar() throws IOException {
        String cpf = descontoCpf.getText();

        if (cpf == null || cpf.trim().isEmpty()) {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Campo do CPF está vazio!");
            return;
        }
        Doador doador = BloodForLive.getBank().buscarDoaodor(cpf);

        if (Objects.equals(doador.getStatus(), "ativo")){
            BloodForLive.getBank().atualizarDoador(cpf, "inativo");

            ControladorAlerta.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Utilizado o desconto com sucesso!");
        } else {
            ControladorAlerta.showAlert(Alert.AlertType.ERROR, "Erro", "Doaodor não tem desconto no sistema!");
        }


    }

    @FXML
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}

