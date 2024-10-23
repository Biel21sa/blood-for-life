package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoBancoSangue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaPrincipalVisualizador {

    public static final String FXML_PATH = "tela-principal-visualizador.fxml";

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
    protected void initialize(){
        // Exibe o nome do usuário logado
        boasVindas.setText(BloodForLive.getUsuarioLogado().getNome());
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
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}
