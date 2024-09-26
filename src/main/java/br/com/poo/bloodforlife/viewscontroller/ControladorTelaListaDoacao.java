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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorTelaListaDoacao {

    public static final String FXML_PATH = "tela-lista-doacao.fxml";

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
    public void voltar()  throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
