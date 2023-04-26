package com.ticketsOffice.Administrator.CreateNewTicketWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.ticketsOffice.controller.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateNewTicketController {


    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField ticketNameTextField;

    @FXML
    private ChoiceBox<String> ticketTypeChoiceBox;

    @FXML
    private TextField daysNumberTextField;

    @FXML
    private TextField ticketCostTextField;

    @FXML
    private Button createTicketBtn;

    private Controller controller = new Controller();


    @FXML
    void initialize() {
        ticketTypeChoiceBox.getItems().addAll("Отдых", "Лечение", "Экскурсия");
        ticketTypeChoiceBox.setValue("Отдых");

        String regex = "\\d+";

        createTicketBtn.setOnAction(event -> {
            if (!ticketNameTextField.getText().trim().equals("") && !daysNumberTextField.getText().trim().equals("") &&
                daysNumberTextField.getText().matches(regex) &&
                ticketCostTextField.getText().replace(".", "").matches(regex)){

                controller.createTicket(ticketNameTextField.getText(), ticketTypeChoiceBox.getAccessibleText(),
                        Integer.parseInt(daysNumberTextField.getText()), Float.parseFloat(ticketCostTextField.getText()));
                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Cохранено!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (ticketNameTextField.getText().trim().equals("") ||
                    daysNumberTextField.getText().trim().equals("") ||
                    daysNumberTextField.getText().trim().equals("") ||
                    ticketCostTextField.getText().trim().equals("")) {

                statusLabel.setText("Заполните все ячейки!");
                } else if (!daysNumberTextField.getText().matches(regex)) {
                    statusLabel.setText("Кол-во дней должно быть указано в цифрах!");
                } else if (!ticketCostTextField.getText().matches(regex)) {
                    statusLabel.setText("Цена путевки должна быть указана в цифрах!");
                }
            }
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
