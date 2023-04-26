import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static String CLIENTS = "Client.txt";
    public static String DRUGS = "Drugs.txt";
    public static String OPERATIONS = "Operations.txt";
    public static String REMOVED_CLIENTS = "RemovedClient.txt";

    private static List<Client> clients = new ArrayList<>();
    private static List<Drugs> existedDrugs = new ArrayList<>();
    private static List<Operation> existedOperations = new ArrayList<>();
    private static List<Client> removedClients = new ArrayList<>();


    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1) Создать лекарство");
            System.out.println("2) Создать операцию");
            System.out.println("3) Зарегестрировать клиента");
            System.out.println("4) Добавить лечение клиенту");
            System.out.println("5) Показать пациентов");
            System.out.println("6) Выписать клиента");
            System.out.println("7) Сохранить клиента");
            System.out.println("8) Извлечь клиента из файла");

            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            switch (choose) {
                case "1": {
                    addDrug();
                    break;
                }
                case "2": {
                    addOperation();
                    break;
                }
                case "3": {
                    registerClient();
                    break;
                }
                case "4": {
                    addClientDrugOperation();
                    break;
                }
                case "5": {
                    showClients();
                    break;
                }
                case "6": {
                    addToBlackList();
                    break;
                }
                case "7": {
                    saveToFile();
                    break;
                }
                case "8": {
                    showSavedClass();
                    break;
                }
            }
        }
    }


    public static void addDrug() {
        System.out.println("\nДобавить новую таблетку:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Таблеток: ");
        String drugName = consoleChoose.nextLine();

        Drugs newDrug = new Drugs(drugName);
        existedDrugs.add(newDrug);
        System.out.println(drugName + " теперь в списке лекарств!\n");
    }


    public static void addOperation() {
        System.out.println("\nДобавить новую Операцию:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Операции: ");
        String oprName = consoleChoose.nextLine();

        Operation newOperation = new Operation(oprName);
        existedOperations.add(newOperation);
        System.out.println(oprName + " теперь в списке Операций!\n");
    }


    // Добавление клиента
    public static void registerClient() {
        int intClientAge = 0;

        System.out.println("\nДобавить нового Клиента:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Имя клиента: ");
        String clientName = consoleChoose.nextLine();  // Вводим имя клиента

        System.out.print("Возраст клиента: ");
        String clientAge = consoleChoose.nextLine();  // Вводим Возраст клиента
        try {
            intClientAge = Integer.parseInt(clientAge);  // Преобразование в целое число

            if (intClientAge < 18) {
                try {
                    throw new AgeException("Ошибка Возраста!");
                } catch (AgeException e) {
                    System.err.println(e.getMessage());}
            }
        }
        catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

        Client newClient = new Client(clientName, intClientAge);
        clients.add(newClient);
        System.out.println(clientName + " теперь в списке клиентов!\n");
    }

    // Выбор клента
    public static Client selectClient() {
        int intChoose = -1;
        int numeric = 0;
        for (Client client: clients){
            numeric ++;
            System.out.println(numeric + ") " + client.getClientName() + " (" + client.getClientAge() + " лет)");
        }

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери клиента: ");
        String choose = consoleChoose.nextLine();

        try {
            intChoose = Integer.parseInt(choose);  // Преобразование в целое число
        }
        catch (Exception e) {
            System.err.println("Неправильный формат строки!");
        }
        return clients.get(intChoose-1);
    }


    public static Vipolnayushiy createVipolnayushego() {
        String choose;
        Scanner consoleChoose = new Scanner(System.in);

        System.out.println("Выерите кто выполнит назначение:\n1) Врач\n2) Медсестра");
        choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            System.out.print("Имя Врача: ");
            String name = consoleChoose.nextLine();
            Vipolnayushiy newVip = new Vipolnayushiy(name, "Врач");
            return newVip;
        }
        else if (Objects.equals(choose, "2")) {
            System.out.print("Имя Meдсестры: ");
            String name = consoleChoose.nextLine();
            Vipolnayushiy newVip = new Vipolnayushiy(name, "Медсестра");

            return newVip;
        }
        return null;
    }

    public static void addClientDrugOperation(){
        int intChooseDrug;
        int intChooseOperation;

        System.out.println("\nДобавить лечение клиенту:");
        Client client = selectClient();

        System.out.println("\nВыберите лечение:\n1) Таблетки\n2) Операция");


        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            int numeric = 0;
            System.out.println("\nВыбери таблетки:");
            for (Drugs drugs : existedDrugs) {
                numeric++;
                System.out.println(numeric + ") " + drugs.getDrugName());
            }

            try {
                System.out.print("Выбери таблетки: ");
                String chooseDrug = consoleChoose.nextLine();

                intChooseDrug = Integer.parseInt(chooseDrug);  // Преобразование в целое число

                if (intChooseDrug <= existedDrugs.size()) {
                    Drugs chosenDrug = existedDrugs.get(intChooseDrug - 1);
                    chosenDrug.setDrugMaster(createVipolnayushego());
                    client.addDrug(chosenDrug);
                }

            } catch (Exception e) {
                System.err.println("Ошибкочка!");
            }
        }

        else if (Objects.equals(choose, "2")){
            int numeric = 0;
            System.out.println("\nВыбери Операцию:");
            for (Operation operation: existedOperations){
                numeric ++;
                System.out.println(numeric + ") " + operation.getOperationName());
            }

            try {
                System.out.print("Выбери: ");
                String chooseOperation = consoleChoose.nextLine();

                intChooseOperation = Integer.parseInt(chooseOperation);  // Преобразование в целое число

                if (intChooseOperation <= existedOperations.size()){
                    client.addOperation(existedOperations.get(intChooseOperation-1));
                }
            }
            catch (Exception e) {
                System.err.println("Неправильный формат строки!");
            }
        }

        else {
            System.out.println("\nНичего не выбрано!\n");
        }

    }


    private static void showClients() {
        int removedNumeric = 0;
        int numeric = 0;

        if (clients.size() != 0) {
            System.out.println("\nСписок пациентов:");
            for (Client client : clients) {
                numeric++;
                System.out.println("\n" + numeric + ") " + client.getClientName());

                if (client.getClientDrugs().size() != 0) {
                    System.out.println("  Лекарства:");
                    for (Drugs drugs : client.getClientDrugs()) {
                        System.out.println("  -- " + drugs.getDrugName() + "  Выполняющий назначение - " +
                                drugs.getDrugMaster().getDoctorName() + " (" + drugs.getDrugMaster().getDoctorStatus() + ")");
                    }
                }

                if (client.getClientOperations().size() != 0) {
                    System.out.println("  Операции:");
                    for (Operation operation : client.getClientOperations()) {
                        System.out.println("  -- " + operation.getOperationName());
                    }
                }
            }
        }

        else {
        System.out.println("\nВ базе нет пациентов!");
        }

        if (removedClients.size() != 0) {
            System.out.println("\n\nВыписанные пациенты:");


            for (Client client : removedClients) {
                removedNumeric++;
                System.out.println("\n" + numeric + ") " + client.getClientName());

                if (client.getClientDrugs().size() != 0) {
                    System.out.println("  Лекарства:");
                    for (Drugs drugs : client.getClientDrugs()) {
                        System.out.println("  -- " + drugs.getDrugName() + "  Выполняющий назначение - " +
                                drugs.getDrugMaster().getDoctorName() + " (" + drugs.getDrugMaster().getDoctorStatus() + ")");
                    }
                }

                if (client.getClientOperations().size() != 0) {
                    System.out.println("  Операции:");
                    for (Operation operation : client.getClientOperations()) {
                        System.out.println("  -- " + operation.getOperationName());
                    }
                }

                System.out.println("  Выписан: " + client.getRemoveReason());
            }
        }
        else {
            System.out.println("\nНет выписанных пациентов!");
        }


        System.out.println("\nКол-во пациентов: " + numeric);
        System.out.println("\nКол-во выписанных пациентов: " + removedNumeric);
    }


    private static void addToBlackList(){
        int numeric = 0;
        int intChoose;

        System.out.println("\nВыписка клиента:");
        for (Client client: clients){
            numeric ++;
            System.out.println(numeric + ") " + client.getClientName() + " (" + client.getClientAge() + " лет)");
        }

        try {
            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери клиента: ");
            String choose = consoleChoose.nextLine();

            intChoose = Integer.parseInt(choose);  // Преобразование в целое число


            System.out.println("\nВыберите причину выписки: \n1) Окончание лечения\n2) Нарушение\nВыберите: ");
            String removeReason = consoleChoose.nextLine();

            if (Objects.equals(removeReason, "1")){
                clients.get(intChoose-1).setRemoveReason("Окончание лечения");
                removedClients.add(clients.get(intChoose-1));
                clients.remove(intChoose-1);
                System.out.println("Клиент выписан!");
            }

            else if (Objects.equals(removeReason, "2")){
                clients.get(intChoose-1).setRemoveReason("Нарушения");
                removedClients.add(clients.get(intChoose-1));
                clients.remove(intChoose-1);
                System.out.println("Клиент выписан!");
            }

            else {
                System.out.println("\nНичего не выбрано!\n");
            }

        }
        catch (Exception e) {
            System.err.println("Неправильный формат строки!");
        }
    }


    // Сериализация класса
    public static void saveToFile() {  // Сохранение пациента в файл
        try {
            FileOutputStream file = new FileOutputStream(CLIENTS);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(clients);
            output.close();

            FileOutputStream file1 = new FileOutputStream(OPERATIONS);
            ObjectOutputStream output1 = new ObjectOutputStream(file1);
            output1.writeObject(existedOperations);
            output1.close();

            FileOutputStream file2 = new FileOutputStream(DRUGS);
            ObjectOutputStream output2 = new ObjectOutputStream(file2);
            output2.writeObject(existedDrugs);
            output2.close();

            FileOutputStream file3 = new FileOutputStream(REMOVED_CLIENTS);
            ObjectOutputStream output3 = new ObjectOutputStream(file3);
            output3.writeObject(removedClients);
            output3.close();

            System.out.println("СОХРАНЕНО!: ");
        }
        catch (Exception e) {
            e.getStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            System.out.println(e.getMessage());

        }
    }


    // Дисериализация класса
    public static void showSavedClass(){
        try {
            // Reads data using the ObjectInputStream
            FileInputStream fileStream = new FileInputStream(CLIENTS);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);
            clients = (List<Client>) objStream.readObject();
            objStream.close();

            FileInputStream fileStream1 = new FileInputStream(OPERATIONS);
            ObjectInputStream objStream1 = new ObjectInputStream(fileStream1);
            existedOperations = (List<Operation>) objStream1.readObject();
            objStream1.close();

            FileInputStream fileStream2 = new FileInputStream(DRUGS);
            ObjectInputStream objStream2 = new ObjectInputStream(fileStream2);
            existedDrugs = (List<Drugs>) objStream2.readObject();
            objStream2.close();

            FileInputStream fileStream3 = new FileInputStream(REMOVED_CLIENTS);
            ObjectInputStream objStream3 = new ObjectInputStream(fileStream3);
            removedClients = (List<Client>) objStream3.readObject();
            objStream3.close();

        } catch (ClassNotFoundException | IOException e){
            System.out.println(e.getMessage());
        }
    }


}
