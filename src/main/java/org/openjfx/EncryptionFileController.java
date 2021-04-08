package org.openjfx;

import controllers.CodebookGenerator;
import controllers.FileEncryptController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.EncryptionFileModel;
import org.openjfx.validation.Validation;
import runners.EncryptionRunner;
import services.GeneratorService;

import java.io.File;
import java.io.IOException;

public class EncryptionFileController {

    private Stage stage;
    private Scene scene;

    @FXML
    ToggleGroup keyLengthToggleGroup;

    @FXML
    private RadioButton radioButton128;

    @FXML
    private RadioButton radioButton256;

    @FXML
    private TextField txtfieldAesEncryptionKey;

    @FXML
    private TextField txtfieldFileLoc;

    @FXML
    private Button btnReturnToMainMenu;

    @FXML
    private Button btnEncryptionKeyRandom;

    @FXML
    private Button btnEncryptionKeyPassword;

    @FXML
    private Button btnSelectFile;

    @FXML
    private Button btnEncryptFile;

    private File selectedFile = null;

    // Function allows for setting of key value from outside of window, used for PBE Key Generation
    public void setValues(String keyString) {
        txtfieldAesEncryptionKey.setText(keyString);
    }

    // Function allows for setting of key value from outside of window, used for PBE Key Generation
    public void setValuesFileLocation(String keyString) {
        txtfieldFileLoc.setText(keyString);
    }

    // Function allows for selecting of radio button from outside of window, used for PBE Key Generation
    public void setRadioButton256Selection(Boolean radioBtn256Selected) {
        if (radioBtn256Selected) {
            radioButton128.setSelected(false);
            radioButton256.setSelected(true);
        } else {
            radioButton128.setSelected(true);
            radioButton256.setSelected(false);
        }
    }

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
        Window owner = btnEncryptionKeyRandom.getScene().getWindow();

        // Try catch in case the AES key generation fails or setting text fails
        try {
            // Replace string with function to generate AES key and return string
            String keyString = GeneratorService.generateKey("AES", keyLengthBits());
            txtfieldAesEncryptionKey.setText(keyString);

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
    protected void handlePasswordKeyGenButtonAction(ActionEvent event) {
        Window owner = btnEncryptionKeyPassword.getScene().getWindow();

        // Try catch in case the AES key generation fails or setting text fails
        try {
            Parent root = FXMLLoader.load(getClass().getResource("passwordPopup.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.hide();
            stage.setMinWidth(800);
            stage.setMinHeight(400);
            stage.setMaxWidth(800);
            stage.setMaxHeight(400);
            stage.setWidth(800);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            // If theres a problem, tell user there was an error
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "AES Encryption Key could not be generated!");
        }
    }

    // Handle click event on File Selection button to get file
    @FXML
    protected void handleSelectFileButtonAction(ActionEvent event) {
        Window owner = btnEncryptionKeyRandom.getScene().getWindow();

        // Define instance of file chooser
        FileChooser fileChooser = new FileChooser();

        // Get user selected file from dialog
        selectedFile = fileChooser.showOpenDialog(owner);

        // Check whether user has selected file, if not set path to blank
        if (selectedFile != null) {
            // Get path to selected file
            String filePathString = selectedFile.getAbsolutePath();

            // Set file path in text field
            txtfieldFileLoc.setText(filePathString);
        } else {
            // Set file path in text field
            txtfieldFileLoc.setText("");
        }
    }

        // Handle click event on submit button to generate ciphertext
    @FXML
    protected void handleEncryptFileButtonAction(ActionEvent event) {
        Window owner = btnEncryptFile.getScene().getWindow();

        // Handle form validation
        if(txtfieldAesEncryptionKey.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter an AES Encryption Key!");
            return;
        }
        if(txtfieldFileLoc.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a Path to your file to encrypt!");
            return;
        }
        if(txtfieldAesEncryptionKey.getText().length() != keyLenRequired()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "AES Encryption key length is: " + txtfieldAesEncryptionKey.getText().length()  + ", not the required length of: " + keyLenRequired());
            return;
        }
        if(!Validation.isValidKey(txtfieldAesEncryptionKey.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Invalid AES Key, please try a valid key!");
            return;
        }
        if(selectedFile == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "No file selected! Please select file for Encryption.");
            return;
        }

        // Run if no validation has failed
        try {
            // Define key and message
            String key = txtfieldAesEncryptionKey.getText().trim();
            String filePath = txtfieldFileLoc.getText().trim();

            // Perform encryption, passing file and key

            // Define encryption model object
            EncryptionFileModel encryptionFileModel = new EncryptionFileModel();

            // Set object values
            encryptionFileModel.setEncryptionAlgorithm("AES/CBC/PKCS5Padding");
            encryptionFileModel.setKeyString(key);
            encryptionFileModel.setIvString(GeneratorService.generateIV());
            encryptionFileModel.setFile(selectedFile);
            
            // Generate empty output file to fill
            File outputFile = new File("encryptedFile.BIN");

            // Encrypt bytes from selected file into output file
            FileEncryptController.encryptFiles(encryptionFileModel, selectedFile, outputFile);

            // Create output file in OS filesystem
            if (outputFile.createNewFile()) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "File Encryption successful!",
                        "New Encrypted File Generated!");
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "File Encryption successful!",
                        "Existing Encrypted File Overwritten!");
            }

            // If validation fails, show error message
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Runtime Error!",
                    "Unable to Encrypt file, please check inputs and try again!");
        }
    }

    // Menu window
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setMinWidth(500);
        stage.setMinHeight(135);
        stage.setMaxWidth(500);
        stage.setMaxHeight(135);
        stage.setWidth(500);
        stage.setHeight(135);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
