package com.bolnica.Doctor.MainWindow.controler;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DoctorMainMenuController {

    @FXML
    private Button createNewDragBtn;

    @FXML
    private Button prescribeTreatmentBtn;

    @FXML
    private Button patientsListBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button loginBtn;

    @FXML
    void initialize() {
        loginBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) loginBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/bolnica/LogInWindow/fxml/loginWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root, 640, 440));
                primaryStage.setMaxWidth(640);
                primaryStage.setMaxHeight(440);
                primaryStage.setMinWidth(640);
                primaryStage.setMinHeight(440);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createNewDragBtn.setOnAction(event -> {
            Stage mainStage = (Stage) createNewDragBtn.getScene().getWindow();
            mainStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/bolnica/Doctor/AddNewDrugWindow/fxml/createDrugWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        prescribeTreatmentBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) prescribeTreatmentBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/bolnica/Doctor/PrescribeTreatmentWindow/fxml/PrescribeTreatmentWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        patientsListBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) patientsListBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/bolnica/Doctor/ShowPatients/fxml/ShowPatientsWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root2));
                primaryStage.show();
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
