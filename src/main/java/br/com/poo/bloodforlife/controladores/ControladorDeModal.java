package br.com.poo.bloodforlife.controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorDeModal {

    // Método para abrir um modal
    public static void abrirModal(String fxmlPath, Stage ownerStage) throws IOException {
        // Carrega o FXML para o conteúdo do modal
        FXMLLoader loader = new FXMLLoader(ControladorDeModal.class.getResource("/br/com/poo/bloodforlife/" + fxmlPath));
        Parent root = loader.load();

        // Cria um novo Stage para o modal
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Torna o modal modal
        modalStage.initOwner(ownerStage); // Define a janela principal como a dona do modal
        modalStage.setTitle("Modal - " + fxmlPath);
        modalStage.setMinWidth(400); // Define a largura mínima do modal
        modalStage.setMinHeight(300); // Define a altura mínima do modal

        // Define o conteúdo do modal e exibe
        modalStage.setScene(new Scene(root));
        modalStage.showAndWait(); // Espera até que o modal seja fechado
    }
}

