package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoBancoSangue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaPrincipalAdmin {

    public static final String FXML_PATH = "tela-principal-admin.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    private BarChart<String, Number> barChartSangue;

    private ControladorArquivoBancoSangue controladorArquivoBancoSangue = new ControladorArquivoBancoSangue();

    @FXML
    protected void initialize(){
        // Exibe o nome do usuário logado
        boasVindas.setText("Usuário Logado: \n" + BloodForLive.getUsuarioLogado().getNome());

        // Preenche o gráfico de barras com os dados do estoque de sangue
        preencherGrafico();
    }

    private void preencherGrafico() {
        // Cria uma série para os dados do estoque de sangue
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Estoque de Sangue");

        // Obtém o estoque do arquivo serial
        var bancoSangue = controladorArquivoBancoSangue.lerEstoqueBancoSangue();

        // Adiciona cada tipo sanguíneo e sua quantidade ao gráfico
        bancoSangue.getEstoqueSanguineo().forEach((tipo, quantidade) -> {
            series.getData().add(new XYChart.Data<>(tipo, quantidade));
        });

        barChartSangue.getData().add(series);
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
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}
