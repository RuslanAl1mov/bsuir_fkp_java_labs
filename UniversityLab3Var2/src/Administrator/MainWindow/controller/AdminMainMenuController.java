package Administrator.MainWindow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdminMainMenuController {

    @FXML
    private Button createNewCandyBtn;

    @FXML
    private Button createPresentBtn;

    @FXML
    private Button candiesListBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button presentsListBtn;

    @FXML
    void initialize() {
        createNewCandyBtn.setOnAction(event -> {
            Stage mainStage = (Stage) createNewCandyBtn.getScene().getWindow();
            mainStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/CreateCandyWindow/fxml/CreateCandyWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("createNewCandyBtn");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createPresentBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) createPresentBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/CreatePresentWindow/fxml/CreatePresentWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("createPresentBtn");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        candiesListBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) candiesListBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/ShowCandiesWindow/fxml/ShowCandiesWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("candiesListBtn");
                primaryStage.setScene(new Scene(root2));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        presentsListBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) presentsListBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/ShowPresentsWindow/fxml/ShowPresentsWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("presentsListBtn");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
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
