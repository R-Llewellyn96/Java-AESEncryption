package org.openjfx;

import controllers.CodebookGenerator;
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
import java.util.Objects;

public class CodebookController {

    @FXML
    private RadioButton radioButton128;

    @FXML
    private ChoiceBox choiceBoxMonth;

    @FXML
    private Button btnGenerateCodebook;

    // Returns the required key length based on selection
    private int keyLenBitsRequired() {
        // Check radio button selection
        if (radioButton128.isSelected()) {
            return (128);
        } else {
            return (256);
        }
    }

    // Method generates codebook for selected month
    public void generateCodebookClicked() {
        Window owner = btnGenerateCodebook.getScene().getWindow();

        // Get index of selected month
        int selectedMonth = (choiceBoxMonth.getSelectionModel().getSelectedIndex() + 1);

        // Sets selected month to 13 if leap year is selected
        if (selectedMonth <= 0) {
            return;
        } else if (selectedMonth < 3) {
           selectedMonth = selectedMonth;
        } else if (selectedMonth == 3) {
            selectedMonth = 13;
        } else {
            selectedMonth = selectedMonth - 1;
        }

        // Attempt to generate codebook using user inputs, if successful show alert, if a failure show error alert
        try {
            CodebookGenerator.generateCodeBook(selectedMonth, keyLenBitsRequired());
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Codebook submission successful!",
                    "Codebook Generated for month: " + choiceBoxMonth.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Codebook submission failed!",
                    "Error generating Codebook!");
        }
    }

    // Menu window
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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
