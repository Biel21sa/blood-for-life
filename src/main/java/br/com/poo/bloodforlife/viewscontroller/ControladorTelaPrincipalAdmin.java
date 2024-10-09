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
                "#A3F188",
                "#C3F188",
                "#CFF188",
                "#E2F188",
                "#B0F188",
                "#FFF48D",
                "#FFF06C",
                "#D7EA67"
        };

        // Mapeamento de tipos sanguíneos
        String[] tiposSanguineos = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

        // Define o estoque mínimo e o valor de aviso
        final int ESTOQUE_MINIMO = 75;
        final int AVISO_ESTOQUE = 100; // valor para indicar que o estoque está se aproximando do mínimo

        // Adiciona cada tipo sanguíneo e sua quantidade ao gráfico
        bancoSangue.getEstoqueSanguineo().forEach((tipo, quantidade) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>(tipo, quantidade);
            series.getData().add(data);
        });

        barChartSangue.getData().add(series);

        // Aplicar as cores e estilos às barras
        for (XYChart.Data<String, Number> data : series.getData()) {
            String tipo = data.getXValue();
            Number quantidade = data.getYValue();

            for (int i = 0; i < tiposSanguineos.length; i++) {
                if (tiposSanguineos[i].equals(tipo)) {
                    // Obtém o nó da barra e define sua cor e estilo
                    Node node = data.getNode();
                    if (node != null) {
                        if (quantidade.intValue() < ESTOQUE_MINIMO) {
                            // Estoque abaixo do mínimo (vermelho)
                            node.setStyle("-fx-bar-fill: #F18888;");
                        } else if (quantidade.intValue() < AVISO_ESTOQUE) {
                            // Estoque próximo do mínimo (amarelo)
                            node.setStyle("-fx-bar-fill: #FFC48D;");
                        } else {
                            // Estoque acima do valor de aviso, usar a cor padrão
                            node.setStyle("-fx-bar-fill: " + cores[i] + ";");
                        }
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
