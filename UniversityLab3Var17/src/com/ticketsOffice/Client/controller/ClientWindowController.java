package com.ticketsOffice.Client.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import com.ticketsOffice.Administrator.CreateNewTicketWindow.entity.Ticket;
import com.ticketsOffice.controller.Controller;
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

public class ClientWindowController {

    @FXML
    private Button saveClientBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField clientFirstNameTextField;

    @FXML
    private TextField clientSecondNameTextField;

    @FXML
    private VBox VBoxLayout;

    @FXML
    private ChoiceBox<String> foodChoiceBox;

    @FXML
    private ChoiceBox<String> transportChoiceBox;

    @FXML
    private Label tNameLabel;

    @FXML
    private Label ticketDaysNumLabel;

    @FXML
    private Label ticketCostLabel;

    @FXML
    private Label statusLabel;

    private Controller controller = new Controller();

    private Ticket clientTicket = null;

    public void showExistedTickets() {
        if (controller.getTickets().size() != 0) {
            int i = 0;
            for (Ticket ticket : controller.getTickets()) {
                i++;
                HBox newHBox = new HBox();
                if (i % 2 == 1) {
                    newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
                }
                newHBox.setAlignment(Pos.CENTER);
                newHBox.setPrefHeight(100.0);
                newHBox.setPrefWidth(504.0);
                newHBox.setMaxWidth(504.0);
                newHBox.setMinHeight(100.0);
                newHBox.setMaxHeight(110.0);


                Label ticketNameLabel = new Label(ticket.getTicketName());
                ticketNameLabel.setFont(Font.font("Georgia", null, null, 15));
                ticketNameLabel.setTextAlignment(TextAlignment.CENTER);
                ticketNameLabel.setAlignment(Pos.CENTER);
                ticketNameLabel.setWrapText(true);
                ticketNameLabel.setPrefHeight(100.0);
                ticketNameLabel.setPrefWidth(195.0);
                newHBox.getChildren().add(ticketNameLabel);

                Label ticketDaysNum = new Label(Integer.toString(ticket.getDaysNum()));
                ticketDaysNum.setFont(Font.font("Georgia", null, null, 23));
                ticketDaysNum.setTextAlignment(TextAlignment.CENTER);
                ticketDaysNum.setAlignment(Pos.CENTER);
                ticketDaysNum.setWrapText(true);
                ticketDaysNum.setMinHeight(55.0);
                ticketDaysNum.setPrefWidth(112.0);
                newHBox.getChildren().add(ticketDaysNum);

                Label ticketCost = new Label(Float.toString(ticket.getTicketCost()));
                ticketCost.setFont(Font.font("Georgia", null, null, 23));
                ticketCost.setTextAlignment(TextAlignment.CENTER);
                ticketCost.setAlignment(Pos.CENTER);
                ticketCost.setWrapText(true);
                ticketCost.setMinHeight(55.0);
                ticketCost.setPrefWidth(105.0);
                newHBox.getChildren().add(ticketCost);

                Button chooseTicket = new Button("Выбрать");
                chooseTicket.setFont(Font.font("Georgia", null, null, 13));
                chooseTicket.setWrapText(true);
                chooseTicket.setMinHeight(40.0);
                chooseTicket.setPrefWidth(90.0);
                newHBox.getChildren().add(chooseTicket);
                chooseTicket.setOnAction(e -> {
                    clientTicket = ticket;
                    tNameLabel.setText(ticket.getTicketName());
                    ticketDaysNumLabel.setText(Integer.toString(ticket.getDaysNum()) + " дней.");
                    ticketCostLabel.setText(Float.toString(ticket.getTicketCost()) + " руб.");
                });
                VBoxLayout.getChildren().add(newHBox);
            }
        } else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Список путевок пуст!");}
    }


    @FXML
    void initialize() {
        foodChoiceBox.getItems().addAll("Русская", "Итальянская", "Узбекская");
        foodChoiceBox.setValue("Русская");
        transportChoiceBox.getItems().addAll("Самолет", "Поезд", "Автобус");
        transportChoiceBox.setValue("Самолет");

        showExistedTickets();

        saveClientBtn.setOnAction(event -> {
            if (!clientFirstNameTextField.getText().trim().equals("") &&
                !clientSecondNameTextField.getText().trim().equals("") && clientTicket != null){
                controller.createClient(clientFirstNameTextField.getText(), clientSecondNameTextField.getText(),
                                        clientTicket);

                clientFirstNameTextField.setText("");
                clientSecondNameTextField.setText("");
                tNameLabel.setText("");
                ticketDaysNumLabel.setText("");
                ticketCostLabel.setText("");
                clientTicket = null;
                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Клиент сохранен!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (clientFirstNameTextField.getText().trim().equals("") ||
                        clientSecondNameTextField.getText().trim().equals("")){
                    statusLabel.setText("Заполните все поля!");
                } else if (clientTicket == null){
                    statusLabel.setText("Выебрите путевку!");
                }
            }
        });


        loginBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) loginBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ticketsOffice/LogInWindow/fxml/loginWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Authorization");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
