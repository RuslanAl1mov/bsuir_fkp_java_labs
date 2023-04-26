package com.saladMaster.Administrator.ShowSaladsWindow.controller;

import java.io.IOException;

import com.saladMaster.Administrator.CreateSaladWindow.entity.Salad;
import com.saladMaster.Administrator.CreateVegetableWindow.entity.Vegetable;
import com.saladMaster.Controller_pack.Controller;
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

public class ShowSaladsController {


    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private Button sortSaladsByKalBtn;

    @FXML
    private VBox VBoxLayer;

    @FXML
    private Label statLabel;

    @FXML
    private Button sortSaladsByVegNumBtn;

    private Controller controller = new Controller();

    public int containersUpdater() {
        int counter = 0;
        for (Salad salad: controller.getSalads()) {
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }

            newHBox.setPrefHeight(100.0);
            newHBox.setMaxWidth(595.0);

            Label label1 = new Label(salad.getSaladName());
            label1.setFont(Font.font("Georgia", null, null, 18));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(228.0);
            newHBox.getChildren().add(label1);

            int vegetablesNum = salad.getSaladVegetables().size();
            Label label2 = new Label(Integer.toString(vegetablesNum));
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(100.0);
            label2.setPrefWidth(139.0);
            newHBox.getChildren().add(label2);

            Label label3 = new Label(Integer.toString(salad.getKalNum()));
            label3.setFont(Font.font("Georgia", null, null, 23));
            label3.setTextAlignment(TextAlignment.CENTER);
            label3.setAlignment(Pos.CENTER);
            label3.setWrapText(true);
            label3.setMinHeight(100.0);
            label3.setPrefWidth(136.0);
            newHBox.getChildren().add(label3);

            Button btn = new Button("Удалить");
            btn.setFont(Font.font("Georgia", null, null, 13));
            btn.setWrapText(true);
            btn.setMinHeight(40.0);
            btn.setPrefWidth(100.0);
            newHBox.getChildren().add(btn);
            btn.setOnAction(e -> {
                controller.removeSalad(salad);
                VBoxLayer.getChildren().remove(newHBox);
            });

            VBoxLayer.getChildren().add(newHBox);
        }
        return counter;
    }

    @FXML
    void initialize() {
        if (containersUpdater() == 0){
            statLabel.setTextFill(Color.RED);
            statLabel.setText("У вас нет салатов!");
        }

        sortSaladsByKalBtn.setOnAction(event -> {
            controller.sortSaladsByKal();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statLabel.setTextFill(Color.GREEN);
                statLabel.setText("Обновлено!");
            } else {
                statLabel.setTextFill(Color.RED);
                statLabel.setText("Обновлять нечего!");
            }
        });

        sortSaladsByVegNumBtn.setOnAction(event -> {
            controller.sortSaladsByVegNum();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statLabel.setTextFill(Color.GREEN);
                statLabel.setText("Обновлено!");
            } else {
                statLabel.setTextFill(Color.RED);
                statLabel.setText("Обновлять нечего!");
            }
        });


        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/saladMaster/Administrator/MainWindow/fxml/AdminMainWindow.fxml"));
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
