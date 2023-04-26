import java.net.*;
import java.io.*;
import java.util.Objects;


class Server {
    static int countclients = 0;//счетчик подключившихся клиентов
    static private String[] numbers = {"Один", "Два", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять", "Десять"};

    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        DataInputStream is = null;
        DataOutputStream os = null;
        try {
            sock = new ServerSocket(1024);
            System.out.println("Server Started!");

            while (true) {
                Socket client = sock.accept();
                countclients++;
                System.out.println("=======================================");
                System.out.println("Client " + countclients + " connected");

                is = new DataInputStream(client.getInputStream());
                os = new DataOutputStream(client.getOutputStream());

                String str;
                while (true) {

                    str = "";
                    try {
                        str = is.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Получено -- > " + str);

                    try {
                        String[] client_message = str.split(" ");

                        float num1 = Float.parseFloat(client_message[0]);
                        float num2 = Float.parseFloat(client_message[2]);

                        if (Objects.equals(client_message[1], "+")){
                            float answer = num1 + num2;
                            os.writeUTF(Float.toString(answer));
                            System.out.println("Ответ --> " + answer);
                        } else if (Objects.equals(client_message[1], "-")){
                            float answer = num1 - num2;
                            os.writeUTF(Float.toString(answer));
                            System.out.println("Ответ --> " + answer);
                        } else if (Objects.equals(client_message[1], "*")){
                            float answer = num1 * num2;
                            os.writeUTF(Float.toString(answer));
                            System.out.println("Ответ --> " + answer);
                        } else if (Objects.equals(client_message[1], "/")){
                            float answer = num1 / num2;
                            os.writeUTF(Float.toString(answer));
                            System.out.println("Ответ --> " + answer);
                        } else {
                            os.writeUTF("Неизвестная команда!");
                        }

                    }  catch (NumberFormatException e) {
                        System.out.println("Ошибка преобразования в число!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            is.close();
            os.close();
            sock.close();
            System.out.println("Client " + countclients + " disconnected");
        }
    }}
