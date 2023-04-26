package com.saladMaster.Administrator.MainWindow.controller;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainMenuController {

    @FXML
    private Button createNewVegetableBtn;

    @FXML
    private Button createSaladBtn;

    @FXML
    private Button saladsListBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize() {
        createNewVegetableBtn.setOnAction(event -> {
            Stage mainStage = (Stage) createNewVegetableBtn.getScene().getWindow();
            mainStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/saladMaster/Administrator/CreateVegetableWindow/fxml/CreateVegetableWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createSaladBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) createSaladBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/saladMaster/Administrator/CreateSaladWindow/fxml/CreateSaladWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saladsListBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) saladsListBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/saladMaster/Administrator/ShowSaladsWindow/fxml/ShowSaladsWindow.fxml")));
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
