package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.openjfx.validation.Validation;
import runners.EncryptionRunner;
import services.GeneratorService;

import java.io.IOException;

public class EncryptionController {

    private Stage stage;
    private Scene scene;

    @FXML
    ToggleGroup keyLengthToggleGroup;

    @FXML
    private RadioButton radioButton128;

    @FXML
    private TextField aesEncryptionKey;

    @FXML
    private TextArea aesEncryptionText;

    @FXML
    private Button generateEncryptionKeyRandom;

    @FXML
    private Button generateEncryptionKeyPassword;

    @FXML
    private Button generateEncryptedTextBtn;

    // Returns the required key length based on selection
    private int keyLenRequired() {
        // Check radio button selection
        if (radioButton128.isSelected()) {
            return (24);
        } else {
            return (44);
        }
        //aesKeyLengthBits = (aesKeyLength * 4);
    }

    // Returns the key length selected in bits
    private int keyLengthBits() {
        if (keyLenRequired() == 24) {
            return 128;
        } else {
            return 256;
        }
    }


    // Handle click event on generate key randomly button
    @FXML
    protected void handleKeyGenButtonAction() {
        Window owner = generateEncryptionKeyRandom.getScene().getWindow();

        // Try catch in case the AES key generation fails or setting text fails
        try {
            // Replace string with function to generate AES key and return string
            String keyString = GeneratorService.generateKey("AES", keyLengthBits());
            aesEncryptionKey.setText(keyString);

            // Alert user that a key has been generated for them and tell them where it is
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Encryption Key Generation Successful!",
                    "Valid AES Encryption key of : " + keyLengthBits() + " bits has been added to your text field!");

            // If theres a problem, tell user there was an error
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "AES Encryption Key could not be generated!");
        }
    }

    // Handle click event on generate key using password button
    @FXML
    protected void handlePasswordKeyGenButtonAction() {
        Window owner = generateEncryptionKeyPassword.getScene().getWindow();

        // Try catch in case the AES key generation fails or setting text fails
        try {
            // Replace string with function to generate AES key and return string
            String testString = "Im a testing string!";
            aesEncryptionKey.setText(testString);

            // Alert user that a key has been generated for them and tell them where it is
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Encryption Key Generation Successful!",
                    "Valid AES Encryption key has been added to your text field!");

            // If theres a problem, tell user there was an error
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "AES Encryption Key could not be generated!");
        }
    }

    // Handle click event on submit button to generate ciphertext
    @FXML
    protected void handleEncryptButtonAction(ActionEvent event) {
        Window owner = generateEncryptedTextBtn.getScene().getWindow();

        // Handle form validation
        if(aesEncryptionKey.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter an AES Encryption Key!");
            return;
        }
        if(aesEncryptionText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a message to encrypt!");
            return;
        }
        if(aesEncryptionKey.getText().length() != keyLenRequired()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "AES Encryption key length is: " + aesEncryptionKey.getText().length()  + ", not the required length of: " + keyLenRequired());
            return;
        }
        if(!Validation.isValidKey(aesEncryptionKey.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Invalid AES Key, please try a valid key!");
            return;
        }

        // Run if no validation has failed
        try {
            // Define key and message
            String key = aesEncryptionKey.getText().trim();
            String message = aesEncryptionText.getText();

            // Perform encryption, passing message and key
            String IVAndCiphertext = EncryptionRunner.encryptionRunnerWKey(message, key);

            // Switch to finished popup if successful containing data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("encryptionPopup.fxml"));
            Parent root = loader.load();

            // Load controller from popup
            PopupHelper popupHelper = loader.getController();

            // Use popup controller instance to call method and set values
            popupHelper.setValues(key, IVAndCiphertext);

            // Generate window of popup
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.hide();
            stage.setMinWidth(800);
            stage.setMinHeight(500);
            stage.setMaxWidth(800);
            stage.setMaxHeight(500);
            stage.setWidth(800);
            stage.setHeight(500);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            // If validation fails, show error message
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Runtime Error!",
                    "Unable to Encrypt ciphertext, please check inputs and try again!");
        }
    }

    // Menu window
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(400);
        stage.setMinHeight(150);
        stage.setMaxWidth(400);
        stage.setMaxHeight(150);
        stage.setWidth(400);
        stage.setHeight(150);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
