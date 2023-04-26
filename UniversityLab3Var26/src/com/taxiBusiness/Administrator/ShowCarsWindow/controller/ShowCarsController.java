package com.taxiBusiness.Administrator.ShowCarsWindow.controller;

import java.io.IOException;
import java.util.Objects;

import com.taxiBusiness.Administrator.CreateNewCarWindow.entity.Car;
import com.taxiBusiness.controller.Controller;
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

public class ShowCarsController {


    @FXML
    private VBox VBoxLayer;

    @FXML
    private Label statusLabel;

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private Button sortByMileageBtn;

    private Controller controller = new Controller();

    public int containersUpdater() {
        int counter = 0;
        for (Car car : controller.getCars()) {
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }
            newHBox.setPrefHeight(100.0);
            newHBox.setMaxWidth(480.0);

            Label label1 = new Label(car.getMark());
            label1.setFont(Font.font("Georgia", null, null, 20));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(309.0);
            newHBox.getChildren().add(label1);

            Label label2 = new Label(Integer.toString(car.getMileage()));
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(100.0);
            label2.setPrefWidth(170.0);
            newHBox.getChildren().add(label2);

            VBoxLayer.getChildren().add(newHBox);
        }
        return counter;
    }

    @FXML
    void initialize() {
        if (containersUpdater()==0){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Список машин пуст!");
        }

        sortByMileageBtn.setOnAction(event -> {
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            controller.sortCarsByMileage();
            containersUpdater();
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Обновлено!");
        });

        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/taxiBusiness/Administrator/MainWindow/fxml/adminMainWindow.fxml")));
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
