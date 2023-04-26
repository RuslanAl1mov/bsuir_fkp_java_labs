package com.taxiBusiness.Administrator.ShowDriversWindow.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.taxiBusiness.Administrator.CreateNewCarWindow.entity.Car;
import com.taxiBusiness.Driver.entity.Driver;
import com.taxiBusiness.controller.Controller;
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

public class ShowDriversController {

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox VBoxLayer;

    @FXML
    private Label statusLineLabel;

    private Controller controller = new Controller();

    public int containersUpdater() {
        int counter = 0;
        for (Driver driver : controller.getDrivers()) {
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }

            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(607.0);

            Label label1 = new Label(driver.getFirstName());
            label1.setFont(Font.font("Georgia", null, null, 18));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(197.0);
            newHBox.getChildren().add(label1);

            Label label2 = new Label(driver.getSecondName());
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(100.0);
            label2.setPrefWidth(193.0);
            newHBox.getChildren().add(label2);

            Label label3 = new Label(driver.getApplication().getCarName());
            label3.setFont(Font.font("Georgia", null, null, 23));
            label3.setTextAlignment(TextAlignment.CENTER);
            label3.setAlignment(Pos.CENTER);
            label3.setWrapText(true);
            label3.setMinHeight(100.0);
            label3.setPrefWidth(125.0);
            newHBox.getChildren().add(label3);

            Label label4 = new Label(driver.getApplication().getStartAddress());
            label4.setFont(Font.font("Georgia", null, null, 23));
            label4.setTextAlignment(TextAlignment.CENTER);
            label4.setAlignment(Pos.CENTER);
            label4.setWrapText(true);
            label4.setMinHeight(100.0);
            label4.setPrefWidth(149.0);
            newHBox.getChildren().add(label4);

            Label label5 = new Label(driver.getApplication().getFinishAddress());
            label5.setFont(Font.font("Georgia", null, null, 23));
            label5.setTextAlignment(TextAlignment.CENTER);
            label5.setAlignment(Pos.CENTER);
            label5.setWrapText(true);
            label5.setMinHeight(100.0);
            label5.setPrefWidth(127.0);
            newHBox.getChildren().add(label5);

            if (driver.getApplication().getOnRepair()){
                Label labelVar1 = new Label("НА РЕМОНТЕ");
                labelVar1.setFont(Font.font("Georgia", null, null, 13));
                labelVar1.setTextAlignment(TextAlignment.CENTER);
                labelVar1.setAlignment(Pos.CENTER);
                labelVar1.setWrapText(true);
                labelVar1.setMinHeight(100.0);
                labelVar1.setPrefWidth(110.0);
                newHBox.getChildren().add(labelVar1);
            } else if (driver.getApplication().getFinished()){
                Label labelVar2 = new Label("ЗАЯВКА ЗАКРЫТА");
                labelVar2.setFont(Font.font("Georgia", null, null, 13));
                labelVar2.setTextAlignment(TextAlignment.CENTER);
                labelVar2.setAlignment(Pos.CENTER);
                labelVar2.setWrapText(true);
                labelVar2.setMinHeight(100.0);
                labelVar2.setPrefWidth(110.0);
                newHBox.getChildren().add(labelVar2);
            } else {
                Button chooseBtn = new Button("Отстранить");
                chooseBtn.setFont(Font.font("Georgia", null, null, 13));
                chooseBtn.setWrapText(true);
                chooseBtn.setMinHeight(100.0);
                chooseBtn.setPrefWidth(110.0);
                newHBox.getChildren().add(chooseBtn);
                chooseBtn.setOnAction(e -> {
                    driver.getApplication().setFinished(true);
                    controller.saveDriversInFile();


                    List<Car> cars = controller.getCars();
                    cars.add(driver.getApplication().getCar());
                    controller.setCars(cars);
                    controller.saveCarsInFile();

                    newHBox.getChildren().remove(chooseBtn);
                    Label labelVar3 = new Label("ЗАЯВКА ЗАКРЫТА");
                    labelVar3.setFont(Font.font("Georgia", null, null, 13));
                    labelVar3.setTextAlignment(TextAlignment.CENTER);
                    labelVar3.setAlignment(Pos.CENTER);
                    labelVar3.setWrapText(true);
                    labelVar3.setMinHeight(100.0);
                    labelVar3.setPrefWidth(110.0);
                    newHBox.getChildren().add(labelVar3);
                });
            }
            VBoxLayer.getChildren().add(newHBox);

        }
        return counter;
    }

    @FXML
    void initialize() {
        if (containersUpdater() == 0){
            statusLineLabel.setTextFill(Color.RED);
            statusLineLabel.setText("Список заявок пуст!");
        }


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
