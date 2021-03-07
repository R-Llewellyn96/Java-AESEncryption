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
    private Parent root;

    @FXML
    private RadioButton radioButton128;

    @FXML
    private RadioButton radioButton256;

    @FXML
    ToggleGroup toggleGroupKeyLength;

    @FXML
    private TextField txtAesDecryptionKey;

    @FXML
    private TextArea txtAesCiphertext;

    @FXML
    private Button btnGenerateDecryptedText;

    // Define values to get from user inputs
    private int aesKeyLength;
    private String decryptKey;
    private String aesCiphertextString;

    // Returns the required key length based on selection
    private int keyLenRequired() {
        // Check radio button selection
        if (radioButton128.isSelected()) {
            return (24);
        } else {
            return (44);
        }
    }

    // Returns the key length selected in bits
    private int keyLengthBits() {
        if (keyLenRequired() == 24) {
            return 128;
        } else {
            return 256;
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

        // Get encryption key entered
        if(!txtAesDecryptionKey.getText().isEmpty() && !txtAesDecryptionKey.getText().isBlank() && txtAesDecryptionKey.getText().length() == keyLenRequired()) {
            decryptKey = txtAesDecryptionKey.getText().trim();
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a valid AES Encryption Key!\nLength of Encryption key must match radio button selection!");
            return;
        }

        // Get encryption ciphertext entered
        if(!txtAesCiphertext.getText().isEmpty() && !txtAesCiphertext.getText().isBlank()) {
            aesCiphertextString = txtAesCiphertext.getText().trim();
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter valid AES Encrypted Ciphertext!");
            return;
        }

        // User gathered values to perform decryption
        try {
            // Define key and message
            String key = decryptKey;
            String message = aesCiphertextString;

            // Perform encryption, passing message and key
            String IVAndCiphertext = EncryptionRunner.decryptionRunnerWGUI(message, key);

            // Switch to finished popup if successful containing data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("encryptionPopup.fxml"));
            root = loader.load();

            // Load controller from popup
            PopupHelper popupHelper = loader.getController();

            // Use popup controller instance to call method and set values
            popupHelper.setValues(key, IVAndCiphertext);

            // Use popup controller instance to call method and set label values
            popupHelper.setLabels("AES Decryption Key","Decrypted Ciphertext");

            // Generate window of popup
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.hide();
            stage.setMinWidth(780);
            stage.setMinHeight(340);
            stage.setMaxWidth(780);
            stage.setMaxHeight(340);
            stage.setWidth(780);
            stage.setHeight(340);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Runtime Error!",
                    "Unable to Decrypt ciphertext, please check inputs and try again!");
            System.out.println(e);
        }

        // Alert shown if all validation is passed
        AlertHelper.showResolutionAlert(Alert.AlertType.CONFIRMATION, owner, "Decryption submission Successful!",
                "Decrypting Ciphertext...\n" +
                        "Using AES Key Length: " + keyLengthBits() + "\n" +
                        "Using AES Key: " + decryptKey + "\n\n" +
                        "Encrypted Ciphertext: " + aesCiphertextString + "\n", 1024, 1024);
    }


    // Menu window
    public void swtichToMainMenu(ActionEvent event) throws IOException {

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
