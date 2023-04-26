/*
* Таксопарк. Определить иерархию легковых автомобилей. Создать таксопарк. Подсчитать стоимость автопарка.
* Провести сортировку автомобилей парка по расходу топлива. Найти автомобиль в компании, соответствующий
* заданному диапазону параметров скорости.
*/



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<Carriage> carriages = new ArrayList<>();
    private static List<Carriage> trainCarriages = new ArrayList<>();
    private static final String Carriages_FILE_PATH = "Carriages.data";
    private static final String TrainCarriages_FILE_PATH = "TrainCarriages.data";


    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("  1) Создать вагон");
            System.out.println("  2) Добавить вагон в состав поезда");
            System.out.println("  3) Показать созданные вагоны");
            System.out.println("  4) Показать вагоны в составе поезда");
            System.out.println("  5) Найти вагон по кол-ву мест");
            System.out.println("  6) Сортировать вагоны по уровню комфортности");
            System.out.println("  7) Сохранитть вагоны");
            System.out.println("  8) Загрузить созраненные вагоны");


            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            switch (choose){
                case "1": {createCarriage(); break;}
                case "2": {addCarriageToTrain(); break;}
                case "3": {new ShowCreatedCarriages(carriages); break;}
                case "4": {new ShowTrainCarriages(trainCarriages); break;}
                case "5": {findCarsBySpeed(); break;}
                case "6": {new SortCarriagesByComfortLevel(carriages); break;}
                case "7": {saveCarriagesInFile(); break;}
                case "8": {loadFile(); break;}
            }
        }
    }


    public static void createCarriage() {


        System.out.println("\nСОЗДАТЬ НОВЫЙ ВАГОН:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Уникальный номер вагона: ");
        String newCarriageName = consoleChoose.nextLine();


        try {
            System.out.print("Кол-во мест в вагоне: ");
            String numberOfSeats = consoleChoose.nextLine();
            int intNumOFSeats = Integer.parseInt(numberOfSeats);  // Преобразование в целое число

            System.out.print("Кол-во вмещаемого багажа (кг): ");
            String packageNum = consoleChoose.nextLine();
            int intPackageNum = Integer.parseInt(packageNum);  // Преобразование в целое число

            System.out.print("Уровень комфорта: ");
            String comfortLevel = consoleChoose.nextLine();
            int intComfortLevel = Integer.parseInt(comfortLevel);

            Carriage newCarriage = new Carriage(newCarriageName, intNumOFSeats, intPackageNum, intComfortLevel);
            carriages.add(newCarriage);
            System.out.println(newCarriageName + " теперь в списке!\n");

        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }
    }

    private static void addCarriageToTrain() {
        if (carriages.size() != 0) {
            int numeric = 0;
            for (Carriage carriage : carriages) {
                numeric++;
                System.out.println(numeric + ") Уникальный номер вагона:" + carriage.getName() + "\n\tКол-во мест: " + carriage.getSeatsNumber() +
                        " \n\tВместимость багажа: " + carriage.getPackageNumber() + " кг.\n\tУровень комфорта: " + carriage.getComfortLevel() + "/10");
            }

            try {
                Scanner consoleChoose = new Scanner(System.in);
                System.out.println("Выбирай:");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);
                trainCarriages.add(carriages.get(intChoose-1));
                System.out.println("Вагон №" + carriages.get(intChoose-1).getName() + " добавлен в состав поезда!");

            } catch (NumberFormatException e){
                System.out.println("Неправильный формат строки!");
            }

        } else {
            try{
                throw new NoCarriagesException("У вас нет созданных вагонов!");
            } catch (NoCarriagesException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    public static void findCarsBySpeed() {
        System.out.println("\nПОИСК ВАГОНА ПО ЧИСЛУ ПАССАЖИРОВ:");
        System.out.println("Впишите диапазон требуемых мест:");
        if (trainCarriages.size() != 0) {
            try {
                Scanner consoleChoose = new Scanner(System.in);

                System.out.print("От: ");
                String Ot = consoleChoose.nextLine();
                int OtInt = Integer.parseInt(Ot);  // Преобразование в целое число

                System.out.print("До: ");
                String Do = consoleChoose.nextLine();
                int DoInt = Integer.parseInt(Do);  // Преобразование в целое число

                for (Carriage carriage : trainCarriages) {
                    if (OtInt <= carriage.getSeatsNumber() & carriage.getSeatsNumber() <= DoInt) {
                        System.out.println("\n - Уникальный номер вагона:" + carriage.getName() + "\n\tКол-во мест: " + carriage.getSeatsNumber() +
                                " \n\tВместимость багажа: " + carriage.getPackageNumber() + " кг.\n\tУровень комфорта: " + carriage.getComfortLevel() + "/10");
                    }
                }
            } catch (Exception e) {
                System.out.println("Неправильный формат строки!");
            }
        } else {
            try{
                throw new NoCarriagesException("Список вагонов в составе пуст!");
            } catch (NoCarriagesException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Сериализация класса
    public static void saveCarriagesInFile(){  // Сохранение музыки в файл в файл
        try {
            FileOutputStream fos= new FileOutputStream(Carriages_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(carriages);
            oos.close();

            FileOutputStream fos1= new FileOutputStream(TrainCarriages_FILE_PATH);
            ObjectOutputStream oos1=new ObjectOutputStream(fos1);
            oos1.writeObject(trainCarriages);
            oos1.close();
            System.out.println("\nВагоны сохранена!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Десерилизация класса
    public static void loadFile(){  // Загрузка сохраненных песен из файла
        System.out.println("\nЗАГРУЗКА ФАЙЛА:");
        try {
            FileInputStream fis1 = new FileInputStream(Carriages_FILE_PATH);
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            carriages = (List<Carriage>) ois1.readObject();

            FileInputStream fis2 = new FileInputStream(TrainCarriages_FILE_PATH);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            trainCarriages = (List<Carriage>) ois2.readObject();

            System.out.println("\nИнформация о вагонах загружена!\n");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
