package com.ticketsOffice.LogInWindow.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginWindowController {


    @FXML
    private Button setAdminBtn;

    @FXML
    private Button setClientBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize(){
        setAdminBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) setAdminBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/Administrator/MainWindow/fxml/adminMainWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        setClientBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) setClientBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/Client/fxml/newClientMainWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Client");
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
