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
import java.io.IOException;

public class PopupHelper {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnReturnToEncryption;

    @FXML
    ToggleGroup keyLengthToggleGroup;

    @FXML
    private RadioButton radioButton128;

    @FXML
    private RadioButton radioButton256;

    @FXML
    private static TextField aesEncryptionKey;

    @FXML
    private static TextArea aesEncryptionText;

    @FXML
    private Button generateEncryptionKeyRandom;

    @FXML
    private Button generateEncryptionKeyPassword;

    @FXML
    private Button generateEncryptedTextBtn;

    public static void setValues(String keyString, String ivAndCiphertextString) {
        try{
        aesEncryptionKey.setText("keyString");
        aesEncryptionText.setText("ivAndCiphertextString");
        } catch (Exception e) {
            System.out.println();
        }
    }

    public static void showPopup(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void showResolutionAlert(Alert.AlertType alertType, Window owner, String title, String message, int width, int height) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.setWidth(width);
        alert.setHeight(height);
        alert.setResizable(false);
        alert.show();
    }

    // Menu window
    public void swtichToEncryption(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("encryptionWindow.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.hide();
        stage.setWidth(780);
        stage.setHeight(440);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}