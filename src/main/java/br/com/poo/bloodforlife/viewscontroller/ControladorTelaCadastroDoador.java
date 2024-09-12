package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.doacao.Doador;
import br.com.poo.bloodforlife.doacao.TipoSangue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControladorTelaCadastroDoador {
    public static final String FXML_PATH = "tela-cadastro-doador.fxml";

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoIdade;

    @FXML
    private ComboBox<String> campoTipo;

    @FXML
    private void initialize() {
        campoTipo.getItems().addAll(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        );
    }

    @FXML
    public void cadastrarDoador() throws IOException{
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String email = campoEmail.getText();
        int idade = Integer.parseInt(campoIdade.getText());
        String tipoSanguineo = campoTipo.getValue();

        TipoSangue tipoSangue = new TipoSangue(tipoSanguineo);
        Doador doador = new Doador(nome,cpf,email,idade,tipoSangue);
        Controlador controlador = new Controlador("Hemocentro");
        controlador.cadastrarDoador(doador);
    }

    @FXML
    public void voltar() throws IOException {
        ControladorDeCena.trocarCena(ControladorTelaCadastroUsuario.FXML_PATH);
    }
}
