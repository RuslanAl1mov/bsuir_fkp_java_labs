package com.bolnica.Patien.controller;


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

import java.io.IOException;
import java.util.Objects;

public class PatientWindowController {
    private Controller controller = new Controller();

    @FXML
    private Button loginBtn;

    @FXML
    private Label statusLabel;

    @FXML
    private Button registerPatientBtn;

    @FXML
    private TextField patientFirstNameTextField;

    @FXML
    private TextField patientSecondNameTextField;

    @FXML
    private TextField patientAgeTefxField;


    @FXML
    void initialize() {
        String regex = "\\d+";

        registerPatientBtn.setOnAction(event -> {
            if(!patientFirstNameTextField.getText().trim().equals("") && !patientSecondNameTextField.getText().trim().equals("") &&
                    !patientAgeTefxField.getText().trim().equals("") && patientAgeTefxField.getText().matches(regex)){
                controller.createPatient(patientFirstNameTextField.getText(), patientSecondNameTextField.getText(),
                        Integer.parseInt(patientAgeTefxField.getText()));

                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Вы зарегестрированы!");
                registerPatientBtn.setDisable(true);
            } else {statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Проверьте поля!");}
        });

        loginBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) loginBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/bolnica/LogInWindow/fxml/loginWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Authorization");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
