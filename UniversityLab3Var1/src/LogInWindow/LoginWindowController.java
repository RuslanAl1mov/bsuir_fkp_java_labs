package LogInWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane LogInVBox;

    @FXML
    private VBox LogInVbox;

    @FXML
    private URL location;

    @FXML
    private Button setAdminBtn;

    @FXML
    private Button setClientBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize(){
        setAdminBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) setAdminBtn.getScene().getWindow();
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

        exitBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) exitBtn.getScene().getWindow();
            LogInStage.close();
        });

    }
}
