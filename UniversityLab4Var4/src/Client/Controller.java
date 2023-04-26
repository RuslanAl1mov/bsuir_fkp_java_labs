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
import java.net.UnknownHostException;


public class Controller {
    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;
    boolean ON_AIR = true;

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


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            socket.close();
            oos.close();
            ois.close();
            this.ON_AIR=false;
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

            while (ON_AIR) {
                try {
                    in = ois.readUTF();  // Декодирование "UTF-8"
                    if (!in.equals("")) {
                        String newText = answerTextArea.getText() + " \n" + in;
                        answerTextArea.setText(newText);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    ON_AIR = false;
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
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
    private Label errorLabel;

    @FXML
    private TextField dataTextField;

    @FXML
    private TextArea answerTextArea;


    @FXML
    void initialize() {
        answerTextArea.setWrapText(true);
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
            errorLabel.setText("");
            answerTextArea.setText("");
            if (!dataTextField.getText().trim().equals("")){
                String[] list = dataTextField.getText().split(" ");
                if (list.length == 1){
                    try {
                        sendMessage(dataTextField.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{errorLabel.setText("Только одно слово!!");}
            } else {errorLabel.setText("Впишите слово!");}
        });

    }
}
