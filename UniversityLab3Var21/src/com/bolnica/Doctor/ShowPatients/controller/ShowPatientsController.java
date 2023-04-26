package com.bolnica.Doctor.ShowPatients.controller;

import java.io.IOException;

import com.bolnica.Controller.Controller;
import com.bolnica.Doctor.AddNewDrugWindow.entity.Drugs;
import com.bolnica.Patien.entity.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ShowPatientsController {

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox patientsVBoxLayer;

    @FXML
    private Label statusLineLabel;

    @FXML
    private VBox drugsVBoxLayer;

    @FXML
    private Label patientFirstNameLabel;

    @FXML
    private Label patientSecondNameLabel;

    @FXML
    private Label drugsNumberLabel;

    @FXML
    private Label patientStatusLabel;

    @FXML
    private Button vipizditBtn;

    private final Controller controller = new Controller();

    private Patient selectedPatient;


    public void loadExistedPatients() {
        int counter = 0;
        for (Patient patient: controller.getPatients()){
            counter ++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }
            newHBox.setAlignment(Pos.CENTER);
            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(438.0);
            newHBox.setMaxWidth(438.0);
            newHBox.setMinHeight(100.0);

            Button btn1 = new Button(patient.getFirstName() + " " + patient.getSecondName());
            btn1.setFont(Font.font("Georgia", null, null, 15));
            btn1.setTextAlignment(TextAlignment.CENTER);
            btn1.setAlignment(Pos.CENTER);
            btn1.setWrapText(true);
            btn1.setPrefHeight(100.0);
            btn1.setPrefWidth(273.0);
            newHBox.getChildren().add(btn1);
            btn1.setOnAction(e -> {
                selectedPatient = patient;
                patientFirstNameLabel.setText(patient.getFirstName());
                patientSecondNameLabel.setText(patient.getSecondName());
                drugsNumberLabel.setText(Integer.toString(patient.getPatientDrugs().size()));
                String patientStatus1 = "На лечении";
                if(patient.getStatus()) {
                    patientStatus1 = "Выписан";
                }

                patientStatusLabel.setText(patientStatus1);
                drugsVBoxLayer.getChildren().removeAll(drugsVBoxLayer.getChildren());

                int counter2 = 0;
                for (Drugs drugs: patient.getPatientDrugs()) {
                    counter2++;
                    HBox newDrugHBox = new HBox();
                    if (counter2 % 2 == 1) {
                        newDrugHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
                    }
                    newDrugHBox.setAlignment(Pos.CENTER);
                    newDrugHBox.setPrefHeight(100.0);
                    newDrugHBox.setPrefWidth(438.0);
                    newDrugHBox.setMaxWidth(438.0);
                    newDrugHBox.setMinHeight(100.0);

                    Label label1 = new Label(drugs.getDrugName());
                    label1.setFont(Font.font("Georgia", null, null, 15));
                    label1.setTextAlignment(TextAlignment.CENTER);
                    label1.setAlignment(Pos.CENTER);
                    label1.setWrapText(true);
                    label1.setPrefHeight(100.0);
                    label1.setPrefWidth(198.0);
                    newDrugHBox.getChildren().add(label1);

                    Label label2 = new Label(drugs.getDrugMaster().getStatus() + " " + drugs.getDrugMaster().getFirstName() + " " + drugs.getDrugMaster().getSecondName());
                    label2.setFont(Font.font("Georgia", null, null, 15));
                    label2.setTextAlignment(TextAlignment.CENTER);
                    label2.setAlignment(Pos.CENTER);
                    label2.setWrapText(true);
                    label2.setPrefHeight(100.0);
                    label2.setPrefWidth(240.0);
                    newDrugHBox.getChildren().add(label2);

                    drugsVBoxLayer.getChildren().add(newDrugHBox);
                }
            });

            String patientStatus = "На лечении";
            if(patient.getStatus()){
                patientStatus = "Выписан";
            }
            Button btn2 = new Button(patientStatus);
            btn2.setFont(Font.font("Georgia", null, null, 15));
            btn2.setTextAlignment(TextAlignment.CENTER);
            btn2.setAlignment(Pos.CENTER);
            btn2.setWrapText(true);
            btn2.setMinHeight(100.0);
            btn2.setPrefWidth(165.0);
            newHBox.getChildren().add(btn2);
            btn2.setOnAction(e -> {
                selectedPatient = patient;

                patientFirstNameLabel.setText(patient.getFirstName());
                patientSecondNameLabel.setText(patient.getSecondName());
                drugsNumberLabel.setText(Integer.toString(patient.getPatientDrugs().size()));
                String patientStatus1 = "На лечении";
                if(patient.getStatus()) {
                    patientStatus1 = "Выписан";
                }

                patientStatusLabel.setText(patientStatus1);

                drugsVBoxLayer.getChildren().removeAll(drugsVBoxLayer.getChildren());

                int counter2 = 0;
                for (Drugs drugs: patient.getPatientDrugs()) {
                    counter2++;
                    HBox newDrugHBox = new HBox();
                    if (counter2 % 2 == 1) {
                        newDrugHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
                    }
                    newDrugHBox.setAlignment(Pos.CENTER);
                    newDrugHBox.setPrefHeight(100.0);
                    newDrugHBox.setPrefWidth(438.0);
                    newDrugHBox.setMaxWidth(438.0);
                    newDrugHBox.setMinHeight(100.0);

                    Label label1 = new Label(drugs.getDrugName());
                    label1.setFont(Font.font("Georgia", null, null, 15));
                    label1.setTextAlignment(TextAlignment.CENTER);
                    label1.setAlignment(Pos.CENTER);
                    label1.setWrapText(true);
                    label1.setPrefHeight(100.0);
                    label1.setPrefWidth(198.0);
                    newDrugHBox.getChildren().add(label1);

                    Label label2 = new Label(drugs.getDrugMaster().getStatus() + " " + drugs.getDrugMaster().getFirstName() + " " + drugs.getDrugMaster().getSecondName());
                    label2.setFont(Font.font("Georgia", null, null, 15));
                    label2.setTextAlignment(TextAlignment.CENTER);
                    label2.setAlignment(Pos.CENTER);
                    label2.setWrapText(true);
                    label2.setPrefHeight(100.0);
                    label2.setPrefWidth(240.0);
                    newDrugHBox.getChildren().add(label2);

                    drugsVBoxLayer.getChildren().add(newDrugHBox);
                }
            });

            patientsVBoxLayer.getChildren().add(newHBox);
        }
    }


    @FXML
    void initialize() {
        loadExistedPatients();

        vipizditBtn.setOnAction(event -> {
            if(selectedPatient != null){
                selectedPatient.setStatus(true);
                patientsVBoxLayer.getChildren().removeAll(patientsVBoxLayer.getChildren());
                drugsVBoxLayer.getChildren().removeAll(drugsVBoxLayer.getChildren());
                controller.savePatientsInFile();
                loadExistedPatients();
                statusLineLabel.setTextFill(Color.GREEN);
                statusLineLabel.setText("Пациент выписан!");
            } else {
                statusLineLabel.setTextFill(Color.RED);
                statusLineLabel.setText("Выберите пациента!");}
        });

        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/bolnica/Doctor/MainWindow/fxml/DoctorMainWindow.fxml"));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
