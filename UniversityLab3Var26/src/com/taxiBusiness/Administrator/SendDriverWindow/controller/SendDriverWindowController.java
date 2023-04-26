package com.taxiBusiness.Administrator.SendDriverWindow.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.taxiBusiness.Administrator.CreateNewCarWindow.entity.Car;
import com.taxiBusiness.Administrator.SendDriverWindow.entity.Application;
import com.taxiBusiness.controller.Controller;
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

public class SendDriverWindowController {

    @FXML
    private Button sendDriverBtn;

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private TextField driverFirstNameTextField;

    @FXML
    private TextField driverSecondNameTextField;

    @FXML
    private VBox VBoxLayout;

    @FXML
    private Label carNameLabel;

    @FXML
    private Label carMileageNumLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField addrFromTextField;

    @FXML
    private TextField addrToTextField;


    private Controller controller = new Controller();

    private Car selectedCar = null;

    public void showExistedCars() {
        if (controller.getCars().size() != 0) {
            int i = 0;
            for (Car car : controller.getCars()) {
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


                Label label1 = new Label(car.getMark());
                label1.setFont(Font.font("Georgia", null, null, 15));
                label1.setTextAlignment(TextAlignment.CENTER);
                label1.setAlignment(Pos.CENTER);
                label1.setWrapText(true);
                label1.setPrefHeight(100.0);
                label1.setPrefWidth(283.0);
                newHBox.getChildren().add(label1);

                Label label2 = new Label(Integer.toString(car.getMileage()));
                label2.setFont(Font.font("Georgia", null, null, 23));
                label2.setTextAlignment(TextAlignment.CENTER);
                label2.setAlignment(Pos.CENTER);
                label2.setWrapText(true);
                label2.setMinHeight(55.0);
                label2.setPrefWidth(110.0);
                newHBox.getChildren().add(label2);

                Button chooseBtn = new Button("Выбрать");
                chooseBtn.setFont(Font.font("Georgia", null, null, 13));
                chooseBtn.setWrapText(true);
                chooseBtn.setMinHeight(40.0);
                chooseBtn.setPrefWidth(110.0);
                newHBox.getChildren().add(chooseBtn);
                chooseBtn.setOnAction(e -> {
                    selectedCar = car;
                    carNameLabel.setText(car.getMark());
                    carMileageNumLabel.setText(Integer.toString(car.getMileage()) + " км.");
                });
                VBoxLayout.getChildren().add(newHBox);
            }
        } else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Список машин пуст!");}
    }


    @FXML
    void initialize() {
        showExistedCars();

        sendDriverBtn.setOnAction(event -> {
            if (!driverFirstNameTextField.getText().trim().equals("") &&
                !driverSecondNameTextField.getText().trim().equals("") &&
                !addrFromTextField.getText().trim().equals("")  &&
                !addrToTextField.getText().trim().equals("") && selectedCar != null){

                Application newApp = new Application(addrFromTextField.getText(), addrToTextField.getText(), selectedCar);
                controller.createDriver(driverFirstNameTextField.getText(), driverSecondNameTextField.getText(), newApp);
                List<Car> cars = controller.getCars();
                cars.remove(selectedCar);
                controller.setCars(cars);
                controller.saveCarsInFile();
                VBoxLayout.getChildren().removeAll(VBoxLayout.getChildren());
                showExistedCars();

                driverFirstNameTextField.setText("");
                driverSecondNameTextField.setText("");
                addrFromTextField.setText("");
                addrToTextField.setText("");
                carNameLabel.setText("");
                carMileageNumLabel.setText("");
                selectedCar = null;

                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Водитель отправлен!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (driverFirstNameTextField.getText().trim().equals("") ||
                    driverSecondNameTextField.getText().trim().equals("") ||
                    addrFromTextField.getText().trim().equals("") ||
                    addrToTextField.getText().trim().equals("")) {

                    statusLabel.setText("Заполните все поля!");

                } else if (selectedCar == null){
                    statusLabel.setText("Выебрите машину!");
                }
            }
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
