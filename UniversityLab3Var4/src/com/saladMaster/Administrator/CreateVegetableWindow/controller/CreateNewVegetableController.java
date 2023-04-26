package com.saladMaster.Administrator.CreateVegetableWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.saladMaster.Controller_pack.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateNewVegetableController {

    @FXML
    private Button goToMainAdminMenuBtn;

    @FXML
    private TextField vegNameTextField;

    @FXML
    private TextField vegKallTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button saveBtn;

    private final Controller controller = new Controller();


    @FXML
    void initialize() {
        goToMainAdminMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainAdminMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/saladMaster/Administrator/MainWindow/fxml/AdminMainWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveBtn.setOnAction(event -> {
            String regex = "\\d+";

            if (!vegNameTextField.getText().equals("") && !vegKallTextField.getText().equals("") &&
                    vegKallTextField.getText().matches(regex)) {

                    controller.createVegetable(vegNameTextField.getText(), Integer.parseInt(vegKallTextField.getText()));

                    vegNameTextField.setText("");
                    vegKallTextField.setText("");

                    statusLabel.setTextFill(Color.GREEN);
                    statusLabel.setText("Овощь сохранен!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (vegNameTextField.getText().equals("") || vegKallTextField.getText().equals("")) {
                    statusLabel.setText("Заполните все поля!");
                } else if (!vegKallTextField.getText().matches(regex)){
                    statusLabel.setText("Калорийность указывается в цифрах!");
                }
            }
        });
    }
}
