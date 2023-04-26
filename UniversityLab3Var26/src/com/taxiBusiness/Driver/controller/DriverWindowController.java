package com.taxiBusiness.Driver.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.taxiBusiness.Administrator.CreateNewCarWindow.entity.Car;
import com.taxiBusiness.Driver.entity.Driver;
import com.taxiBusiness.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DriverWindowController {
    private Controller controller = new Controller();

    private int driverID;
    private Driver driver;


    @FXML
    private Button loginBtn;

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    @FXML
    private Label driverStatusLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label driverFirstNameLabel;

    @FXML
    private Label driverSecondNameLabel;

    @FXML
    private Label carNameLabel;

    @FXML
    private Button sendToRepairBtn;

    @FXML
    private Button finishApplicationBtn;

    public DriverWindowController(int driverID){
        this.driverID = driverID;
        this.driver = controller.getDrivers().get(driverID);
    }


    @FXML
    void initialize() {
        driverFirstNameLabel.setText(driver.getFirstName());
        driverSecondNameLabel.setText(driver.getSecondName());

        carNameLabel.setText(driver.getApplication().getCarName());

        fromLabel.setText(driver.getApplication().getStartAddress());
        toLabel.setText(driver.getApplication().getFinishAddress());

        if (driver.getApplication().getFinished()){
            finishApplicationBtn.setDisable(true);
            sendToRepairBtn.setDisable(true);
            driverStatusLabel.setTextFill(Color.RED);
            driverStatusLabel.setText("ЗАЯВКА ЗАКРЫТА!");
        } else if (driver.getApplication().getOnRepair()){
            finishApplicationBtn.setDisable(true);
            driverStatusLabel.setTextFill(Color.PURPLE);
            driverStatusLabel.setText("Машина на ремонте!");
            sendToRepairBtn.setText("Завершить ремонт");
        } else {
            driverStatusLabel.setTextFill(Color.GREEN);
            driverStatusLabel.setText("Машина на вызове!");}

        sendToRepairBtn.setOnAction(event -> {
            if (!driver.getApplication().getOnRepair()) {
                driver.getApplication().setOnRepair(true);
                driverStatusLabel.setTextFill(Color.PURPLE);
                driverStatusLabel.setText("Машина на ремонте!");
                finishApplicationBtn.setDisable(true);
                sendToRepairBtn.setText("Завершить ремонт");
                controller.saveDriversInFile();
            } else if (driver.getApplication().getOnRepair()) {
                driver.getApplication().setOnRepair(false);
                driverStatusLabel.setTextFill(Color.GREEN);
                driverStatusLabel.setText("Машина на вызове!");
                finishApplicationBtn.setDisable(false);
                sendToRepairBtn.setText("Отправить на ремонт");
                controller.saveDriversInFile();
            }
        });

        finishApplicationBtn.setOnAction(event -> {
            driver.getApplication().setFinished(true);
            controller.saveDriversInFile();


            List<Car> cars = controller.getCars();
            cars.add(driver.getApplication().getCar());
            controller.setCars(cars);
            controller.saveCarsInFile();

            driverStatusLabel.setTextFill(Color.RED);
            driverStatusLabel.setText("ЗАЯВКА ЗАКРЫТА!");
            finishApplicationBtn.setDisable(true);
            sendToRepairBtn.setDisable(true);
        });



        loginBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) loginBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/taxiBusiness/LogInWindow/fxml/loginWindow.fxml")));
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
