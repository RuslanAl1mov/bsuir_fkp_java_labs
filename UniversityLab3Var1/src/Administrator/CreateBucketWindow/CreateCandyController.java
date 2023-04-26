package Administrator.CreateBucketWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Controller_pack.Controller;
import Controller_pack.Flower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CreateCandyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox ExistedFlowersVBoxLayer;

    @FXML
    private VBox bucketFlowersVBoxLayer;

    @FXML
    private Label newBucketCostLabel;

    @FXML
    private Label newBucketFlowersAmountLabel;

    @FXML
    private TextField newBucketNameTextField;

    @FXML
    private Button saveBucketBtn;

    @FXML
    private Label statusLabel;

    private Controller controller = new Controller();

    private List<Flower> newBucketsFlowers = new ArrayList<>();


    public void loadExistedFlowers() {
        int counter = 0;
        for (Flower flower: controller.getFlowers()){
            counter ++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }
            newHBox.setAlignment(Pos.CENTER);
            newHBox.setPrefHeight(100.0);
            newHBox.setPrefWidth(354.0);
            newHBox.setMaxWidth(354.0);
            newHBox.setMinHeight(100.0);

            Label flowerNameLabel = new Label(flower.getFlowerName());
            flowerNameLabel.setFont(Font.font("Georgia", null, null, 15));
            flowerNameLabel.setTextAlignment(TextAlignment.CENTER);
            flowerNameLabel.setAlignment(Pos.CENTER);
            flowerNameLabel.setWrapText(true);
            flowerNameLabel.setPrefHeight(100.0);
            flowerNameLabel.setPrefWidth(150.0);
            newHBox.getChildren().add(flowerNameLabel);

            Label flowersNumberLabel = new Label(Integer.toString(flower.getFlowerPrice()));
            flowersNumberLabel.setFont(Font.font("Georgia", null, null, 23));
            flowersNumberLabel.setTextAlignment(TextAlignment.CENTER);
            flowersNumberLabel.setAlignment(Pos.CENTER);
            flowersNumberLabel.setWrapText(true);
            flowersNumberLabel.setMinHeight(55.0);
            flowersNumberLabel.setPrefWidth(122.0);
            newHBox.getChildren().add(flowersNumberLabel);

            Button addFlowerToBucketBtn = new Button("Добавить");
            addFlowerToBucketBtn.setFont(Font.font("Georgia", null, null, 13));
            addFlowerToBucketBtn.setWrapText(true);
            addFlowerToBucketBtn.setMinHeight(40.0);
            addFlowerToBucketBtn.setPrefWidth(100.0);
            newHBox.getChildren().add(addFlowerToBucketBtn);
            addFlowerToBucketBtn.setOnAction(e -> {
                newBucketsFlowers.add(flower);
                int bucketPrice = 0;
                for (Flower fl: newBucketsFlowers){
                    bucketPrice = bucketPrice + fl.getFlowerPrice();
                }
                newBucketCostLabel.setText(String.valueOf(bucketPrice) + " руб.");
                newBucketFlowersAmountLabel.setText(String.valueOf(newBucketsFlowers.size()) + " шт.");
                addFlowerToBucketScroll(flower);
            });

            ExistedFlowersVBoxLayer.getChildren().add(newHBox);
        }
    }

    public void addFlowerToBucketScroll(Flower flower) {
        HBox newHBox = new HBox();
        if (newBucketsFlowers.size() % 2 == 1) {
            newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
        }
        newHBox.setAlignment(Pos.CENTER);
        newHBox.setPrefHeight(100.0);
        newHBox.setPrefWidth(354.0);
        newHBox.setMaxWidth(354.0);
        newHBox.setMinHeight(100.0);

        Label flowerNameLabel = new Label(flower.getFlowerName());
        flowerNameLabel.setFont(Font.font("Georgia", null, null, 15));
        flowerNameLabel.setTextAlignment(TextAlignment.CENTER);
        flowerNameLabel.setAlignment(Pos.CENTER);
        flowerNameLabel.setWrapText(true);
        flowerNameLabel.setPrefHeight(100.0);
        flowerNameLabel.setPrefWidth(150.0);
        newHBox.getChildren().add(flowerNameLabel);

        Label flowersNumberLabel = new Label(Integer.toString(flower.getFlowerPrice()));
        flowersNumberLabel.setFont(Font.font("Georgia", null, null, 23));
        flowersNumberLabel.setTextAlignment(TextAlignment.CENTER);
        flowersNumberLabel.setAlignment(Pos.CENTER);
        flowersNumberLabel.setWrapText(true);
        flowersNumberLabel.setMinHeight(55.0);
        flowersNumberLabel.setPrefWidth(115.0);
        newHBox.getChildren().add(flowersNumberLabel);

        Button removeFlowerFromBucketBtn = new Button("Удалить");
        removeFlowerFromBucketBtn.setFont(Font.font("Georgia", null, null, 13));
        removeFlowerFromBucketBtn.setWrapText(true);
        removeFlowerFromBucketBtn.setMinHeight(40.0);
        removeFlowerFromBucketBtn.setPrefWidth(100.0);
        newHBox.getChildren().add(removeFlowerFromBucketBtn);
        removeFlowerFromBucketBtn.setOnAction(e -> {
            newBucketsFlowers.remove(flower);
            int bucketPrice = 0;
            for (Flower fl: newBucketsFlowers){
                bucketPrice = bucketPrice + fl.getFlowerPrice();
            }
            newBucketCostLabel.setText(String.valueOf(bucketPrice) + " руб.");
            newBucketFlowersAmountLabel.setText(String.valueOf(newBucketsFlowers.size()) + " шт.");
            bucketFlowersVBoxLayer.getChildren().remove(newHBox);
        });

        bucketFlowersVBoxLayer.getChildren().add(newHBox);
    }


    @FXML
    void initialize() {
        loadExistedFlowers();

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

        saveBucketBtn.setOnAction(event -> {
            if (!newBucketNameTextField.getText().trim().equals("") && newBucketsFlowers.size() != 0){
                controller.createBucket(newBucketNameTextField.getText(), newBucketsFlowers);
                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Букет сохранен!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (newBucketNameTextField.getText().trim().equals("")) {
                    statusLabel.setText("Название нового букета не указано!");
                } else if (newBucketsFlowers.size() == 0) {
                    statusLabel.setText("В новом букете нет цветов!");
                }
            }

        });


    }
}
