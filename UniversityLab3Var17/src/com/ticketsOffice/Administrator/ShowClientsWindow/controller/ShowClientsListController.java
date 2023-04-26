package com.ticketsOffice.Administrator.ShowClientsWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.ticketsOffice.Client.entity.Client;
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

public class ShowClientsListController {


    @FXML
    private VBox VBoxLayer;

    @FXML
    private Label statusLineLabel;

    @FXML
    private Button goToMainMenuBtn;

    private Controller controller = new Controller();

    public int containersUpdater() {
        int counter = 0;
        for (Client client: controller.getClients()) {
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }

            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(907.0);
            newHBox.setMaxWidth(907.0);

            Label label1 = new Label(client.getFirstName());
            label1.setFont(Font.font("Georgia", null, null, 23));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(220.0);
            newHBox.getChildren().add(label1);

            Label label2 = new Label(client.getSecondName());
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(100.0);
            label2.setPrefWidth(211.0);
            newHBox.getChildren().add(label2);

            Label label3 = new Label(client.getClientTicket().getTicketName());
            label3.setFont(Font.font("Georgia", null, null, 18));
            label3.setTextAlignment(TextAlignment.CENTER);
            label3.setAlignment(Pos.CENTER);
            label3.setWrapText(true);
            label3.setMinHeight(100.0);
            label3.setPrefWidth(211.0);
            newHBox.getChildren().add(label3);

            Label label4 = new Label(Integer.toString(client.getClientTicket().getDaysNum()));
            label4.setFont(Font.font("Georgia", null, null, 18));
            label4.setTextAlignment(TextAlignment.CENTER);
            label4.setAlignment(Pos.CENTER);
            label4.setWrapText(true);
            label4.setMinHeight(100.0);
            label4.setPrefWidth(146.0);
            newHBox.getChildren().add(label4);

            Label label5 = new Label(Float.toString(client.getClientTicket().getTicketCost()));
            label5.setFont(Font.font("Georgia", null, null, 18));
            label5.setTextAlignment(TextAlignment.CENTER);
            label5.setAlignment(Pos.CENTER);
            label5.setWrapText(true);
            label5.setMinHeight(100.0);
            label5.setPrefWidth(113.0);
            newHBox.getChildren().add(label5);

            VBoxLayer.getChildren().add(newHBox);
        }

        return counter;
    }

    @FXML
    void initialize() {
        if (containersUpdater()==0){
            statusLineLabel.setTextFill(Color.RED);
            statusLineLabel.setText("Список клиентов пуст!");
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        statusLineLabel.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        };

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
