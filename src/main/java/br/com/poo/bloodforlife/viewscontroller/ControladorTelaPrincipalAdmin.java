package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoBancoSangue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaPrincipalAdmin {

    public static final String FXML_PATH = "tela-principal-admin.fxml";

    @FXML
    private Text boasVindas;


    @FXML
    protected void initialize(){
        boasVindas.setText(BloodForLive.getUsuarioLogado().getNome());
    }

    @FXML
    protected void listarDoador() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaListaDoador.FXML_PATH);
    }

    @FXML
    protected void listarUsuario() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaListaUsuario.FXML_PATH);
    }

    @FXML
    protected void listarDoacao() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaListaDoacao.FXML_PATH);
    }

    @FXML
    protected void graficoMensal() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaGraficoMensal.FXML_PATH);
    }

    @FXML
    protected void graficoEstoque() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaGraficoEstoque.FXML_PATH);
    }

    @FXML
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}
