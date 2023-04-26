package Client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;


public class Controller {
    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;
    private boolean ON_AIR=true;

    private void createConnection(String host, int port) throws IOException {
        // запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента
        try {
            socket = new Socket(host, port);
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());

            System.out.println("Client connected to socket.");
            System.out.println();

            connectionStatus.setTextFill(Color.GREEN);
            connectionStatus.setText("Connected!");


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            socket.close();
            oos.close();
            this.ON_AIR = false;
        }
    }

    public void sendMessage(String message) throws IOException, InterruptedException {
        // пишем данные с консоли в канал сокета для сервера
        oos.writeUTF(message);
        System.out.println("Client sent message " + message + " to server.");
        VBoxLayout.getChildren().add(messageHBox(messageToSendTextField.getText(), "right"));
        messageToSendTextField.setText("");
        Thread.sleep(300);

    }

    public void chekMessageIn() throws IOException {
        String in = ois.readUTF();
        VBoxLayout.getChildren().add(messageHBox(in, "left"));  // Декодирование "UTF-8
    }


    @FXML
    private Button connectionBtn;

    @FXML
    private TextField hostTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Label connectionStatus;

    @FXML
    private Button sendDataBtn;

    @FXML
    private TextField messageToSendTextField;

    @FXML
    private VBox VBoxLayout;


    private VBox messageHBox(String messageText, String orientation){
        VBox newVBox = new VBox();
        newVBox.setPadding(new Insets(5, 0, 0, 0));
        if (orientation.equals("right")) {
            newVBox.setAlignment(Pos.TOP_RIGHT);
        } else if (orientation.equals("left")) {
            newVBox.setAlignment(Pos.TOP_LEFT);
        }
        newVBox.setMinHeight(100.0);
        newVBox.setMaxWidth(530.0);

        Label mainMessage = new Label(messageText);
        mainMessage.setFont(Font.font("Georgia", null, null, 14));
        mainMessage.setWrapText(true);
        if (orientation.equals("right")){
            mainMessage.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 3px; -fx-border-radius:3px");
            mainMessage.setTextAlignment(TextAlignment.RIGHT);
            mainMessage.setAlignment(Pos.TOP_RIGHT);
        } else if (orientation.equals("left")) {
            mainMessage.setStyle("-fx-border-color: #dddddd; -fx-border-width: 3px; -fx-border-radius:3px");
            mainMessage.setTextAlignment(TextAlignment.LEFT);
            mainMessage.setAlignment(Pos.TOP_LEFT);
        }

        int labelAdaptiveHeight;
        int celoeZnachKolSimvolov = messageText.length() - (messageText.length() % 50);
        int ostatok = messageText.length() % 50;

        if (messageText.length() > 50){
            labelAdaptiveHeight = (celoeZnachKolSimvolov/50)*20;
            System.out.println((celoeZnachKolSimvolov/50));
            if (ostatok != 0){
                labelAdaptiveHeight = labelAdaptiveHeight + 20;
            }
            mainMessage.setPrefHeight(labelAdaptiveHeight);
        } else {
            mainMessage.setPrefHeight(20.0);
            newVBox.setMinHeight(50.0);
        }

        mainMessage.setPrefWidth(395.0);
        newVBox.getChildren().add(mainMessage);


        // Инициализация объекта date
        Date date = new Date();
        // Вывод текущей даты и времени с использованием toString()
        String str = String.format("%ta %tb %te %tT", date, date, date, date);

        Label dateTimeLabel = new Label(str);
        dateTimeLabel.setFont(Font.font("System", null, null, 11));
        if (orientation.equals("right")){
            dateTimeLabel.setStyle("-fx-background-color:#dddddd; -fx-border-color: #e5e5e5; -fx-border-width: 3px; -fx-border-radius:3px");
        } else if (orientation.equals("left")) {
            dateTimeLabel.setStyle("-fx-border-color: #dddddd; -fx-border-width: 3px; -fx-border-radius:3px");
        }
        dateTimeLabel.setTextAlignment(TextAlignment.LEFT);
        dateTimeLabel.setAlignment(Pos.CENTER_LEFT);
        dateTimeLabel.setWrapText(true);
        dateTimeLabel.setMinHeight(20.0);
        dateTimeLabel.setPrefWidth(100.0);
        newVBox.getChildren().add(dateTimeLabel);

        return newVBox;
    }


    @FXML
    void initialize() {

        Pane pane = new Pane();
        pane.setMaxWidth(100);
        pane.setPrefHeight(200);
        VBoxLayout.getChildren().add(pane);
        VBoxLayout.setMaxWidth(530.0);
        VBoxLayout.setPadding(new Insets(5, 10, 0, 0));

        connectionBtn.setOnAction(event -> {
            String host = hostTextField.getText().trim();  // Получение строки с ip_адресом
            String port = portTextField.getText().trim();  // Получение строки с портом
            int intPort = Integer.parseInt(port);
            try {
                createConnection(host, intPort);
            } catch (Exception e){
                System.out.println(e);
            }
        });


        sendDataBtn.setOnAction(event -> {

            if (!messageToSendTextField.getText().trim().equals("") && ON_AIR){
                try {
                    sendMessage(messageToSendTextField.getText());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            chekMessageIn();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
}







