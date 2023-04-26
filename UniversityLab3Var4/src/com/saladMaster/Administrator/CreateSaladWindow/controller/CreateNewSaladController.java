package com.saladMaster.Administrator.CreateSaladWindow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.saladMaster.Administrator.CreateSaladWindow.entity.Salad;
import com.saladMaster.Administrator.CreateVegetableWindow.entity.Vegetable;
import com.saladMaster.Controller_pack.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CreateNewSaladController {

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox ExistedVegVBoxLayer;

    @FXML
    private VBox saladVegVBoxLayer;

    @FXML
    private Label newSaladKalNumLabel;

    @FXML
    private Label newSaladVagNumLabel;

    @FXML
    private TextField newSaladNameTextField;

    @FXML
    private Button saveSaladBtn;

    @FXML
    private Label statusLabel;

    private Controller controller = new Controller();

    private List<Vegetable> newSaladVegetables = new ArrayList<>();


    public int loadExistedVegetables() {
        int counter = 0;
        for (Vegetable vegetable: controller.getVegetables()){
            counter ++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }
            newHBox.setAlignment(Pos.CENTER);
            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(354.0);
            newHBox.setMaxWidth(354.0);
            newHBox.setMinHeight(100.0);

            Label label1 = new Label(vegetable.getVegetableName());
            label1.setFont(Font.font("Georgia", null, null, 15));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(150.0);
            newHBox.getChildren().add(label1);

            Label label2 = new Label(Integer.toString(vegetable.getKalNum()));
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(55.0);
            label2.setPrefWidth(122.0);
            newHBox.getChildren().add(label2);

            Button btn = new Button("Добавить");
            btn.setFont(Font.font("Georgia", null, null, 13));
            btn.setWrapText(true);
            btn.setMinHeight(40.0);
            btn.setPrefWidth(100.0);
            newHBox.getChildren().add(btn);
            btn.setOnAction(e -> {
                newSaladVegetables.add(vegetable);
                int vegKal = 0;
                for (Vegetable vg: newSaladVegetables){
                    vegKal = vegKal + vg.getKalNum();
                }
                newSaladKalNumLabel.setText(String.valueOf(vegKal) + " калл.");
                newSaladVagNumLabel.setText(String.valueOf(newSaladVegetables.size()) + " шт.");
                addVegetableToSaladScroll(vegetable);
            });

            ExistedVegVBoxLayer.getChildren().add(newHBox);
        }
        return counter;
    }

    public void addVegetableToSaladScroll(Vegetable vegetable) {
        HBox newHBox = new HBox();
        if (newSaladVegetables.size() % 2 == 1) {
            newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
        }
        newHBox.setAlignment(Pos.CENTER);
        newHBox.setPrefHeight(100.0);
        newHBox.setPrefWidth(354.0);
        newHBox.setMaxWidth(354.0);
        newHBox.setMinHeight(100.0);

        Label label1 = new Label(vegetable.getVegetableName());
        label1.setFont(Font.font("Georgia", null, null, 15));
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setAlignment(Pos.CENTER);
        label1.setWrapText(true);
        label1.setPrefHeight(100.0);
        label1.setPrefWidth(150.0);
        newHBox.getChildren().add(label1);

        Label label2 = new Label(Integer.toString(vegetable.getKalNum()));
        label2.setFont(Font.font("Georgia", null, null, 23));
        label2.setTextAlignment(TextAlignment.CENTER);
        label2.setAlignment(Pos.CENTER);
        label2.setWrapText(true);
        label2.setMinHeight(55.0);
        label2.setPrefWidth(115.0);
        newHBox.getChildren().add(label2);

        Button removeBtn = new Button("Удалить");
        removeBtn.setFont(Font.font("Georgia", null, null, 13));
        removeBtn.setWrapText(true);
        removeBtn.setMinHeight(40.0);
        removeBtn.setPrefWidth(100.0);
        newHBox.getChildren().add(removeBtn);
        removeBtn.setOnAction(e -> {
            newSaladVegetables.remove(vegetable);
            int vegKal = 0;
            for (Vegetable vg: newSaladVegetables){
                vegKal = vegKal + vg.getKalNum();
            }
            newSaladKalNumLabel.setText(String.valueOf(vegKal) + " калл.");
            newSaladVagNumLabel.setText(String.valueOf(newSaladVegetables.size()) + " шт.");
            saladVegVBoxLayer.getChildren().remove(newHBox);
        });

        saladVegVBoxLayer.getChildren().add(newHBox);
    }


    @FXML
    void initialize() {
        newSaladVagNumLabel.setText("0");
        newSaladKalNumLabel.setText("0");
        if (loadExistedVegetables() == 0){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Список овощей пуст!");
        }

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

        saveSaladBtn.setOnAction(event -> {
            if (!newSaladNameTextField.getText().trim().equals("") && newSaladVegetables.size() != 0){
                controller.createSalad(newSaladNameTextField.getText(), newSaladVegetables);
                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Салат сохранен!");

                newSaladNameTextField.setText("");
                saladVegVBoxLayer.getChildren().removeAll(saladVegVBoxLayer.getChildren());
                newSaladVegetables.clear();
                newSaladVagNumLabel.setText("0");
                newSaladKalNumLabel.setText("0");


            } else {
                statusLabel.setTextFill(Color.RED);
                if (newSaladNameTextField.getText().trim().equals("")) {
                    statusLabel.setText("Название нового салата не указано!");
                } else if (newSaladVegetables.size() == 0) {
                    statusLabel.setText("В новом Салате нет овощей!");
                }
            }
        });
    }
}
