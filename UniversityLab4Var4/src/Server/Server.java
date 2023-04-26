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
                String text = "выражена основная мысль.Олень-северное животное.В летнее время оленям в тайге " +
                        "жарко,а в горах даже в июле холодно.Олень как бы создан для северных просторов,жёсткого " +
                        "ветра,длинных морозных ночей.Олень легко бежит вперёд по тайге,подминает под себя кусты," +
                        "переплывает быстрые реки.Олень не тонет,потому что каждая его шерстинка-это длинная " +
                        "трубочка,которую внутри наполняет воздух..Нос у оленя покрыт серебристой шёрсткой." +
                        "Если бы шерсти на носу не было,олень бы его отморозил.";
                String[] splitedText = text.split("\\.");
                System.out.println(Arrays.toString(splitedText));

                while (true) {

                    str = "";
                    try {
                        str = is.readUTF();
                        str = str.toLowerCase();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Получено -- > " + str);


                    boolean found = false;
                    String[] splitedSentence;
                    for(String sentence: splitedText){
                        sentence = sentence.replace("-", " ");
                        System.out.println(sentence);
                        splitedSentence = sentence.split(" ");
                        for (String word: splitedSentence){
                            if (str.equals(word.toLowerCase())){
                                if (!sentence.equals("")) {
                                    os.writeUTF(sentence);
                                }
                                found = true;
                            }
                        }
                    }
                    if (!found){
                        os.writeUTF("ВАШЕГО СЛОВА НЕТ В ТЕКСТЕ!");
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
