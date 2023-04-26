package Administrator.CreatePresentWindow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Administrator.CreateCandyWindow.entity.Candy;
import Controller.Controller;
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

public class CreatePresentController {

    @FXML
    private Button goToMainMenuBtn;

    @FXML
    private VBox existedCandiesVBoxLayer;

    @FXML
    private VBox presentCandiesVBoxLayer;

    @FXML
    private Label presentWeightLabel;

    @FXML
    private Label candiesAmountLabel;

    @FXML
    private TextField newPresentNameTextField;

    @FXML
    private Button savePresentBtn;

    @FXML
    private Label statusLabel;

    private Controller controller = new Controller();

    private List<Candy> newPresentCandies = new ArrayList<>();


    public void loadExistedCandies() {
        int counter = 0;
        for (Candy candy: controller.getCandies()){
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

            Label label1 = new Label(candy.getCandyName());
            label1.setFont(Font.font("Georgia", null, null, 15));
            label1.setTextAlignment(TextAlignment.CENTER);
            label1.setAlignment(Pos.CENTER);
            label1.setWrapText(true);
            label1.setPrefHeight(100.0);
            label1.setPrefWidth(150.0);
            newHBox.getChildren().add(label1);

            Label label2 = new Label(Integer.toString(candy.getCandyWeight()));
            label2.setFont(Font.font("Georgia", null, null, 23));
            label2.setTextAlignment(TextAlignment.CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            label2.setMinHeight(55.0);
            label2.setPrefWidth(122.0);
            newHBox.getChildren().add(label2);

            Button btn = new Button("Добавить");
            btn.setFont(Font.font("Georgia", null, null, 13));
            btn.setWrapText(true);
            btn.setMinHeight(40.0);
            btn.setPrefWidth(100.0);
            newHBox.getChildren().add(btn);
            btn.setOnAction(e -> {
                newPresentCandies.add(candy);
                int candyWeight = 0;
                for (Candy cn: newPresentCandies){
                    candyWeight = candyWeight + cn.getCandyWeight();
                }
                presentWeightLabel.setText(String.valueOf(candyWeight) + " гр.");
                candiesAmountLabel.setText(String.valueOf(newPresentCandies.size()) + " шт.");
                addCandyToPresentScroll(candy);
            });

            existedCandiesVBoxLayer.getChildren().add(newHBox);
        }
    }

    public void addCandyToPresentScroll(Candy candy) {
        HBox newHBox = new HBox();
        if (newPresentCandies.size() % 2 == 1) {
            newHBox.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 2px; -fx-border-radius:10px");
        }
        newHBox.setAlignment(Pos.CENTER);
        newHBox.setPrefHeight(100.0);
        newHBox.setPrefWidth(354.0);
        newHBox.setMaxWidth(354.0);
        newHBox.setMinHeight(100.0);

        Label label1 = new Label(candy.getCandyName());
        label1.setFont(Font.font("Georgia", null, null, 15));
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setAlignment(Pos.CENTER);
        label1.setWrapText(true);
        label1.setPrefHeight(100.0);
        label1.setPrefWidth(150.0);
        newHBox.getChildren().add(label1);

        Label label2 = new Label(Integer.toString(candy.getCandyWeight()));
        label2.setFont(Font.font("Georgia", null, null, 23));
        label2.setTextAlignment(TextAlignment.CENTER);
        label2.setAlignment(Pos.CENTER);
        label2.setWrapText(true);
        label2.setMinHeight(55.0);
        label2.setPrefWidth(115.0);
        newHBox.getChildren().add(label2);

        Button btn = new Button("Удалить");
        btn.setFont(Font.font("Georgia", null, null, 13));
        btn.setWrapText(true);
        btn.setMinHeight(40.0);
        btn.setPrefWidth(100.0);
        newHBox.getChildren().add(btn);
        btn.setOnAction(e -> {
            newPresentCandies.remove(candy);
            int candyWeight = 0;
            for (Candy cn: newPresentCandies){
                candyWeight = candyWeight + cn.getCandyWeight();
            }
            presentWeightLabel.setText(String.valueOf(candyWeight) + " гр.");
            candiesAmountLabel.setText(String.valueOf(newPresentCandies.size()) + " шт.");
            presentCandiesVBoxLayer.getChildren().remove(newHBox);
        });

        presentCandiesVBoxLayer.getChildren().add(newHBox);
    }


    @FXML
    void initialize() {
        loadExistedCandies();

        goToMainMenuBtn.setOnAction(event -> {
            Stage LogInStage = (Stage) goToMainMenuBtn.getScene().getWindow();
            LogInStage.close();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Administrator/MainWindow/fxml/adminMainWindow.fxml")));
                Stage newStage = new Stage();
                newStage.setTitle("Administrator");
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        savePresentBtn.setOnAction(event -> {
            if (!newPresentNameTextField.getText().trim().equals("") && newPresentCandies.size() != 0){
                controller.createPresent(newPresentNameTextField.getText(), newPresentCandies);

                newPresentNameTextField.setText("");
                presentCandiesVBoxLayer.getChildren().removeAll(presentCandiesVBoxLayer.getChildren());
                newPresentCandies.clear();

                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Подарок сохранен!");

            } else {
                statusLabel.setTextFill(Color.RED);
                if (newPresentNameTextField.getText().trim().equals("")) {
                    statusLabel.setText("Название нового подарка не указано!");
                } else if (newPresentCandies.size() == 0) {
                    statusLabel.setText("В новом подарке нет конфет!");
                }
            }
        });
    }
}
