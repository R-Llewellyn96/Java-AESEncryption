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

public class CodebookController {

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
    private ChoiceBox choiceBoxMonth;

    @FXML
    private Button btnMenu;

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

        try {
            CodebookGenerator.generateCodeBook(selectedMonth, 256);
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Codebook submission successful!",
                    "Generating Codebook for selected month...");
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Codebook submission failed!",
                    "Error generating Codebook!");
        }

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
