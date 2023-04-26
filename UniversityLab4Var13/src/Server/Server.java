package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


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
                float answer;

                while (true) {

                    str = "";
                    try {
                        str = is.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Получено -- > " + str);

                    String[] client_message = str.split(" ");

                    float num1 = Float.parseFloat(client_message[0]);
                    float num3 = Float.parseFloat(client_message[2]);
                    float num5 = Float.parseFloat(client_message[4]);
                    float num7 = Float.parseFloat(client_message[6]);
                    float num9 = Float.parseFloat(client_message[8]);

                    answer = ((num1+num5+num9)/3)/((num3+num5+num7)/3);

                    System.out.println("Отправлено -- > " + answer);
                    os.writeUTF(Float.toString(answer));
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
