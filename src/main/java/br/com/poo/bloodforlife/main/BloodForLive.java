package br.com.poo.bloodforlife.main;

import br.com.poo.bloodforlife.controladores.Controlador;
import br.com.poo.bloodforlife.controladores.ControladorDeCena;
import br.com.poo.bloodforlife.usuarios.Usuario;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class BloodForLive extends Application {

    private static Controlador bancoDeSangue = new Controlador("Hemocentro");
    private static Usuario usuarioLogado = null;


    @Override
    public void start(Stage stage) throws IOException {
        ControladorDeCena.setSceneStage(stage);
        ControladorDeCena.trocarCena("tela-login.fxml");
        stage.setResizable(false);
        stage.show();
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        BloodForLive.usuarioLogado = usuarioLogado;
    }

    public static Controlador getBank(){
        return bancoDeSangue;
    }

    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }

    public static void main(String[] args) {
        launch();
    }
}
