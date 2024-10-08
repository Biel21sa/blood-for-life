package br.com.poo.bloodforlife.viewscontroller;

import javafx.scene.control.Alert;

public class ControladorAlerta {

    public static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
