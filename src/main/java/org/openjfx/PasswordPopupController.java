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
import services.GeneratorService;

import java.io.IOException;

public class PasswordPopupController {

    @FXML
    private TextField aesEncryptionPass;

    @FXML
    private Button generateEncryptionKeyBtn;

    // Handle click event on submit button to generate ciphertext
    @FXML
    protected void handleGenerateButtonAction(ActionEvent event) {

        int passLenRequired = 8;

        Window owner = generateEncryptionKeyBtn.getScene().getWindow();

        // Handle form validation
        if(aesEncryptionPass.getText().trim().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a Password!");
            return;
        }
        if(aesEncryptionPass.getText().trim().isBlank()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a Password!");
            return;
        }
        if(aesEncryptionPass.getText().trim().length() < passLenRequired) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Entered Password's length is: " + aesEncryptionPass.getText().length()  + ", not the required length of: " + passLenRequired);
            return;
        }

        // Run if no validation has failed
        try {
            // Define key and message
            String pass = aesEncryptionPass.getText().trim();

            // Generate Secure Random Salt string
            String salt = GeneratorService.generateSalt();

            // Perform key generation, using password and generated salt
            String keyFromPass = GeneratorService.getKeyFromPassword(pass, salt);

            // Switch to finished popup if successful containing data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("encryptionWindow.fxml"));
            Parent root = loader.load();

            // Load controller from popup
            EncryptionController encryptionController = loader.getController();

            // Use encryption controller instance to call method and set value of key
            encryptionController.setValues(keyFromPass);

            // Use encryption controller instance to call method and set value of radio button 256 to true
            encryptionController.setRadioButton256Selection(true);

            // Generate window of popup
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

            System.out.println(salt);

            // Alert user that a key has been generated for them and tell them where it is
            AlertHelper.showBigAlert(Alert.AlertType.CONFIRMATION, owner, "Encryption Key Generation Successful!",
                    "Generated Salt: " + salt + "\n\n" + "Using Password: " + pass + "\n" +
                            "Valid AES Encryption key of : " + keyFromPass + "\nhas been added to your text field!"
            );

            // If validation fails, show error message
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Runtime Error!",
                    "Unable to Encrypt ciphertext, please check inputs and try again!");
        }
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
}