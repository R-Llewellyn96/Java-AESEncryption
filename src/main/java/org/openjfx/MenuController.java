package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Encryption Window
    public void switchToEncryption(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("encryptionWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(780);
        stage.setMinHeight(440);
        stage.setMaxWidth(780);
        stage.setMaxHeight(440);
        stage.setWidth(780);
        stage.setHeight(440);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // Decryption Window
    public void switchToDecryption(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("decryptionWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(800);
        stage.setMinHeight(320);
        stage.setMaxWidth(800);
        stage.setMaxHeight(320);
        stage.setWidth(800);
        stage.setHeight(320);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    // Codebook window
    public void switchToCodebook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("codebookWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(450);
        stage.setMinHeight(250);
        stage.setMaxWidth(450);
        stage.setMaxHeight(250);
        stage.setWidth(450);
        stage.setHeight(250);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // Encryption File window
    public void switchToEncryptionFile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("encryptionFileWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(780);
        stage.setMinHeight(270);
        stage.setMaxWidth(780);
        stage.setMaxHeight(270);
        stage.setWidth(780);
        stage.setHeight(270);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // Decryption File window
    public void switchToDecryptionFile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("decryptionFileWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(780);
        stage.setMinHeight(440);
        stage.setMaxWidth(780);
        stage.setMaxHeight(440);
        stage.setWidth(780);
        stage.setHeight(440);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}