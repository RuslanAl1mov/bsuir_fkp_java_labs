package Administrator.MainWindow;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainMenuController {


    @FXML
    private Button createNewStoneBtn;

    @FXML
    private Button createNecklaceBtn;

    @FXML
    private Button necklacesListBtn;

    @FXML
    private Button exitBtn;


    @FXML
    void initialize() {

        createNewStoneBtn.setOnAction(event -> {
            Stage mainStage = (Stage) createNewStoneBtn.getScene().getWindow();
            mainStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/CreateStoneWindow/fxml/createStoneWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createNecklaceBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) createNecklaceBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/CreateNecklaceWindow/fxml/CreateNecklaceWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        necklacesListBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) necklacesListBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/ShowNecklacesWindow/fxml/ShowNecklacesWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root2));
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
