package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class PopupHelper {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField aesEncryptionKey;

    @FXML
    private TextArea aesEncryptionText;

    @FXML
    private Label aesEncryptionKeyLabel;

    @FXML
    private Label aesEncryptionTextLabel;

    // Set text area text
    public void setValues(String keyString, String ivAndCiphertextString) {
        aesEncryptionKey.setText(keyString);
        aesEncryptionText.setText(ivAndCiphertextString);
        aesEncryptionKey.setEditable(false);
        aesEncryptionText.setEditable(false);
    }

    // Set label text
    public void setLabels(String encryptionKeyLabel, String encryptedMessageLabel) {
        aesEncryptionKeyLabel.setText(encryptionKeyLabel);
        aesEncryptionTextLabel.setText(encryptedMessageLabel);
    }

    // Menu window
    public void switchToEncryption(ActionEvent event) throws IOException {

        //Parent root = FXMLLoader.load(getClass().getResource("encryptionWindow.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("encryptionWindow.fxml"));
        root = loader.load();

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