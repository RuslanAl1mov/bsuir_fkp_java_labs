package com.bolnica.LogInWindow.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginWindowController {

    @FXML
    private Button setDoctorBtn;

    @FXML
    private Button setPatientBtn;

    @FXML
    private Button exitBtn;
    @FXML
    void initialize(){
        setDoctorBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) setDoctorBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/bolnica/Doctor/MainWindow/fxml/DoctorMainWindow.fxml"));
                Stage newStage = new Stage();
                newStage.setTitle("Doctor");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setPatientBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) setPatientBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/bolnica/Patien/fxml/PatientMainWindow.fxml"));
                Stage newStage = new Stage();
                newStage.setTitle("Patient");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) exitBtn.getScene().getWindow();
            LogInStage.close();
        });

    }
}
