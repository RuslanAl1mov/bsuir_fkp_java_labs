/*
 * Туристические путевки. Сформировать набор предложений клиенту по выбору туристической путевки
 * различного типа (отдых, экскурсии, лечение, шопинг, круиз и т. д.) для оптимального выбора. Учитывать
 * возможность выбора транспорта, питания и числа дней. Реализовать выбор и сортировку путевок.
 *
 * */


import java.io.*;
import java.util.*;

public class Main {
    public final static String EXISTED_TICKETS = "D:/ExTickets.data";
    public final static String CLIENT_TICKETS = "D:/ClTickets.data";
    private static List<Ticket> existedTickets = new ArrayList<>();
    private static List<Ticket> clientTickets = new ArrayList<>();

    public static void main(String[] args) {
        loadSavedClass();
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("1) Создать Путевку;");
        System.out.println("2) Сортировать путевки по цене;");
        System.out.println("3) Сортировать путевки по кол-ву дней;");
        System.out.println("4) Выбрать Путевку;");
        System.out.println("5) Посмотреть Путевки клиента:");
        System.out.println("6) Сохранить Путевку");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createTicket();
        } else if (Objects.equals(choose, "2")) {
            new SortTicketsByPrice(existedTickets);
            mainMenu();
        } else if (Objects.equals(choose, "3")) {
            new SortTicketsByDays(existedTickets);
            mainMenu();
        } else if (Objects.equals(choose, "4")) {
            getTicket();
        } else if (Objects.equals(choose, "5")) {
            showClientTickets();
        } else if (Objects.equals(choose, "6")) {
            saveToFile();
        } else {mainMenu();}
    }


    public static void createTicket() {
        int intTicketCost = 0;
        int intTicketDays = 0;
        String ticketType = "";

        System.out.println("\nДобавить новую путевку:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название путевки: ");
        String newTicketName = consoleChoose.nextLine();

        boolean type = false;
        do {
            System.out.println("Тип Путевки: ");
            System.out.println("1)Отдых\n2)Экскурсии\n3)Лечение");

            System.out.print("\nВыбери: ");
            String chooseType = consoleChoose.nextLine();
            if (Objects.equals(chooseType, "1")) {
                ticketType = "Отдых";
                type = true;
            } else if (Objects.equals(chooseType, "2")) {
                ticketType = "Экскурсия";
                type = true;
            } else if (Objects.equals(chooseType, "3")) {
                ticketType = "Лечение";
                type = true;
            } else {
                System.out.println("не верный вид путевки!");
            }
        } while (!type);

        System.out.print("Колличество дней: ");
        String ticketDays = consoleChoose.nextLine();
        try {
            intTicketDays = Integer.parseInt(ticketDays);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        System.out.print("Стоимость Путевки: ");
        String ticketCost = consoleChoose.nextLine();
        try {
            intTicketCost = Integer.parseInt(ticketCost);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        Ticket newTicket = new Ticket(newTicketName, ticketType, intTicketDays, intTicketCost);
        existedTickets.add(newTicket);
        System.out.println(newTicketName + " теперь в каталоге!\n");
        mainMenu();
    }

    public static void getTicket() {
        System.out.println("\nДОБАВИТЬ ПУТЕВКУ КЛИЕНТУ:");
        Scanner consoleChoose = new Scanner(System.in);

        if (existedTickets.size() == 0){
            try{
                throw new NumberException("\nСПИСОК БИЛЕТОВ ПУСТ!\n");
            } catch (NumberException e){
                System.out.println(e.getMessage());
                mainMenu();
            }
        }

        int numeric = 0;
        for (Ticket exTicket : existedTickets) {
            numeric ++;
            System.out.println(numeric + ") " + exTicket.getTicketName() + " (" + exTicket.getDaysNum() + " дней) - " + exTicket.getTicketCost() + " руб.");
        }

        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();
        try {
            int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

            String transportType = "";
            boolean type = false;
            do {
                System.out.println("Выберите тип трансората: ");
                System.out.println("1) Автобус\n2) Самолет\n3) Поезд");

                System.out.print("Выбери: ");
                String chooseType = consoleChoose.nextLine();
                if (Objects.equals(chooseType, "1")) {
                    transportType = "Автобус";
                    type = true;
                } else if (Objects.equals(chooseType, "2")) {
                    transportType = "Самолет";
                    type = true;
                } else if (Objects.equals(chooseType, "3")) {
                    transportType = "Поезд";
                    type = true;
                } else {
                    System.out.println("не верный вид путевки!");
                }
            } while (!type);

            String foodType = "";
            boolean typeFood = false;
            do {
                System.out.println("Выберите кухню: ");
                System.out.println("1) Русская кухня\n2) Индийская\n3) Европейская");

                System.out.print("Выбери: ");
                String chooseType = consoleChoose.nextLine();
                if (Objects.equals(chooseType, "1")) {
                    foodType = "Автобус";
                    typeFood = true;
                } else if (Objects.equals(chooseType, "2")) {
                    foodType = "Самолет";
                    typeFood = true;
                } else if (Objects.equals(chooseType, "3")) {
                    foodType = "Поезд";
                    typeFood = true;
                } else {
                    System.out.println("не верный вид путевки!");
                }
            } while (!typeFood);

            Ticket chosenTicket = existedTickets.get(intChoose-1);
            chosenTicket.setTransportType(transportType);
            chosenTicket.setFoodType(foodType);
            clientTickets.add(chosenTicket);
            System.out.println("Путевка добавлен в путевки клиента!");
            mainMenu();
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }
    }

    public static void showClientTickets() {
        int numeric = 0;

        System.out.println("\nПУТЕВКИ КЛИЕНТА");

        for (Ticket clTicket : clientTickets) {
            numeric ++;
            System.out.println(numeric + ") " + clTicket.getTicketName() + " (" + clTicket.getDaysNum() + " дней) - " + clTicket.getTicketCost() + " руб.");
            System.out.println("\tТип транспорта: " + clTicket.getTransportType());
            System.out.println("\tКухня: " + clTicket.getFoodType() + "\n");
        }
        mainMenu();
    }

    // Сериализация класса
    public static void saveToFile() {  // Сохранение путевок
        try {
            FileOutputStream file = new FileOutputStream(EXISTED_TICKETS);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(existedTickets);
            output.close();

            FileOutputStream file1 = new FileOutputStream(CLIENT_TICKETS);
            ObjectOutputStream output1 = new ObjectOutputStream(file1);
            output1.writeObject(clientTickets);
            output1.close();


            System.out.println("Путевки сохранены");
            mainMenu();
        }
        catch (Exception e) {
            e.getStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            System.out.println(e.getMessage());

        }
    }


    // Дисериализация класса
    public static void loadSavedClass(){   // показать сохраненную путевку
        try {
            // Reads data using the ObjectInputStream
            FileInputStream fileStream = new FileInputStream(CLIENT_TICKETS);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);
            clientTickets = (List<Ticket>) objStream.readObject();
            objStream.close();


            FileInputStream fileStream1 = new FileInputStream(EXISTED_TICKETS);
            ObjectInputStream objStream1 = new ObjectInputStream(fileStream1);
            existedTickets = (List<Ticket>) objStream1.readObject();
            objStream1.close();
            System.out.println("\nПутевки загружены!");
            mainMenu();
        } catch (ClassNotFoundException | IOException e){
            System.out.println(e.getMessage());
            mainMenu();
        }
    }
}