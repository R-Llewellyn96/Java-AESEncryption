package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.IOException;

public class PopupHelper {

    @FXML
    private Menu menuBarTitleText;

    @FXML
    private Button btnReturnToEncryption;

    @FXML
    private TextField aesEncryptionKey;

    @FXML
    private TextArea aesEncryptionText;

    @FXML
    private Label aesEncryptionKeyLabel;

    @FXML
    private Label aesEncryptionTextLabel;

    @FXML
    private Region regionKeyPrepend;

    @FXML
    private Region regionMessagePrepend;

    // Set Menu Title text
    public void setMenuBarTitleText(String menuText) {
        menuBarTitleText.setText(menuText);
    }

    // Set Return Button text
    public void setButtonText(String buttonText){
        btnReturnToEncryption.setText(buttonText);
    }

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

    // Set region sizes
    public void setRegion(int keyRegionLength, int messageRegionLength) {
        regionKeyPrepend.setMinWidth(keyRegionLength);
        regionMessagePrepend.setMinWidth(messageRegionLength);
    }

    // Set action on click of return button, switch to return to decryption window
    public void switchBackButtonAction() {
        btnReturnToEncryption.setOnAction(e -> {
            try {
                switchToDecryption(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    // Encryption window
    public void switchToEncryption(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("encryptionWindow.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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

    // Decryption window
    public void switchToDecryption(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("decryptionWindow.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(780);
        stage.setMinHeight(300);
        stage.setMaxWidth(780);
        stage.setMaxHeight(300);
        stage.setWidth(780);
        stage.setHeight(300);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}