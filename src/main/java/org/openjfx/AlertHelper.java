package org.openjfx;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertHelper {

    // Show alert box as prompt
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}