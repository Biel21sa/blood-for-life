package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorTelaListaDoador {
    public static final String FXML_PATH = "tela-lista-doador.fxml";

    @FXML
    TableView<Doador> tabelaDoador;

    @FXML
    TableColumn<Doador, String> nomeDoador;

    @FXML
    TableColumn<Doador, String> cpfDoador;

    @FXML
    TableColumn<Doador, String> emailDoador;

    @FXML
    TableColumn<Doador, String> statusDoador;

    ArrayList<Doador> doadores = BloodForLive.getBank().getDoadores();
    ObservableList<Doador> doadorObservableList;

    @FXML
    public void initialize(){
        nomeDoador.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfDoador.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        emailDoador.setCellValueFactory(new PropertyValueFactory<>("email"));
        statusDoador.setCellValueFactory(new PropertyValueFactory<>("status"));

        doadorObservableList = FXCollections.observableArrayList(doadores);
        tabelaDoador.setItems(doadorObservableList);
    }

    @FXML
    protected void cadastrarDoador() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroDoador.FXML_PATH);
    }

    @FXML
    public void voltar()  throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
