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
    private BarChart<String, Number> barChartSangue;

    @FXML
    private HBox legendaContainer;

    final int ESTOQUE_MINIMO = 300;
    final int ESTOQUE_MAXIMO = 1000;
    final int AVISO_ESTOQUE_MINIMO = 400;
    final int AVISO_ESTOQUE_MAXIMO = 900;


    private ControladorArquivoBancoSangue controladorArquivoBancoSangue = new ControladorArquivoBancoSangue();

    @FXML
    protected void initialize(){
        boasVindas.setText("Usuário Logado: \n" + BloodForLive.getUsuarioLogado().getNome());

        preencherGrafico();

        adicionarLegenda();

        verificarEstoque();
    }

    private void preencherGrafico() {
        // Cria uma série para os dados do estoque de sangue
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Estoque de Sangue");
        barChartSangue.setLegendVisible(false);


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
                        } else if (quantidade.intValue() < AVISO_ESTOQUE_MINIMO) {
                            // Estoque próximo do mínimo (laranja)
                            node.setStyle("-fx-bar-fill: #FFC48D;");
                        } else if (quantidade.intValue() > ESTOQUE_MAXIMO){
                            // Estoque acima do máximo (verde escuro)
                            node.setStyle("-fx-bar-fill: #3d843f;");
                        } else if (quantidade.intValue() > AVISO_ESTOQUE_MAXIMO) {
                            // Estoque próximo do máximo (laranja)
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

    private void adicionarLegenda() {
        legendaContainer.getChildren().clear(); // Limpa a legenda anterior

        // Cria um HBox para a legenda
        HBox legendaHBox = new HBox(15); // Espaçamento entre os itens

        // Adiciona a série de tipos sanguíneos
        String[] tiposSanguineos = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        String[] cores = {
                "#A3F188", "#C3F188", "#CFF188", "#E2F188",
                "#B0F188", "#FFF48D", "#FFF06C", "#D7EA67"
        };

        for (int i = 0; i < tiposSanguineos.length; i++) {
            // Cria um retângulo para representar a cor
            javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(15, 15);
            rect.setFill(Color.web(cores[i])); // Define a cor

            // Cria um texto para o tipo sanguíneo
            Text tipoSanguineo = new Text(tiposSanguineos[i]);

            // Adiciona ambos ao HBox da legenda
            HBox legendaItem = new HBox(5, rect, tipoSanguineo); // Espaçamento interno
            legendaHBox.getChildren().add(legendaItem);
        }

        // Adiciona as entradas de estoque mínimo e máximo
        Text estoqueMinimo = new Text("Estoque Mínimo");
        Rectangle minRect = new Rectangle(15, 15);
        minRect.setFill(Color.web("#F18888")); // Vermelho
        HBox estoqueMinimoItem = new HBox(5, minRect, estoqueMinimo);
        legendaHBox.getChildren().add(estoqueMinimoItem);

        Text estoqueMaximo = new Text("Estoque Máximo");
        Rectangle maxRect = new Rectangle(15, 15);
        maxRect.setFill(Color.web("#3D843F")); // Verde escuro
        HBox estoqueMaximoItem = new HBox(5, maxRect, estoqueMaximo);
        legendaHBox.getChildren().add(estoqueMaximoItem);

        Text aviso = new Text("Aviso");
        Rectangle avisoRect = new Rectangle(15, 15);
        avisoRect.setFill(Color.web("#FFC48D")); // Laranja
        HBox avisoItem = new HBox(5, avisoRect, aviso);
        legendaHBox.getChildren().add(avisoItem);

        // Adiciona o HBox da legenda ao container principal
        legendaContainer.getChildren().add(legendaHBox);
    }

    private void verificarEstoque() {
        var bancoSangue = controladorArquivoBancoSangue.lerEstoqueBancoSangue();

        bancoSangue.getEstoqueSanguineo().forEach((tipo, quantidade) -> {
            if (quantidade < ESTOQUE_MINIMO) {
                ControladorAlerta.showAlert(Alert.AlertType.WARNING, "Aviso", "Atenção: Estoque de " + tipo + " está abaixo do mínimo!");
            } else if (quantidade > ESTOQUE_MAXIMO) {
                ControladorAlerta.showAlert(Alert.AlertType.WARNING, "Aviso", "Atenção: Estoque de " + tipo + " está acima do máximo!");
            }
        });
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
    protected void editarGrafico() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaEditarGrafico.FXML_PATH);
    }

    @FXML
    protected void graficoMensal() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaGraficoMensal.FXML_PATH);
    }

    @FXML
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}
