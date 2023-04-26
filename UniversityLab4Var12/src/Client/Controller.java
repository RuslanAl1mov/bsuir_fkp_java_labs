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
import java.net.UnknownHostException;


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

                    String[] server_answer = in.split(" ");

                    lineNumberTextField.setText(server_answer[0]);
                    columnNumberTextField.setText(server_answer[1]);

                } catch (IOException e) {
                    e.printStackTrace();
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
    private TextField columnNumberTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField lineNumberTextField;



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
            columnNumberTextField.setText("");
            lineNumberTextField.setText("");

            if (Num1.getText().substring(Num1.getText().length()-1).equals(".")){
                Num1.setText(Num1.getText().substring(0, Num1.getText().length()-1));
            }
            if (Num2.getText().substring(Num2.getText().length()-1).equals(".")){
                Num2.setText(Num2.getText().substring(0, Num2.getText().length()-1));
            }
            if (Num3.getText().substring(Num3.getText().length()-1).equals(".")){
                Num3.setText(Num3.getText().substring(0, Num3.getText().length()-1));
            }
            if (Num4.getText().substring(Num4.getText().length()-1).equals(".")){
                Num4.setText(Num4.getText().substring(0, Num4.getText().length()-1));
            }
            if (Num5.getText().substring(Num5.getText().length()-1).equals(".")){
                Num5.setText(Num5.getText().substring(0, Num5.getText().length()-1));
            }
            if (Num6.getText().substring(Num6.getText().length()-1).equals(".")){
                Num6.setText(Num6.getText().substring(0, Num6.getText().length()-1));
            }
            if (Num7.getText().substring(Num7.getText().length()-1).equals(".")){
                Num7.setText(Num7.getText().substring(0, Num7.getText().length()-1));
            }
            if (Num8.getText().substring(Num8.getText().length()-1).equals(".")){
                Num8.setText(Num8.getText().substring(0, Num8.getText().length()-1));
            }
            if (Num9.getText().substring(Num9.getText().length()-1).equals(".")){
                Num9.setText(Num9.getText().substring(0, Num9.getText().length()-1));
            }


            if( Num1.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num2.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num3.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num4.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num5.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num6.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num7.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num8.getText().replace("-", "").replace(".", "").matches(regex) &&
                Num9.getText().replace("-", "").replace(".", "").matches(regex)) {

                String messageToServer = "";
                messageToServer = Num1.getText() + " " + Num2.getText() + " " + Num3.getText() + " " +
                                  Num4.getText() + " " + Num5.getText() + " " + Num6.getText() + " " +
                                  Num7.getText() + " " + Num8.getText() + " " + Num9.getText() + " " ;

                try {
                    sendMessage(messageToServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if(Num1.getText().trim().equals("") || Num2.getText().trim().equals("") || Num3.getText().trim().equals("") ||
                       Num4.getText().trim().equals("") || Num5.getText().trim().equals("") || Num6.getText().trim().equals("") ||
                       Num7.getText().trim().equals("") || Num8.getText().trim().equals("") || Num9.getText().trim().equals("")) {
                errorLabel.setText("Не все поля заполнены!");
            } else {
                errorLabel.setText("Ячейки могут быть заполнены только цифрами!");
            }

        });
    }

}







