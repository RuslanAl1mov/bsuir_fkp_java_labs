package Runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Runner extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/MainWindow/fxml/adminMainWindow.fxml")));  // /LogInWindow/fxml/loginWindow.fxml
        Stage newStage = new Stage();
        newStage.setTitle("Administrator");
        newStage.setScene(new Scene(root));
        newStage.show();

    }

    public static void main(String[] args) {launch(args);}
}
