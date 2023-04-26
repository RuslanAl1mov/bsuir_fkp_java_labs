package Client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller {
    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;
    private boolean ON_AIR = true;

    private void createConnection(String host, int port) throws IOException {
        // запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента
        try {
            socket = new Socket(host, port);
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());

            System.out.println("Client connected to socket.");
            System.out.println();
            Thread serverScanner = new Thread(new ServerListener(socket, ois));
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
        private final Socket socket;
        private final DataInputStream ois;

        public ServerListener(Socket socket, DataInputStream ois) {
            this.socket = socket;
            this.ois = ois;
        }

        @Override
        public void run() {
            String in;

            while (ON_AIR)
                try {
                    in = ois.readUTF();  // Декодирование "UTF-8"
                    answerTextField.setText(in);

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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label titleLabel;

    @FXML
    private Button connectionBtn;

    @FXML
    private Label hostLabel;

    @FXML
    private TextField hostTextField;

    @FXML
    private Label portLabel;

    @FXML
    private TextField portTextField;

    @FXML
    private Label sendDataLabel;

    @FXML
    private Label connectionStatus;

    @FXML
    private Button sendDataBtn;

    @FXML
    private TextField Num1;

    @FXML
    private TextField Num2;

    @FXML
    private TextField Num3;

    @FXML
    private TextField Num4;

    @FXML
    private TextField Num5;

    @FXML
    private TextField Num6;

    @FXML
    private TextField Num7;

    @FXML
    private TextField Num8;

    @FXML
    private TextField Num9;

    @FXML
    private TextField Num10;

    @FXML
    private TextField answerTextField;

    @FXML
    private Label errorLabel;


    @FXML
    void initialize() {
        connectionBtn.setOnAction(event -> {
            String host = hostTextField.getText().trim();  // Получение строки с ip_адресом
            String port = portTextField.getText().trim();  // Получение строки с портом
            try {
                int intPort = Integer.parseInt(port);
                createConnection(host, intPort);
            }  catch (NumberFormatException | IOException e) {
                portTextField.setText("");
            }
        });

        sendDataBtn.setOnAction(event -> {
            String regex = "\\d+";

            errorLabel.setText("");
            answerTextField.setText("");

            if( Num1.getText().matches(regex) && Num2.getText().matches(regex) && Num3.getText().matches(regex) &&
                Num4.getText().matches(regex) && Num5.getText().matches(regex) && Num6.getText().matches(regex) &&
                Num7.getText().matches(regex) && Num8.getText().matches(regex) && Num9.getText().matches(regex) &&
                Num10.getText().matches(regex)) {

                String messageToServer = Num1.getText() + " " + Num2.getText() + " " + Num3.getText() + " " +
                                         Num4.getText() + " " + Num5.getText() + " " + Num6.getText() + " " +
                                         Num7.getText() + " " + Num8.getText() + " " + Num9.getText() + " " +
                                         Num10.getText();

                try {
                    sendMessage(messageToServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if( Num1.getText().trim().equals("") || Num2.getText().trim().equals("") || Num3.getText().trim().equals("") ||
                       Num4.getText().trim().equals("") || Num5.getText().trim().equals("") || Num6.getText().trim().equals("") ||
                       Num7.getText().trim().equals("") || Num8.getText().trim().equals("") || Num9.getText().trim().equals("") ||
                       Num10.getText().trim().equals("")) {
                errorLabel.setText("Не все поля заполнены!");
            } else {
                errorLabel.setText("Ячейки могут быть заполнены только цифрами!");
            }

        });
    }

}







