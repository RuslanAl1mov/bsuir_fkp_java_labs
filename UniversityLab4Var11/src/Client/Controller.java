package Client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
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
            String answerAdded;
            while (ON_AIR)
                try {
                    in = ois.readUTF();  // Декодирование "UTF-8"
                    if (!in.equals("")){
                        answerAdded = answerTextArea.getText() + "\n" + in;
                        answerTextArea.setText(answerAdded);
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
    private Button sendDataBtn;

    @FXML
    private TextField dataTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextArea answerTextArea;


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
            answerTextArea.setText("");

            if(dataTextField.getText().replace(".", "").matches(regex)) {
                try {
                    sendMessage(dataTextField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(dataTextField.getText().equals("")) {
                errorLabel.setText("Впишите данные!");
            } else {
                errorLabel.setText("Ячейка может быть заполнена только цифрами!");
            }

        });
    }

}







