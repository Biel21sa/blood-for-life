package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.RegistroDoacao;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorTelaListaDoacao {

    public static final String FXML_PATH = "tela-lista-doacao.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    TableView<RegistroDoacao> tabelaDoacao;

    @FXML
    TableColumn<RegistroDoacao, String> dataDoacao;

    @FXML
    TableColumn<RegistroDoacao, String> doadorDoacao;

    @FXML
    TableColumn<RegistroDoacao, String> tipoSangueDoacao;

    @FXML
    TableColumn<RegistroDoacao, String> quantidadeDoacao;


    ArrayList<RegistroDoacao> doacoes = BloodForLive.getBank().getDoacoes();
    ObservableList<RegistroDoacao> doacaoObservableList;

    @FXML
    public void initialize(){
        boasVindas.setText(BloodForLive.getUsuarioLogado().getNome());

        dataDoacao.setCellValueFactory(new PropertyValueFactory<>("data"));
        doadorDoacao.setCellValueFactory(new PropertyValueFactory<>("doador"));
        tipoSangueDoacao.setCellValueFactory(new PropertyValueFactory<>("tipoSangue"));
        quantidadeDoacao.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        doacaoObservableList = FXCollections.observableArrayList(doacoes);
        tabelaDoacao.setItems(doacaoObservableList);
        atualizarTabela(doacoes);
    }

    @FXML
    protected void registrarDoacao() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaRegistroDoacao.FXML_PATH);
    }

    private void atualizarTabela(List<RegistroDoacao> listaDoacao) {
        // Limita a lista a no máximo 15 doadores
        List<RegistroDoacao> listaLimitada = listaDoacao.size() > 15 ? listaDoacao.subList(0, 15) : listaDoacao;

        doacaoObservableList = FXCollections.observableArrayList(listaLimitada);
        tabelaDoacao.setItems(doacaoObservableList);
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
