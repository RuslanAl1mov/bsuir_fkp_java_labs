package com.taxiBusiness.LogInWindow.controller;

import com.taxiBusiness.Driver.controller.DriverWindowController;
import com.taxiBusiness.Driver.entity.Driver;
import com.taxiBusiness.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginWindowController {

    @FXML
    private Button setAdminBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label statusLabel;

    @FXML
    private VBox VBoxLayer;

    public Controller controller = new Controller();


    private int updateList(){
        int counter = 0;
        for (Driver driver: controller.getDrivers()) {
            if (!driver.getApplication().getFinished()) {
                counter++;
                Button chooseBtn = new Button("Водитель: " + driver.getFirstName());
                chooseBtn.setFont(Font.font("Georgia", null, null, 23));
                chooseBtn.setWrapText(true);
                chooseBtn.setMinWidth(382.0);
                chooseBtn.setMaxWidth(382.0);
                chooseBtn.setMaxHeight(60.0);
                chooseBtn.setMinHeight(60.0);
                VBoxLayer.getChildren().add(chooseBtn);
                chooseBtn.setOnAction(event -> {
                    try {
                        Stage LogInStage = (Stage) chooseBtn.getScene().getWindow();
                        LogInStage.close();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/taxiBusiness/Driver/fxml/DriverMainWindow.fxml"));
                        DriverWindowController driverWindowController = new DriverWindowController(controller.getDrivers().indexOf(driver));
                        loader.setController(driverWindowController);
                        Stage newStage = new Stage();
                        newStage.setTitle("Driver");
                        newStage.setScene(new Scene((Parent) loader.load()));
                        newStage.show();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
            }
        }
        return counter;
    }


    @FXML
    void initialize(){
        if (updateList() == 0){
            statusLabel.setText("Водителей нет");
        }
        setAdminBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) setAdminBtn.getScene().getWindow();
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

        exitBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) exitBtn.getScene().getWindow();
            LogInStage.close();
        });

    }
}
