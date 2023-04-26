/*
 * Система Автобаза. Диспетчер распределяет заявки на Рейсы между Водителями и назначает для этого Автомобиль.
 * Водитель может сделать заявку на ремонт. Диспетчер может отстранить Водителя от работы. Водитель делает
 * отметку о выполнении Рейса и состоянии Автомобиля.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static List<Client> clients = new ArrayList<>();


    public static void main(String[] args) {
        while (true) {
            System.out.println("\nГлавное Меню:\nВЫБЕРИТЕ ПЕРСОНАЖА:");
            System.out.println("1) Администратор;");

            if (clients.size() > 0){
                int numeric = 2;
                for (Client client: clients) {
                    System.out.println(numeric + ") Клиент :: " + client.getName());
                    numeric ++;
                }
            }

            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            if (Objects.equals(choose, "1")) {
                new AdminMenu(clients);
            } else {
                try {
                    int intChoose = Integer.parseInt(choose);
                    new ClientMenu(clients.get(intChoose-2), clients);

                } catch (NumberFormatException e){
                    System.out.println("ВВЕДЕНО НЕВЕРНОЕ ЗНАЧЕНИЕ!");
                }
            }
        }
    }



}


