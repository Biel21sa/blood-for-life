package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import br.com.poo.bloodforlife.usuarios.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorTelaListaUsuario {

    public static final String FXML_PATH = "tela-lista-usuario.fxml";

    @FXML
    TableView<Usuario> tabelaUsuario;

    @FXML
    TableColumn<Usuario, String> idUsuario;

    @FXML
    TableColumn<Usuario, String> nomeUsuario;

    @FXML
    TableColumn<Usuario, String> usuarioUsuario;

    @FXML
    TableColumn<Usuario, String> tipoUsuario;


    ArrayList<Usuario> usuarios = BloodForLive.getBank().getUsuarios();
    ObservableList<Usuario> usuarioObservableList;

    @FXML
    public void initialize(){
        idUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        usuarioUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        usuarioObservableList = FXCollections.observableArrayList(usuarios);
        tabelaUsuario.setItems(usuarioObservableList);
    }

    @FXML
    protected void cadastrarUsuario() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }

    @FXML
    public void voltar()  throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaPrincipalAdmin.FXML_PATH);
    }
}
