package Administrator.ShowNecklacesWindow;

import java.io.IOException;

import Controller_pack.Necklaces.Necklace;
import Controller_pack.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AdminShowNecklacesList {


    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private Button sortNecklacesByPriceBtn;

    @FXML
    private VBox VBoxLayer;

    @FXML
    private Label statusLineLabel;

    @FXML
    private Button sortNecklacesByWeightBtn;

    public Controller controller = new Controller();

    public int containersUpdater() {
        int counter = 0;
        for (int i=0; i<controller.getNecklaces().size(); i++) {
            Necklace necklace = controller.getNecklaces().get(i);
            counter++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }

            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(508.0);

            Label necklaceNameLabel = new Label(necklace.getName());
            necklaceNameLabel.setFont(Font.font("Georgia", null, null, 18));
            necklaceNameLabel.setTextAlignment(TextAlignment.CENTER);
            necklaceNameLabel.setAlignment(Pos.CENTER);
            necklaceNameLabel.setWrapText(true);
            necklaceNameLabel.setPrefHeight(100.0);
            necklaceNameLabel.setPrefWidth(198.0);
            newHBox.getChildren().add(necklaceNameLabel);

            int stonesNum = necklace.getNecklaceStones().size();
            Label bucketFlowersNumberLabel = new Label(Integer.toString(stonesNum));
            bucketFlowersNumberLabel.setFont(Font.font("Georgia", null, null, 20));
            bucketFlowersNumberLabel.setTextAlignment(TextAlignment.CENTER);
            bucketFlowersNumberLabel.setAlignment(Pos.CENTER);
            bucketFlowersNumberLabel.setWrapText(true);
            bucketFlowersNumberLabel.setMinHeight(100.0);
            bucketFlowersNumberLabel.setPrefWidth(129.0);
            newHBox.getChildren().add(bucketFlowersNumberLabel);

            Label necklaceWeightLabel = new Label(Float.toString(necklace.getNecklaceWeight()));
            necklaceWeightLabel.setFont(Font.font("Georgia", null, null, 20));
            necklaceWeightLabel.setTextAlignment(TextAlignment.CENTER);
            necklaceWeightLabel.setAlignment(Pos.CENTER);
            necklaceWeightLabel.setWrapText(true);
            necklaceWeightLabel.setMinHeight(100.0);
            necklaceWeightLabel.setPrefWidth(102.0);
            newHBox.getChildren().add(necklaceWeightLabel);

            Label necklaceCostLabel = new Label(Float.toString(necklace.getNecklacePrice()));
            necklaceCostLabel.setFont(Font.font("Georgia", null, null, 20));
            necklaceCostLabel.setTextAlignment(TextAlignment.CENTER);
            necklaceCostLabel.setAlignment(Pos.CENTER);
            necklaceCostLabel.setWrapText(true);
            necklaceCostLabel.setMinHeight(100.0);
            necklaceCostLabel.setPrefWidth(89.0);
            newHBox.getChildren().add(necklaceCostLabel);

            VBoxLayer.getChildren().add(newHBox);
        }

        return counter;
    }

    @FXML
    void initialize() {
        containersUpdater();
        sortNecklacesByPriceBtn.setOnAction(event -> {
            controller.sortNecklacesByPrice();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statusLineLabel.setTextFill(Color.GREEN);
                statusLineLabel.setText("Обновлено по стоимости!");
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


        sortNecklacesByWeightBtn.setOnAction(event -> {
            controller.sortNecklacesByWeight();
            VBoxLayer.getChildren().removeAll(VBoxLayer.getChildren());
            int bucketsNumber = containersUpdater();
            if (bucketsNumber != 0){
                statusLineLabel.setTextFill(Color.GREEN);
                statusLineLabel.setText("Обновлено по весу!");
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
