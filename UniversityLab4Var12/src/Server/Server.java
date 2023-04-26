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
                int minIndex = -1;
                int maxIndex = -1;

                while (true) {

                    str = "";
                    try {
                        str = is.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Получено -- > " + str);

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


                    float[] lns = new float[] {num1, num2, num3, num4, num5, num6, num7, num8, num9};
                    float[] clms = new float[] {num1, num4, num7, num2, num5, num8, num3, num6, num9};

                    List<Float> lines = new ArrayList<>();
                    List<Float> linesToCompare = new ArrayList<>();
                    List<Float> columns = new ArrayList<>();
                    List<Float> columnsToCompare = new ArrayList<>();
                    for (float num: lns){
                        lines.add(num);
                        linesToCompare.add(num);
                    }
                    for (float num: clms){
                        columns.add(num);
                        columnsToCompare.add(num);
                    }

                    System.out.println(lines);
                    System.out.println(columns);

                    Collections.sort(lines, new Comparator<Float>() {
                        @Override
                        public int compare(Float o1, Float o2) {
                            return Float.compare(o1, (o2));
                        }});

                    Collections.sort(columns, new Comparator<Float>() {
                        @Override
                        public int compare(Float o1, Float o2) {
                            return Float.compare(o1, (o2));
                        }});

                    System.out.println(lines);
                    System.out.println(columns);

                    int lineListIndex = linesToCompare.indexOf(lines.get(0))+1;
                    int columnListIndex = columnsToCompare.indexOf(columns.get(lines.size()-1))+1;

                    if (lineListIndex <=3) minIndex=1;
                    else if (lineListIndex > 3 && lineListIndex<=6){minIndex=2;}
                    else if (lineListIndex > 6 && lineListIndex<=9){minIndex=3;}

                    if (columnListIndex <=3) maxIndex=1;
                    else if (columnListIndex > 3 && columnListIndex<=6){maxIndex=2;}
                    else if (columnListIndex > 6 && columnListIndex<=9){maxIndex=3;}


                    String answer = Integer.toString(minIndex) + " " + Integer.toString(maxIndex);
                    System.out.println("Отправлено -- > " + answer);
                    os.writeUTF(answer);
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
