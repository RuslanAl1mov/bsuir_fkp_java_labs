package com.ticketsOffice.Administrator.MainWindow.controller;

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
    private Button newTicketBtn;

    @FXML
    private Button showTicketsBtn;

    @FXML
    private Button showClientsBtn;

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
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/LogInWindow/fxml/loginWindow.fxml")));
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

        newTicketBtn.setOnAction(event -> {
            Stage mainStage = (Stage) newTicketBtn.getScene().getWindow();
            mainStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/Administrator/CreateNewTicketWindow/fxml/CreateNewTicketWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("New Ticket");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        showTicketsBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) showTicketsBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/Administrator/ShowTicketsWindow/fxml/ShowTicketsWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Show Tickets");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        showClientsBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) showClientsBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/Administrator/ShowClientsWindow/fxml/ShowClientsWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Show Clients");
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
