package Administrator.CreateStoneWindow;

import java.io.IOException;

import Controller_pack.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateNewStoneController {

    @FXML
    private Button goToMainAdminMenuBtn;

    @FXML
    private TextField newStoneNameTextField;

    @FXML
    private TextField stoneColorTextField;

    @FXML
    private TextField stoneWeightTextField;

    @FXML
    private TextField stonePriceTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button saveStoneBtn;

    @FXML
    private MenuButton stoneTypeMenuBtn;

    @FXML
    void initialize() {
        goToMainAdminMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainAdminMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Administrator/MainWindow/fxml/adminMainWindow.fxml"));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveStoneBtn.setOnAction(event -> {
            String regex = "\\d+";


            if (stonePriceTextField.getText().replace(".", "").matches(regex) && stoneWeightTextField.getText().replace(".", "").matches(regex) &&
                !stoneColorTextField.getText().trim().equals("") && !newStoneNameTextField.getText().trim().equals("")) {

                try {
                    float stonePrice = Float.parseFloat(stonePriceTextField.getText().trim());
                    float stoneWeight = Float.parseFloat(stoneWeightTextField.getText().trim());

                    Controller controller = new Controller();
                    controller.createStone(newStoneNameTextField.getText(), stoneColorTextField.getText(),
                            stoneTypeMenuBtn.getText(), stonePrice, stoneWeight);

                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("Камень сохранен!");
                    newStoneNameTextField.setText("");
                    stoneColorTextField.setText("");
                    stoneWeightTextField.setText("");
                    stonePriceTextField.setText("");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Неверно заполнены поля!");
                }


            } else {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Неверно заполнены поля!");
            }


        });



    }
}
