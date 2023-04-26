package com.ticketsOffice.Administrator.ShowTicketsWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.ticketsOffice.Administrator.CreateNewTicketWindow.entity.Ticket;
import com.ticketsOffice.controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ShowTicketsListController {


    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private Button sortTicketsByPriceBtn;

    @FXML
    private VBox VBoxLayer;

    @FXML
    private Label statusLineLabel;

    @FXML
    private Button sortTicketsByDaysNumBtn;

    private Controller controller = new Controller();


    public int containersUpdater() {
        int counter = 0;
        for (Ticket ticket: controller.getTickets()) {
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }

            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(607.0);

            Label label1 = new Label(ticket.getTicketName());
            label1.setFont(Font.font("Georgia", null, null, 18));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(363.0);
            newHBox.getChildren().add(label1);

            Label label2 = new Label(Integer.toString(ticket.getDaysNum()));
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(100.0);
            label2.setPrefWidth(130.0);
            newHBox.getChildren().add(label2);

            Label label3 = new Label(Float.toString(ticket.getTicketCost()));
            label3.setFont(Font.font("Georgia", null, null, 23));
            label3.setTextAlignment(TextAlignment.CENTER);
            label3.setAlignment(Pos.CENTER);
            label3.setWrapText(true);
            label3.setMinHeight(100.0);
            label3.setPrefWidth(122.0);
            newHBox.getChildren().add(label3);

            VBoxLayer.getChildren().add(newHBox);
        }

        return counter;
    }

    @FXML
    void initialize() {
        containersUpdater();
        sortTicketsByPriceBtn.setOnAction(event -> {
            controller.sortTicketsByPrice();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statusLineLabel.setTextFill(Color.GREEN);
                statusLineLabel.setText("Обновлено!");
            } else {
                statusLineLabel.setTextFill(Color.RED);
                statusLineLabel.setText("Обновлять нечего!");
            }
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        statusLineLabel.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        sortTicketsByDaysNumBtn.setOnAction(event -> {
            controller.sortTicketsByDaysNum();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statusLineLabel.setTextFill(Color.GREEN);
                statusLineLabel.setText("Обновлено!");
            } else {
                statusLineLabel.setTextFill(Color.RED);
                statusLineLabel.setText("Обновлять нечего!");
            }
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        statusLineLabel.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });


        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
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
    }
}
