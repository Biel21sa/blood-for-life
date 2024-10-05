package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoBancoSangue;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

        // Defina as cores harmoniosas para cada tipo sanguíneo
        String[] cores = {
                "#FF6F00",
                "#FFA726",
                "#FFD54F",
                "#FFF176",
                "#DCE775",
                "#AEDD55",
                "#FF7043",
                "#F57C00"
        };

        // Mapeamento de tipos sanguíneos
        String[] tiposSanguineos = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

        // Adiciona cada tipo sanguíneo e sua quantidade ao gráfico
        bancoSangue.getEstoqueSanguineo().forEach((tipo, quantidade) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>(tipo, quantidade);
            series.getData().add(data);
        });

        barChartSangue.getData().add(series);

        // Aplicar as cores às barras
        for (XYChart.Data<String, Number> data : series.getData()) {
            String tipo = data.getXValue();
            for (int i = 0; i < tiposSanguineos.length; i++) {
                if (tiposSanguineos[i].equals(tipo)) {
                    // Obtém o nó da barra e define sua cor
                    Node node = data.getNode();
                    if (node != null) {
                        node.setStyle("-fx-bar-fill: " + cores[i] + ";");
                    }
                }
            }
        }
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
