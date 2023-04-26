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
                System.out.println("Client " + countclients + " connected");
                System.out.println("=======================================");


                is = new DataInputStream(client.getInputStream());
                os = new DataOutputStream(client.getOutputStream());

                HashMap<String, String> sportsman = new HashMap<>();
                sportsman.put("1ADW32", "Фамилия1");
                sportsman.put("SD2Q25", "Фамилия2");
                sportsman.put("43aDw", "Фамилия3");
                sportsman.put("fSa23", "Фамилия4");

                String str;
                boolean flag = true;
                while (flag) {

                    System.out.println("\n=======================================\n");

                    try {
                        str = is.readUTF();

                        System.out.println("Получено -- > " + str);

                        if (str.equals("GET IDes")) {
                            String answer = String.valueOf(sportsman.keySet()).replace("[", "").replace("]",
                                    "").replace(",", "");
                            os.writeUTF("ID " + answer);
                            System.out.println("Отправлено: ID " + answer);
                        } else {
                            String[] converted = str.split(" ");
                            if (converted[0].equals("RESULT")){
                                try {
                                    List<Integer> defaultJumps = new ArrayList<>();
                                    List<Integer> jumps = new ArrayList<>();
                                    List<String> keys = new ArrayList<>();
                                    keys.add(converted[1].split(":")[0]);
                                    keys.add(converted[2].split(":")[0]);
                                    keys.add(converted[3].split(":")[0]);
                                    keys.add(converted[4].split(":")[0]);

                                    defaultJumps.add(Integer.parseInt(converted[1].split(":")[1]));
                                    defaultJumps.add(Integer.parseInt(converted[2].split(":")[1]));
                                    defaultJumps.add(Integer.parseInt(converted[3].split(":")[1]));
                                    defaultJumps.add(Integer.parseInt(converted[4].split(":")[1]));
                                    System.out.println(defaultJumps);


                                    jumps.add(Integer.parseInt(converted[1].split(":")[1]));
                                    jumps.add(Integer.parseInt(converted[2].split(":")[1]));
                                    jumps.add(Integer.parseInt(converted[3].split(":")[1]));
                                    jumps.add(Integer.parseInt(converted[4].split(":")[1]));


                                    Collections.sort(jumps, new Comparator<Integer>() {
                                        @Override
                                        public int compare(Integer n1, Integer n2) {
                                            return Integer.compare(n1, (n2));
                                        }});
                                    Collections.reverse(jumps);

                                    String strAnswer = "";
                                    for (int num: jumps){
                                        int i = 0;
                                        for (int value: defaultJumps){
                                            if (num == value){
                                                System.out.println(sportsman.values());
                                                strAnswer = strAnswer + keys.get(i) + ":" + sportsman.get(keys.get(i)) + " ";
                                            }
                                            i++;
                                        }
                                    }
                                    os.writeUTF("RESULT " + strAnswer);
                                    System.out.println("Отправлено: RESULT " + strAnswer);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }



                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        flag = false;
                        System.out.println("Client " + countclients + " disconnected");
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
