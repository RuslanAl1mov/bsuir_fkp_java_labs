package Administrator.CreateFlowerWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller_pack.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateNewFlowerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label createFlowerWindowTitleLabel;

    @FXML
    private ImageView flowerImageFrame;

    @FXML
    private Button goToMaainAdminMenuBtn;

    @FXML
    private TextField newFlowerNameTextField;

    @FXML
    private Label newFlowerNameLabel;

    @FXML
    private TextField straightLengthTextField;

    @FXML
    private Label straightLengthLabel;

    @FXML
    private TextField freshTextField;

    @FXML
    private Label freshLabel;

    @FXML
    private TextField flowerPriceTextField;

    @FXML
    private Label flowerPriceLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Button saveFlowerBtn;

    @FXML
    void initialize() {
        goToMaainAdminMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMaainAdminMenuBtn.getScene().getWindow();
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

        saveFlowerBtn.setOnAction(event -> {
            String regex = "\\d+";


            if (flowerPriceTextField.getText().matches(regex) && straightLengthTextField.getText().matches(regex) &&
                freshTextField.getText().matches(regex) && !newFlowerNameTextField.getText().trim().equals("")) {

                try {
                    int flowerPrice = Integer.parseInt(flowerPriceTextField.getText().trim());
                    int flowerFresh = Integer.parseInt(freshTextField.getText().trim());
                    int flowerStemLength = Integer.parseInt(straightLengthTextField.getText().trim());

                    Controller controller = new Controller();
                    controller.createFlower(newFlowerNameTextField.getText(), flowerPrice, flowerFresh, flowerStemLength);

                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("Цветок сохранен!");
                    flowerPriceTextField.setText("");
                    straightLengthTextField.setText("");
                    freshTextField.setText("");
                    newFlowerNameTextField.setText("");

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
