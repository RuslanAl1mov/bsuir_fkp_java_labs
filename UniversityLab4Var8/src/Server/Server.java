package Server;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

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
            sock = new ServerSocket(108);
            System.out.println("Server Started!");

            while (true) {
                Socket client = sock.accept();
                countclients++;
                System.out.println("Client " + countclients + " connected");
                System.out.println("=======================================");


                is = new DataInputStream(client.getInputStream());
                os = new DataOutputStream(client.getOutputStream());

                HashMap<Integer, int[]> existedTickets = new HashMap<>();
                existedTickets.put(1, new int[] {1, 2, 33, 4, 55, 66, 7, 8, 9, 10});
                existedTickets.put(2, new int[] {25, 83, 46, 21, 4, 84, 31, 57, 24, 32});
                existedTickets.put(3, new int[] {1, 42, 99, 32, 56, 27, 4, 53, 12, 5});
                existedTickets.put(4, new int[] {53, 4, 12, 63, 25, 84, 45, 65, 9, 21});
                existedTickets.put(5, new int[] {52, 23, 65, 43, 20, 43, 50, 12, 54, 32});

                String str;
                int correct;
                int ticketIndex;
                int maxNum = -1;
                int maxNumIndex = 0;
                List<Integer> relationNumberIndexes = new ArrayList<>();
                while (true) {

                    System.out.println("\n=======================================\n");

                    str = "";
                    try {
                        str = is.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Получено -- > " + str);

                    int[] luckyNumbers = new int[10];
                    int index = 0;
                    for (String strNumber: str.split(" ")){
                        luckyNumbers[index] = Integer.parseInt(strNumber);
                        index ++;
                    }

                    ticketIndex = 0;
                    relationNumberIndexes.clear();
                    for (int[] valuesList: existedTickets.values()){
                        ticketIndex ++;
                        System.out.println("Удачные числа: " + Arrays.toString(luckyNumbers));
                        System.out.println("Числа в билете №" + ticketIndex + ": " + Arrays.toString(valuesList));
                        correct = 0;
                        for (int value: valuesList){
                            for (int s : luckyNumbers) {
                                if (value == s) {
                                    correct ++;
                                }
                            }
                        }
                        relationNumberIndexes.add(correct);
                        System.out.println("Совпадений найдено - " + correct);
                        System.out.println("-----  -----  -----  -----  -----");
                    }
                    System.out.println(relationNumberIndexes);

                    for (int num: relationNumberIndexes){
                        if(num >= maxNum){
                            maxNum = num;
                            maxNumIndex = relationNumberIndexes.indexOf(num);
                        }
                    }

                    System.out.println("Побетитель Билет # " + (maxNumIndex+1));
                    os.writeUTF(Integer.toString(maxNumIndex+1));

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
