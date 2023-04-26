import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



public class Controller {
    private Socket socket;
    private DataOutputStream oos;
    private DataInputStream ois;

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

            while (!socket.isOutputShutdown())
                try {
                    in = ois.readUTF();  // Декодирование "UTF-8"

                    String[] server_answer = in.split(":");
                    System.out.println(server_answer[0]);

                    if (Objects.equals(server_answer[0], "error:")){
                        StringBuilder serverError = null;
                        for (int i=1; i < server_answer.length; i++) {
                            serverError.append(server_answer[i]);
                            serverError.append(" ");
                        }
                        System.out.println("Cообщение об ошибке  --> " + serverError);
                        errorLineLabel.setText(String.valueOf(serverError));
                    } else { answerTextField.setText(in); }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


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
        private TextField sendNum2TextField;

        @FXML
        private TextField sendNum1TextField;

        @FXML
        private TextField sendZnakTextField;

        @FXML
        private TextField answerTextField;

        @FXML
        private Label errorLineLabel;


    @FXML
    void initialize() {
        connectionBtn.setOnAction(event -> {
            String host = hostTextField.getText().trim();  // Получение строки с ip_адресом
            String port = portTextField.getText().trim();  // Получение строки с портом
            try {
                int intPort = Integer.parseInt(port);
                createConnection(host, intPort);
            }  catch (NumberFormatException | IOException e) {
                portLabel.setText("");
            }
        });

        sendDataBtn.setOnAction(event -> {
            errorLineLabel.setText("");
            answerTextField.setText("");

            if (!sendNum1TextField.getText().trim().equals("") & !sendZnakTextField.getText().trim().equals("") &
                    !sendNum2TextField.getText().trim().equals("")){

                String inputNumber1 = sendNum1TextField.getText().trim();  // Получить строку с цифрой
                String inputZnak = sendZnakTextField.getText().trim();  // Получить строку с цифрой
                String inputNumber2 = sendNum2TextField.getText().trim();  // Получить строку с цифрой

                try {
                    Integer.parseInt(inputNumber1);
                    Integer.parseInt(inputNumber2);
                    if (inputZnak.equals("/") && inputNumber2.equals("0")) {
                        errorLineLabel.setText("Ощибка! На 0 делить нельзя!");
                    } else {
                        String sendInformation = inputNumber1 + " " + inputZnak + " " + inputNumber2;
                        sendMessage(sendInformation);  // Отправка числа на сервер

                    }
                }  catch (NumberFormatException | IOException e) {
                    errorLineLabel.setText("В числовые поля могут быть записаны только числа!");
                }
            } else {
                errorLineLabel.setText("Впишите все значения!");
            }
        });
    }

}







