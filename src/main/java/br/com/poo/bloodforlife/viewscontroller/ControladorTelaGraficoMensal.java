package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.manipulacaoarquivo.ControladorArquivoBancoSangue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


import java.io.IOException;

public class ControladorTelaGraficoMensal {

    public static final String FXML_PATH = "tela-grafico-mensal.fxml";

    @FXML
    private BarChart<String, Number> barChartSangue;


    final int ESTOQUE_MINIMO = 300;
    final int ESTOQUE_MAXIMO = 1000;
    final int AVISO_ESTOQUE_MINIMO = 400;
    final int AVISO_ESTOQUE_MAXIMO = 900;


    private ControladorArquivoBancoSangue controladorArquivoBancoSangue = new ControladorArquivoBancoSangue();

    @FXML
    protected void initialize(){

        preencherGrafico();
    }


    private void preencherGrafico() {
        // Limpar dados existentes no gráfico
        barChartSangue.getData().clear();

        // Configurar a largura das barras
        barChartSangue.setCategoryGap(0);
        barChartSangue.setBarGap(2);

        // Tipos sanguíneos
        String[] tiposSanguineos = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

        // Meses do ano
        String[] meses = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        // Mapa para armazenar doações mensais por tipo sanguíneo
        Map<String, int[]> doacoesMensaisPorTipo = new HashMap<>();

        // Inicializar o mapa com 12 meses para cada tipo sanguíneo
        for (String tipo : tiposSanguineos) {
            doacoesMensaisPorTipo.put(tipo, new int[12]);
        }

        // Ler as doações do arquivo de doações
        var listaDoacoes = BloodForLive.getBank().getDoacoes();

        // Percorrer cada doação e organizar por mês e tipo sanguíneo
        listaDoacoes.forEach(doacao -> {
            String tipoSanguineo = doacao.getTipoSangue();  // Supondo que existe um método para pegar o tipo sanguíneo da doação
            LocalDate dataDoacao = doacao.getData();     // Supondo que existe um método para pegar a data da doação
            int mes = dataDoacao.getMonthValue() - 1;          // Obter o mês (0-11 para o array de meses)

            // Adicionar a quantidade doada ao mês e tipo correspondente
            doacoesMensaisPorTipo.get(tipoSanguineo)[mes] += (int) doacao.getQuantidade();
        });

        // Cores para cada tipo sanguíneo
        String[] cores = {
                "#A3F188", "#C3F188", "#CFF188", "#E2F188",
                "#B0F188", "#FFF48D", "#FFF06C", "#D7EA67"
        };

        // Para cada tipo sanguíneo, crie uma série
        for (int i = 0; i < tiposSanguineos.length; i++) {
            // Copia de 'i' para tornar a variável efetivamente final
            int index = i;

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(tiposSanguineos[index]);

            // Adiciona os dados de doação mensal para este tipo sanguíneo
            int[] doacoesPorMes = doacoesMensaisPorTipo.get(tiposSanguineos[index]);
            for (int mes = 0; mes < meses.length; mes++) {
                XYChart.Data<String, Number> data = new XYChart.Data<>(meses[mes], doacoesPorMes[mes]);
                series.getData().add(data);
            }

            // Adiciona a série ao gráfico
            barChartSangue.getData().add(series);

            // Aplicar cor às barras da série
            for (XYChart.Data<String, Number> data : series.getData()) {
                data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                    if (newNode != null) {
                        newNode.setStyle("-fx-bar-fill: " + cores[index] + ";");
                    }
                });
            }
        }
    }



    @FXML
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}
