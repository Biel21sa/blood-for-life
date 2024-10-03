package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @FXML
    private TextField campoPesquisaCpf;

    @FXML
    private TextField campoDeletarDoador;


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
        atualizarTabela(doadores);
    }

    @FXML
    protected void cadastrarDoador() throws IOException{
        ControladorDeCena.trocarCena(ControladorTelaCadastroDoador.FXML_PATH);
    }

    @FXML
    public void pesquisarDoador() {
        String cpfPesquisa = campoPesquisaCpf.getText().trim();

        // Filtra a lista de doadores pelo CPF
        List<Doador> resultadoPesquisa = doadores.stream()
                .filter(doador -> doador.getCpf().contains(cpfPesquisa))
                .collect(Collectors.toList());

        doadorObservableList = FXCollections.observableArrayList(resultadoPesquisa);
        tabelaDoador.setItems(doadorObservableList);
        atualizarTabela(resultadoPesquisa);
    }

    @FXML
    public void excluirDoador() {
        String cpfPesquisa = campoDeletarDoador.getText().trim();

        BloodForLive.getBank().excluirDoador(cpfPesquisa);

        ControladorAlerta.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Excluído o doador com sucesso!");

        ArrayList<Doador> doadores = BloodForLive.getBank().getDoadores();
        doadorObservableList = FXCollections.observableArrayList(doadores);
        tabelaDoador.setItems(doadorObservableList);
        atualizarTabela(doadores);
    }

    private void atualizarTabela(List<Doador> listaDoador) {
        // Limita a lista a no máximo 15 doadores
        List<Doador> listaLimitada = listaDoador.size() > 15 ? listaDoador.subList(0, 15) : listaDoador;

        doadorObservableList = FXCollections.observableArrayList(listaLimitada);
        tabelaDoador.setItems(doadorObservableList);
    }

    @FXML
    public void voltar()  throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }


}
