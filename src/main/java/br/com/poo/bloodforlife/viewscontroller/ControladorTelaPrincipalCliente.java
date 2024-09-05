package br.com.poo.bloodforlife.viewscontroller;

import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ControladorTelaPrincipalCliente {

    public static final String FXML_PATH = "tela-principal-cliente.fxml";

    @FXML
    private Text boasVindas;

    @FXML
    protected void initialize(){
        boasVindas.setText("Usuario Logado: \n" + BloodForLive.getUsuarioLogado().getNome());
    }

    @FXML
    protected void acessarContas(){
        System.out.println("Acessar Contas");
    }

    @FXML
    protected void sair() throws IOException {
        BloodForLive.setUsuarioLogado(null);
        ControladorDeCena.trocarCena(ControladorTelaLogin.FXML_PATH);
    }
}

