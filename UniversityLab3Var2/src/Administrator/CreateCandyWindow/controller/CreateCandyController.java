package Administrator.CreateCandyWindow.controller;

import java.io.IOException;
import java.util.Objects;

import Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateCandyController {

    @FXML
    private Button goToMainAdminMenuBtn;

    @FXML
    private TextField candyNameTextField;

    @FXML
    private TextField candySugarTextField;

    @FXML
    private TextField candyWeightTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button saveCandyBtn;

    @FXML
    void initialize() {
        goToMainAdminMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainAdminMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/MainWindow/fxml/adminMainWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveCandyBtn.setOnAction(event -> {
            String regex = "\\d+";


            if (!candyNameTextField.getText().trim().equals("") && !candySugarTextField.getText().trim().equals("") &&
                candySugarTextField.getText().matches(regex) && !candyWeightTextField.getText().trim().equals("") &&
                candyWeightTextField.getText().matches(regex)) {

                try {
                    Controller controller = new Controller();
                    controller.createCandy(candyNameTextField.getText(), Integer.parseInt(candySugarTextField.getText().trim()),
                            Integer.parseInt(candyWeightTextField.getText().trim()));

                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("Конфета сохранена!");
                    candyNameTextField.setText("");
                    candySugarTextField.setText("");
                    candyWeightTextField.setText("");

                } catch (Exception e) {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Неверно заполнены поля!");
                }

            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Неверно заполнены поля!");
            }


        });



    }
}
