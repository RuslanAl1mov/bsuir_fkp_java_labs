package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


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
                System.out.println("Client " + countclients + " connected");
                System.out.println("=======================================");


                is = new DataInputStream(client.getInputStream());
                os = new DataOutputStream(client.getOutputStream());

                HashMap<Double, String> existedFlats = new HashMap<>();
                existedFlats.put(1324.0, "Адрес 1");
                existedFlats.put(832.0, "Адрес 2");
                existedFlats.put(562.0, "Адрес 3");
                existedFlats.put(1322.8, "Адрес 4");
                existedFlats.put(683.0, "Адрес 5");

                String str;
                double doublePrice = 0;
                while (true) {

                    System.out.println("\n=======================================\n");

                    str = "";
                    try {
                        str = is.readUTF();
                        doublePrice = Double.parseDouble(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Получено -- > " + str);
                    boolean found = false;
                    for (Double cost: existedFlats.keySet()){
                        if (cost <= doublePrice){
                            System.out.println("Квартира: " + existedFlats.get(cost) +
                                               "\n\tЦена: " + Double.toString(cost));
                            os.writeUTF("Адресс: " + existedFlats.get(cost) + "  Цена: " + Double.toString(cost));
                            found = true;
                        }
                    }
                    if (!found){
                        os.writeUTF("НИЧЕГО НЕ НАЙДЕНО!");
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
