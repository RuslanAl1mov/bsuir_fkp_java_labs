package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;


class Server {
    static int countclients = 0;//счетчик подключившихся клиентов

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

                    // Инициализация объекта date
                    Date date = new Date();
                    // Вывод текущей даты и времени с использованием toString()
                    String dateStr = String.format("%ta %tb %te %tT", date, date, date, date);
                    System.out.println(dateStr + "  Получено -- > " + str);

                    Scanner consoleChoose = new Scanner(System.in);
                    System.out.print("Сообщение: ");
                    String newMessage = consoleChoose.nextLine();


                    os.writeUTF(newMessage);
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
    }
}
