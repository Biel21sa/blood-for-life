package br.com.poo.bloodforlife.controladores;

import br.com.poo.bloodforlife.main.BloodForLive;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorDeCena {
    private static Stage stage;

    public static void setSceneStage(Stage stage){
        ControladorDeCena.stage = stage;
    }

    public static void trocarCena(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(ControladorDeCena.class.getResource("/br/com/poo/bloodforlife/" + fxmlPath));
        Parent root = loader.load();
        stage.setTitle("BloodForLife" + " - " + BloodForLive.getBank().getNome());
        stage.setMinWidth(1300);
        stage.setMinHeight(650);
        stage.setScene(new Scene(root));
    }
}
