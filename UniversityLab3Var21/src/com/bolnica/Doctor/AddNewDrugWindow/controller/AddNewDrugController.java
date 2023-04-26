package com.bolnica.Doctor.AddNewDrugWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.bolnica.Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddNewDrugController {

    @FXML
    private Button goToMainAdminMenuBtn;

    @FXML
    private TextField newDrugNameTextField;

    @FXML
    private Button saveDrugBtn;

    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        goToMainAdminMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainAdminMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/bolnica/Doctor/MainWindow/fxml/DoctorMainWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveDrugBtn.setOnAction(event -> {
            if (!newDrugNameTextField.getText().trim().equals("")) {

                try {
                    Controller controller = new Controller();
                    controller.createDrug(newDrugNameTextField.getText());

                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("Таблетки сохранены!");
                    newDrugNameTextField.setText("");

                } catch (Exception e) {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Неверно заполнены поля!");
                }

            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("ЗАПОЛНИТЕ ПОЛЕ!");
            }
        });
    }
}
