package com.bolnica.Doctor.PrescribeTreatmentWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.bolnica.Controller.Controller;
import com.bolnica.Doctor.AddNewDrugWindow.entity.Drugs;
import com.bolnica.Doctor.PrescribeTreatmentWindow.entity.DrugMaster;
import com.bolnica.Patien.entity.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PrescribeTreatmentController {

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox ExistedDrugsVBoxLayer;

    @FXML
    private TextField doctorFirstNameTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private Label drugNameLabel;

    @FXML
    private TextField doctorSecondNameTextField;

    @FXML
    private Button prescribeBtn;

    @FXML
    private VBox existedPatientsVBoxLayer;

    @FXML
    private Label patientNameLabel;

    @FXML
    private ChoiceBox<String> doctorStatus;

    private Controller controller = new Controller();

    private Drugs selectedDrug;

    private Patient selectedPatient;


    public void loadExistedDrugs() {
        int counter = 0;
        for (Drugs drugs: controller.getDrugs()){
            counter ++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }
            newHBox.setAlignment(Pos.CENTER);
            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(354.0);
            newHBox.setMaxWidth(354.0);
            newHBox.setMinHeight(100.0);

            Label label1 = new Label(drugs.getDrugName());
            label1.setFont(Font.font("Georgia", null, null, 15));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(267.0);
            newHBox.getChildren().add(label1);


            Button addFlowerToBucketBtn = new Button("Выбрать");
            addFlowerToBucketBtn.setFont(Font.font("Georgia", null, null, 13));
            addFlowerToBucketBtn.setWrapText(true);
            addFlowerToBucketBtn.setMinHeight(40.0);
            addFlowerToBucketBtn.setPrefWidth(111.0);
            newHBox.getChildren().add(addFlowerToBucketBtn);
            addFlowerToBucketBtn.setOnAction(e -> {
                selectedDrug = drugs;
                drugNameLabel.setText(drugs.getDrugName());
            });

            ExistedDrugsVBoxLayer.getChildren().add(newHBox);
        }
    }

    public void loadExistedPatients() {
        int counter = 0;
        for (Patient patient: controller.getPatients()){
            if (!patient.getStatus()) {
                counter++;
                HBox newHBox = new HBox();
                if (counter % 2 == 1) {
                    newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
                }
                newHBox.setAlignment(Pos.CENTER);
                newHBox.setPrefHeight(80.0);
                newHBox.setPrefWidth(432.0);
                newHBox.setMaxWidth(432.0);
                newHBox.setMinHeight(80.0);

                Button btn1 = new Button(patient.getFirstName() + " " + patient.getSecondName());
                btn1.setFont(Font.font("Georgia", null, null, 16));
                btn1.setWrapText(true);
                btn1.setMinHeight(80.0);
                btn1.setPrefWidth(431.0);
                newHBox.getChildren().add(btn1);
                btn1.setOnAction(e -> {
                    selectedPatient = patient;
                    patientNameLabel.setText(patient.getFirstName() + " " + patient.getSecondName());
                });
                existedPatientsVBoxLayer.getChildren().add(newHBox);
            }
        }
    }


    @FXML
    void initialize() {
        doctorStatus.getItems().addAll("Медсестра", "Доктор");
        doctorStatus.setValue("Медсестра");

        loadExistedDrugs();
        loadExistedPatients();

        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
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

        prescribeBtn.setOnAction(event -> {
            if (!doctorFirstNameTextField.getText().trim().equals("") &&
                !doctorSecondNameTextField.getText().trim().equals("") && selectedPatient != null && selectedDrug != null){

                DrugMaster newMaster = new DrugMaster(doctorFirstNameTextField.getText(),
                        doctorSecondNameTextField.getText(), doctorStatus.getValue());
                selectedDrug.setDrugMaster(newMaster);
                controller.prescribeTreatment(selectedPatient, selectedDrug);

                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Назначение выполнено!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (doctorFirstNameTextField.getText().trim().equals("") || doctorSecondNameTextField.getText().trim().equals("")) {
                    statusLabel.setText("Не указано имя исполняющего!");
                } else if (selectedPatient == null) {
                    statusLabel.setText("Выберите Пациента!");
                } else if (selectedDrug == null) {
                    statusLabel.setText("Выберите Лекарство!!");
                }
            }
        });
    }
}
