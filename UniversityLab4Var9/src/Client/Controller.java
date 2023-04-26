package Client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller {
    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;
    private boolean ON_AIR = false;

    private void createConnection(String host, int port) throws IOException {
        // запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента
        try {
            socket = new Socket(host, port);
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());
            this.ON_AIR = true;

            System.out.println("Client connected to socket.");
            System.out.println();
            Thread serverScanner = new Thread(new ServerListener(ois));
            serverScanner.start();

            connectionStatus.setTextFill(Color.GREEN);
            connectionStatus.setText("Connected!");


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.ON_AIR = false;
            socket.close();
            oos.close();
            ois.close();
        }
    }

    public void sendMessage(String message) throws IOException {
        // пишем данные с консоли в канал сокета для сервера
        oos.writeUTF(message);
        System.out.println("Client sent message " + message + " to server.");
    }


    class ServerListener  implements Runnable {
        private final DataInputStream ois;

        public ServerListener(DataInputStream ois) {
            this.ois = ois;
        }

        @Override
        public void run() {
            String in;
            String[] converted;

            while (ON_AIR)
                try {
                    in = ois.readUTF();  // Декодирование "UTF-8"

                    converted = in.split(" ");
                    if (!in.equals("")) {
                        System.out.println(in);
                        if (converted[0].equals("ID")){
                            sportsmenID1.setText(converted[1].replace(" ", ""));
                            sportsmenID2.setText(converted[2].replace(" ", ""));
                            sportsmenID3.setText(converted[3].replace(" ", ""));
                            sportsmenID4.setText(converted[4].replace(" ", ""));
                        } else if (converted[0].equals("RESULT")){
                            firstPlaceTexftField.setText(converted[1].split(":")[1]);
                            spID1.setText(converted[1].split(":")[0].replace(" ", ""));
                            secondPlaceTexftField.setText(converted[2].split(":")[1]);
                            spID2.setText(converted[2].split(":")[0].replace(" ", ""));
                            thirdPlaceTexftField.setText(converted[3].split(":")[1]);
                            spID3.setText(converted[3].split(":")[0].replace(" ", ""));
                        }
                    }



                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ON_AIR=false;
                }
            }
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
    private Button getSportsmensIDesBtn;

    @FXML
    private TextField sportsmenJump1;

    @FXML
    private TextField sportsmenJump2;

    @FXML
    private TextField sportsmenJump3;

    @FXML
    private TextField sportsmenJump4;

    @FXML
    private TextField secondPlaceTexftField;

    @FXML
    private TextField thirdPlaceTexftField;

    @FXML
    private TextField firstPlaceTexftField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label sendDataLabel1;

    @FXML
    private Label sendDataLabel11;

    @FXML
    private Label sendDataLabel111;

    @FXML
    private Button getResultsBtn;

    @FXML
    private TextField sportsmenID1;

    @FXML
    private TextField sportsmenID2;

    @FXML
    private TextField sportsmenID3;

    @FXML
    private TextField sportsmenID4;

    @FXML
    private TextField spID1;

    @FXML
    private TextField spID2;

    @FXML
    private TextField spID3;

    @FXML
    void initialize() {
        connectionBtn.setOnAction(event -> {
            String host = hostTextField.getText().trim();  // Получение строки с ip_адресом
            String port = portTextField.getText().trim();  // Получение строки с портом
            try {
                int intPort = Integer.parseInt(port);
                createConnection(host, intPort);
                connectionBtn.setDisable(true);
            }  catch (NumberFormatException | IOException e) {
                portTextField.setText("");
            }
        });

        getSportsmensIDesBtn.setOnAction(event -> {
            errorLabel.setText("");
            if (ON_AIR){
                try {
                    sendMessage("GET IDes");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        getResultsBtn.setOnAction(event -> {
            errorLabel.setText("");
            firstPlaceTexftField.setText("");
            secondPlaceTexftField.setText("");
            thirdPlaceTexftField.setText("");

            String regex = "\\d+";
            if( sportsmenJump1.getText().matches(regex) && sportsmenJump2.getText().matches(regex) &&
                sportsmenJump3.getText().matches(regex) && sportsmenJump4.getText().matches(regex)) {

                if (ON_AIR) {
                    try {
                        sendMessage("RESULT " + sportsmenID1.getText() + ":" + sportsmenJump1.getText() + " " +
                                                sportsmenID2.getText() + ":" + sportsmenJump2.getText() + " " +
                                                sportsmenID3.getText() + ":" + sportsmenJump3.getText() + " " +
                                                sportsmenID4.getText() + ":" + sportsmenJump4.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {errorLabel.setText("Вписывать можно только числа!");}
        });

    }
}







