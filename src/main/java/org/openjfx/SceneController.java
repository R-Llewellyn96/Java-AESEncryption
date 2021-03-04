package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnEncryptionMenu;

    @FXML
    private Button btnDecryptionMenu;

    @FXML
    private Button btnCodebookMenu;

    // Encryption Window
    public void swtichToEncryption(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("encryptionWindow.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setWidth(780);
        stage.setHeight(440);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // Decryption Window
    public void swtichToDecryption(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("decryptionWindow.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setWidth(780);
        stage.setHeight(440);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    // Codebook window
    public void swtichToCodebook(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("codebookWindow.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setWidth(450);
        stage.setHeight(225);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    // Menu window
    public void swtichToMainMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setWidth(400);
        stage.setHeight(150);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}