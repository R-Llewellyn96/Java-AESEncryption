package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.setTitle("AES Encryptor / Decryptor");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}