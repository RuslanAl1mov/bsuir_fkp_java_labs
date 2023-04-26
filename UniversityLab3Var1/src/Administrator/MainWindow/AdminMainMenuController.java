package Administrator.MainWindow;

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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminWindowTitleLabel;

    @FXML
    private Button createNewFlowerBtn;

    @FXML
    private Button createBucketBtn;

    @FXML
    private Button bucketsListBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private ImageView flowerImageFrame;

    @FXML
    private Button loginBtn;

    @FXML
    private Label adminMenuTitleLabel;

    @FXML
    void initialize() {
        loginBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) loginBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/LogInWindow/fxml/loginWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root, 640, 440));
                primaryStage.setMaxWidth(640);
                primaryStage.setMaxHeight(440);
                primaryStage.setMinWidth(640);
                primaryStage.setMinHeight(440);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createNewFlowerBtn.setOnAction(event -> {
            Stage mainStage = (Stage) createNewFlowerBtn.getScene().getWindow();
            mainStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/CreateFlowerWindow/fxml/createFlowerWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createBucketBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) createBucketBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/CreateBucketWindow/fxml/CreateCandyWindow.fxml")));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Authorization");
                primaryStage.setScene(new Scene(root1));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bucketsListBtn.setOnAction(event -> {
            Stage mainStage1 = (Stage) bucketsListBtn.getScene().getWindow();
            mainStage1.close();
            try {
                Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/ShowBucketsWindow/fxml/ShowBucketsWindow.fxml")));
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
