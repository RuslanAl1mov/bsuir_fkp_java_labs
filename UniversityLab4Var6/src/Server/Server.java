package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;


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

                    System.out.println("Получено -- > " + str);

                    try {
                        String[] client_message = str.split(" ");
                        System.out.println(Arrays.toString(client_message));



                        float num1 = Float.parseFloat(client_message[0]);
                        float num2 = Float.parseFloat(client_message[1]);
                        float num3 = Float.parseFloat(client_message[2]);
                        float num4 = Float.parseFloat(client_message[3]);
                        float num5 = Float.parseFloat(client_message[4]);
                        float num6 = Float.parseFloat(client_message[5]);
                        float num7 = Float.parseFloat(client_message[6]);
                        float num8 = Float.parseFloat(client_message[7]);
                        float num9 = Float.parseFloat(client_message[8]);

                        float answer;

                        answer = (num1*num5*num9)+(num7*num2*num6)+(num3*num4*num8)-
                                 (num3*num5*num7)-(num2*num4*num9)-(num1*num6*num8);
                        System.out.println("Ответ: " + Float.toString(answer));

                        os.writeUTF(Float.toString(answer));


                    }  catch (Exception e) {
                        System.out.println(e.getMessage());
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
