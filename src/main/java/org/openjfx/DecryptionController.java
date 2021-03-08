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
import runners.EncryptionRunner;

import java.io.IOException;

public class DecryptionController {

    private Stage stage;
    private Scene scene;

    @FXML
    private RadioButton radioButton128;

    @FXML
    ToggleGroup toggleGroupKeyLength;

    @FXML
    private TextField txtAesDecryptionKey;

    @FXML
    private TextArea txtAesCiphertext;

    @FXML
    private Button btnGenerateDecryptedText;

    // Returns the required key length based on selection
    private int keyLenRequired() {
        // Check radio button selection
        if (radioButton128.isSelected()) {
            return (24);
        } else {
            return (44);
        }
    }

    // Handle click event on submit button to generate ciphertext
    @FXML
    protected void handleDecryptButtonAction(ActionEvent event) {

        // Throw Alert window if any errors
        Window owner = btnGenerateDecryptedText.getScene().getWindow();
        if(txtAesDecryptionKey.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a valid AES Encryption Key!");
            return;
        }
        if(txtAesDecryptionKey.getText().length() != keyLenRequired()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "AES Encryption key length is: " + txtAesDecryptionKey.getText().length()  + ", not the required length of: " + keyLenRequired());
            return;
        }
        if(txtAesCiphertext.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a Ciphertext message to decrypt!");
            return;
        }
        if(txtAesCiphertext.getText().length() < 32) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Warning: Ciphertext must contain pre-pended 32 character initialisation vector!");
            return;
        }

        // Get encryption key entered, if there isn't one show error
        String decryptKey;
        if(!txtAesDecryptionKey.getText().isEmpty() && !txtAesDecryptionKey.getText().isBlank() && txtAesDecryptionKey.getText().length() == keyLenRequired()) {
            decryptKey = txtAesDecryptionKey.getText().trim();
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a valid AES Encryption Key!\nLength of Encryption key must match radio button selection!");
            return;
        }

        // Get encryption ciphertext entered, if there isn't one show error
        String aesCiphertextString;
        if(!txtAesCiphertext.getText().isEmpty() && !txtAesCiphertext.getText().isBlank()) {
            aesCiphertextString = txtAesCiphertext.getText().trim();
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter valid AES Encrypted Ciphertext!");
            return;
        }

        // User gathered values to perform decryption
        try {
            // Perform encryption, passing message and key
            String IVAndCiphertext = EncryptionRunner.decryptionRunnerWGUI(aesCiphertextString, decryptKey);

            // Switch to finished popup if successful containing data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("encryptionPopup.fxml"));
            Parent root = loader.load();

            // Load controller from popup
            PopupHelper popupHelper = loader.getController();

            // Use popup controller instance to call method and set values
            popupHelper.setValues(decryptKey, IVAndCiphertext);

            // Use popup controller instance to call method and set label values
            popupHelper.setLabels("AES Decryption Key","Decrypted Ciphertext");

            // Use popup controller instance to call method and set region sizes
            popupHelper.setRegion(13,15);

            // Set Return button text
            popupHelper.setButtonText("Back To Decryption");

            // Set Menu Title text
            popupHelper.setMenuBarTitleText("Decryption Mode");

            // Use popup controller instance to call method and set back button action
            popupHelper.switchBackButtonAction();

            // Generate window of popup
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.hide();
            stage.setMinWidth(780);
            stage.setMinHeight(480);
            stage.setMaxWidth(780);
            stage.setMaxHeight(480);
            stage.setWidth(780);
            stage.setHeight(480);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            // Catch any runtime error and fail safely by showing error message
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Runtime Error!",
                    "Unable to Decrypt ciphertext, please check inputs and try again!");
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
