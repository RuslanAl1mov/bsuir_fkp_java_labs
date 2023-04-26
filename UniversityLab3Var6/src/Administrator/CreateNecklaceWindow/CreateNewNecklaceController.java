package Administrator.CreateNecklaceWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller_pack.Controller;
import Controller_pack.Stones.Stone;
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

public class CreateNewNecklaceController {

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox createdStonesVBoxLayer;

    @FXML
    private VBox newNecklaceVBoxLayer;

    @FXML
    private Label newNecklaceCostLabel;

    @FXML
    private Label newNecklaceStonesAmountLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveNecklaceBtn;

    @FXML
    private Label statusLabel;


    private Controller controller = new Controller();

    private List<Stone> newNecklaceStones = new ArrayList<>();


    public void loadExistedStones() {
        int counter = 0;
        for (Stone stone : controller.getStones()){
            counter ++;
            HBox newHBox = new HBox();
            if (counter % 2 == 1) {
                newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
            }
            newHBox.setAlignment(Pos.CENTER);
            newHBox.setPrefHeight(100.0);
            newHBox.setMaxWidth(440.0);
            newHBox.setMinHeight(100.0);

            Label stoneNameLabel = new Label(stone.getName());
            stoneNameLabel.setFont(Font.font("Georgia", null, null, 15));
            stoneNameLabel.setTextAlignment(TextAlignment.CENTER);
            stoneNameLabel.setAlignment(Pos.CENTER);
            stoneNameLabel.setWrapText(true);
            stoneNameLabel.setPrefHeight(100.0);
            stoneNameLabel.setPrefWidth(152.0);
            newHBox.getChildren().add(stoneNameLabel);

            Label stoneWeightLabel = new Label(Float.toString(stone.getWeight()));
            stoneWeightLabel.setFont(Font.font("Georgia", null, null, 23));
            stoneWeightLabel.setTextAlignment(TextAlignment.CENTER);
            stoneWeightLabel.setAlignment(Pos.CENTER);
            stoneWeightLabel.setWrapText(true);
            stoneWeightLabel.setMinHeight(55.0);
            stoneWeightLabel.setPrefWidth(100.0);
            newHBox.getChildren().add(stoneWeightLabel);

            Label stonePriceLabel = new Label(Float.toString(stone.getPrice()));
            stonePriceLabel.setFont(Font.font("Georgia", null, null, 23));
            stonePriceLabel.setTextAlignment(TextAlignment.CENTER);
            stonePriceLabel.setAlignment(Pos.CENTER);
            stonePriceLabel.setWrapText(true);
            stoneWeightLabel.setMinHeight(55.0);
            stonePriceLabel.setPrefWidth(98.0);
            newHBox.getChildren().add(stonePriceLabel);

            Button addStoneToNecklaceBtn = new Button("Добавить");
            addStoneToNecklaceBtn.setFont(Font.font("Georgia", null, null, 13));
            addStoneToNecklaceBtn.setWrapText(true);
            addStoneToNecklaceBtn.setMinHeight(40.0);
            addStoneToNecklaceBtn.setPrefWidth(91.0);
            newHBox.getChildren().add(addStoneToNecklaceBtn);
            addStoneToNecklaceBtn.setOnAction(e -> {
                newNecklaceStones.add(stone);
                float neclackePrice = 0;
                for (Stone fl: newNecklaceStones){
                    neclackePrice = neclackePrice + fl.getPrice();
                }
                newNecklaceCostLabel.setText(String.valueOf(neclackePrice) + " руб.");
                newNecklaceStonesAmountLabel.setText(String.valueOf(newNecklaceStones.size()) + " шт.");
                addFlowerToNecklaceScroll(stone);
            });

            createdStonesVBoxLayer.getChildren().add(newHBox);
        }
    }

    public void addFlowerToNecklaceScroll(Stone stone) {
        HBox newHBox = new HBox();
        if (newNecklaceStones.size() % 2 == 1) {
            newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
        }
        newHBox.setAlignment(Pos.CENTER);
        newHBox.setPrefHeight(100.0);
        newHBox.setMaxWidth(440.0);
        newHBox.setMinHeight(100.0);

        Label stoneNameLabel = new Label(stone.getName());
        stoneNameLabel.setFont(Font.font("Georgia", null, null, 15));
        stoneNameLabel.setTextAlignment(TextAlignment.CENTER);
        stoneNameLabel.setAlignment(Pos.CENTER);
        stoneNameLabel.setWrapText(true);
        stoneNameLabel.setPrefHeight(100.0);
        stoneNameLabel.setPrefWidth(152.0);
        newHBox.getChildren().add(stoneNameLabel);

        Label stoneWeightLabel = new Label(Float.toString(stone.getWeight()));
        stoneWeightLabel.setFont(Font.font("Georgia", null, null, 20));
        stoneWeightLabel.setTextAlignment(TextAlignment.CENTER);
        stoneWeightLabel.setAlignment(Pos.CENTER);
        stoneWeightLabel.setWrapText(true);
        stoneWeightLabel.setMinHeight(55.0);
        stoneWeightLabel.setPrefWidth(100.0);
        newHBox.getChildren().add(stoneWeightLabel);

        Label stonePriceLabel = new Label(Float.toString(stone.getPrice()));
        stonePriceLabel.setFont(Font.font("Georgia", null, null, 20));
        stonePriceLabel.setTextAlignment(TextAlignment.CENTER);
        stonePriceLabel.setAlignment(Pos.CENTER);
        stonePriceLabel.setWrapText(true);
        stoneWeightLabel.setMinHeight(55.0);
        stonePriceLabel.setPrefWidth(98.0);
        newHBox.getChildren().add(stonePriceLabel);

        Button removeStoneFromNecklaceBtn = new Button("Удалить");
        removeStoneFromNecklaceBtn.setFont(Font.font("Georgia", null, null, 13));
        removeStoneFromNecklaceBtn.setWrapText(true);
        removeStoneFromNecklaceBtn.setMinHeight(40.0);
        removeStoneFromNecklaceBtn.setPrefWidth(91.0);
        newHBox.getChildren().add(removeStoneFromNecklaceBtn);
        removeStoneFromNecklaceBtn.setOnAction(e -> {
            newNecklaceStones.remove(stone);
            float neclackePrice = 0;
            for (Stone fl: newNecklaceStones){
                neclackePrice = neclackePrice + fl.getPrice();
            }
            newNecklaceCostLabel.setText(String.valueOf(neclackePrice) + " руб.");
            newNecklaceStonesAmountLabel.setText(String.valueOf(newNecklaceStones.size()) + " шт.");
            newNecklaceVBoxLayer.getChildren().remove(newHBox);
        });

        newNecklaceVBoxLayer.getChildren().add(newHBox);
    }


    @FXML
    void initialize() {
        loadExistedStones();

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

        saveNecklaceBtn.setOnAction(event -> {
            if (!nameTextField.getText().trim().equals("") && newNecklaceStones.size() != 0){
                controller.createNecklace(nameTextField.getText(), newNecklaceStones);
                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Ожерелье сохранен!");
            } else {
                statusLabel.setTextFill(Color.RED);
                if (nameTextField.getText().trim().equals("")) {
                    statusLabel.setText("Название нового ожерелья не указано!");
                } else if (newNecklaceStones.size() == 0) {
                    statusLabel.setText("В новом ожерелье нет камней!");
                }
            }

        });


    }
}
