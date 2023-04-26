package Administrator.ShowBucketsWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller_pack.Flower;
import Controller_pack.Bucket;
import Controller_pack.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AdminShowBucketsList {


    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private Button sortBucketByPriceBtn;

    @FXML
    private VBox VBoxLayer;


    @FXML
    private Label statusLineLabel;

    private Controller controller = new Controller();

    public int containersUpdater() {
        int counter = 0;
        for (Bucket bucket: controller.getBuckets()) {
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }

            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(507.0);

            Label bucketNameLabel = new Label(bucket.getBucketName());
            bucketNameLabel.setFont(Font.font("Georgia", null, null, 18));
            bucketNameLabel.setTextAlignment(TextAlignment.CENTER);
            bucketNameLabel.setAlignment(Pos.CENTER);
            bucketNameLabel.setWrapText(true);
            bucketNameLabel.setPrefHeight(100.0);
            bucketNameLabel.setPrefWidth(235.0);
            newHBox.getChildren().add(bucketNameLabel);

            int flowers_num = bucket.getFlowers().size();
            Label bucketFlowersNumberLabel = new Label(Integer.toString(flowers_num));
            bucketFlowersNumberLabel.setFont(Font.font("Georgia", null, null, 23));
            bucketFlowersNumberLabel.setTextAlignment(TextAlignment.CENTER);
            bucketFlowersNumberLabel.setAlignment(Pos.CENTER);
            bucketFlowersNumberLabel.setWrapText(true);
            bucketFlowersNumberLabel.setMinHeight(100.0);
            bucketFlowersNumberLabel.setPrefWidth(145.0);
            newHBox.getChildren().add(bucketFlowersNumberLabel);

            Label bucketCostLabel = new Label(Integer.toString(bucket.getBucketPrice()));
            bucketCostLabel.setFont(Font.font("Georgia", null, null, 23));
            bucketCostLabel.setTextAlignment(TextAlignment.CENTER);
            bucketCostLabel.setAlignment(Pos.CENTER);
            bucketCostLabel.setWrapText(true);
            bucketCostLabel.setMinHeight(100.0);
            bucketCostLabel.setPrefWidth(145.0);
            newHBox.getChildren().add(bucketCostLabel);

            VBoxLayer.getChildren().add(newHBox);
        }

        return counter;
    }

    @FXML
    void initialize() {
        containersUpdater();
        sortBucketByPriceBtn.setOnAction(event -> {
            controller.sortBucketsByPrice();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statusLineLabel.setTextFill(Color.GREEN);
                statusLineLabel.setText("Обновлено!");
            } else {
                statusLineLabel.setTextFill(Color.RED);
                statusLineLabel.setText("Обновлять нечего!");
            }
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        statusLineLabel.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });


        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
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
    }
}
