import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    List<Client> clients;
    String FILE_PATH;

    public AdminMenu(List<Client> clients, String FILE_PATH){
        this.clients = clients;
        this.FILE_PATH = FILE_PATH;
        dispatcherMenu();
    }

    // Меню АДМИНИСТАТОРА
    public void dispatcherMenu() {
        System.out.println("\nМеню АДМИНИСТРАТОРА:");
        System.out.println("1) Регистрация клиента;");
        System.out.println("2) Посмотреть клиентов и информацию о них;");
        System.out.println("3) Посмотреть Должников и отключить;");
        System.out.println("4) Посмотреть Заблокированных за неуплату;");
        System.out.println("5) Изменить номер клиенту;");
        System.out.println("6) Отключить клиента от сети;");
        System.out.println("7) Сохранить клиентов;");
        System.out.println("8) Загрузить клиентов;");
        System.out.println("9) В ГЛАВНОЕ МЕНЮ;");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        switch (choose) {
            case "1": {createNewClient(); break;}
            case "2": {showClients(); break;}
            case "3": {blockClientsWithDebts(); break;}
            case "4": {showBlockedClients(); break;}
            case "5": {updateClientPhoneNumber(); break;}
            case "6": {removeClient(); break;}
            case "7": {saveClientsInFile(); break;}
            case "8": {loadFile(); break;}
        }
    }


    public void createNewClient() {
        System.out.println("\nРЕГИСТРАЦИЯ НОВОГО КЛИЕНТА!");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Имя клиента: ");
        String clientName = consoleChoose.nextLine();
        System.out.print("Номер телефона: ");
        String clientPhNum = consoleChoose.nextLine();

        System.out.println("\nПри регистрации клиент в подарок получает 20р на счет в подарок!");
        Client newClient = new Client(clientName, clientPhNum, 20);
        clients.add(newClient);
        System.out.println("Клиент создан!");
        dispatcherMenu();
    }


    public void showClients() {
        System.out.println("\nСПИСОК КЛИЕНТОВ:");
        if (clients.size() != 0) {
            int numeric = 0;
            for (Client client : clients) {
                numeric++;
                String block = "НЕТ";
                if (client.getIsBlocked()) {
                    block = "ДА";
                }
                System.out.println(numeric + ") " + client.getName() +
                        "\n\tНомер телефона: " + client.getPhoneNumber() +
                        "\n\tНа счету: " + client.getMoney() + " руб." +
                        "\n\tБлокировка: " + block);
            }
            dispatcherMenu();
        } else {
            try {
                throw new NoClientsException("У вас нет клиентов");
            } catch (NoClientsException e) {
                System.err.println(e.getMessage());
                dispatcherMenu();
            }
        }

    }

    public void blockClientsWithDebts(){
        System.out.println("\nСПИСОК ДОЛЖНИКОВ:");
        if (clients.size() != 0) {
            List<Client> clientsDebts = new ArrayList<>();
            int numeric = 0;
            for (Client client : clients) {
                if (client.getMoney() <= 0 && !client.getIsBlocked()) {
                    numeric++;
                    clientsDebts.add(client);
                    System.out.println(numeric + ") " + client.getName() +
                            "\n\tНомер телефона: " + client.getPhoneNumber() +
                            "\n\tНа счету: " + client.getMoney() + " руб." +
                            "\n\tБлокировка: " + "НЕТ");
                }
            }
            if (numeric != 0) {

                Scanner consoleChoose = new Scanner(System.in);

                try {
                    System.out.print("Выбери: ");
                    String choose = consoleChoose.nextLine();
                    int intChoose = Integer.parseInt(choose);

                    Client clientWithDebt = clientsDebts.get(intChoose - 1);
                    clientWithDebt.setIsBlocked(true);
                    System.out.println("Клиент заблокирован!");
                    dispatcherMenu();

                } catch (Exception e) {
                    System.out.println("Неверный формат!");
                    dispatcherMenu();
                }
            } else {
                System.out.println("Нет ДОЛЖНИКОВ!");
                dispatcherMenu();
            }
        } else {
            try {
                throw new NoClientsException("У вас нет клиентов");
            } catch (NoClientsException e) {
                System.err.println(e.getMessage());
                dispatcherMenu();
            }
        }

    }

    public void showBlockedClients(){
        System.out.println("\nСПИСОК БЛОКИРОВАННЫХ КЛИЕНТОВ:");
        int numeric = 0;
        for (Client client: clients){
            if (client.getIsBlocked()){
                numeric++;
                System.out.println(numeric + ") " + client.getName() +
                        "\n\tНомер телефона: " + client.getPhoneNumber() +
                        "\n\tНа счету: " + client.getMoney() + " руб." +
                        "\n\tБлокировка: " + "ДА");}
        }
        dispatcherMenu();
    }

    public void updateClientPhoneNumber(){
        System.out.println("\nИЗМЕНЕНИЕ НОМЕРА ТЕЛФОНА КЛИЕНТА:");
        if (clients.size() != 0) {
            System.out.println("Выбериете клиента:");
            int numeric = 0;
            for (Client client : clients) {
                numeric++;
                String block = "НЕТ";
                if (client.getIsBlocked()) {
                    block = "ДА";
                }
                System.out.println(numeric + ") " + client.getName() +
                        "\n\tНомер телефона: " + client.getPhoneNumber() +
                        "\n\tНа счету: " + client.getMoney() + " руб." +
                        "\n\tБлокировка: " + block);
            }

            Scanner consoleChoose = new Scanner(System.in);

            try {
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);

                Client chooseClient = clients.get(intChoose - 1);

                System.out.print("Новый номер: +");
                String newPhNumber = consoleChoose.nextLine();

                chooseClient.setPhoneNumber(newPhNumber);
                System.out.println("Номер обновлен!");
                dispatcherMenu();

            } catch (Exception e) {
                System.out.println("Неверный формат!");
                dispatcherMenu();
            }
        } else {
            try {
                throw new NoClientsException("У вас нет клиентов");
            } catch (NoClientsException e) {
                System.err.println(e.getMessage());
                dispatcherMenu();
            }
        }
    }

    public void removeClient(){
        System.out.println("\nОТКЛЮЧИТЬ КЛИЕНТА НАВСЕГДА:");
        if (clients.size() != 0) {
            System.out.println("Выбериете клиента:");
            int numeric = 0;
            for (Client client : clients) {
                numeric++;
                String block = "НЕТ";
                if (client.getIsBlocked()) {
                    block = "ДА";
                }
                System.out.println(numeric + ") " + client.getName() +
                        "\n\tНомер телефона: " + client.getPhoneNumber() +
                        "\n\tНа счету: " + client.getMoney() + " руб." +
                        "\n\tБлокировка: " + block);
            }

            Scanner consoleChoose = new Scanner(System.in);

            try {
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);

                Client chooseClient = clients.get(intChoose - 1);
                clients.remove(chooseClient);
                dispatcherMenu();

            } catch (Exception e) {
                System.out.println("Неверный формат!");
                dispatcherMenu();
            }
        } else {
            try {
                throw new NoClientsException("У вас нет клиентов");
            } catch (NoClientsException e) {
                System.err.println(e.getMessage());
                dispatcherMenu();
            }
        }
    }

    // Сериализация класса
    public void saveClientsInFile(){  // Сохранение в файл
        try {
            if (clients.size() != 0) {
                FileOutputStream fos = new FileOutputStream(FILE_PATH);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(clients);
                oos.close();

                System.out.println("\nКлиенты сохранены!\n");
            } else {
                try {
                    throw new NoClientsException("У вас нет клиентов");
                } catch (NoClientsException e) {
                    System.err.println(e.getMessage());
                    dispatcherMenu();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Десерилизация класса
    public void loadFile(){  // Загрузка сохраненных из файла
        System.out.println("\nЗАГРУЗКА ФАЙЛА:");
        try {
            FileInputStream fis1 = new FileInputStream(FILE_PATH);
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            Main.clients = (List<Client>) ois1.readObject();

            System.out.println("\nКлиенты загружены!\n");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
