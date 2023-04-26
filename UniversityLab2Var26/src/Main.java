/*
* Система Автобаза. Диспетчер распределяет заявки на Рейсы между Водителями и назначает для этого Автомобиль.
* Водитель может сделать заявку на ремонт. Диспетчер может отстранить Водителя от работы. Водитель делает
* отметку о выполнении Рейса и состоянии Автомобиля.
*/


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static List<Driver> orderOnAir = new ArrayList<>();
    private static List<Driver> finishedOrders = new ArrayList<>();
    private static List<Driver> onRepairCars = new ArrayList<>();
    private static List<Car> existedCars = new ArrayList<>();
    private final static String GARAGE_CARS_PATH = "\\GarageCars.data";
    private final static String DRIVERS_ON_ORDER_PATH = "\\DriversOnOrder.data";
    private final static String DRIVERS_ON_REPAIR_PATH = "\\DriversOnRepair.data";
    private final static String FINISHED_ORDERS_PATH = "\\FinishedOrders.data";


    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nГлавное Меню:\nВЫБЕРИТЕ ПЕРСОНАЖА:");
        System.out.println("1) Добавить машину в гараж;");
        System.out.println("2) Диспетчер;");

        int numeric = 2;
        for (Driver workingDriver: orderOnAir) {
            numeric ++;
            System.out.println(numeric + ") Водитель :: " + workingDriver.getFirstName() + " " + workingDriver.getSecondName());
        }

        if (onRepairCars.size() > 0){
            System.out.println("Водители на ремонте:");
            for (Driver onRepairDriver: onRepairCars) {
                numeric ++;
                System.out.println(numeric + ") Водитель :: " + onRepairDriver.getFirstName() + " " + onRepairDriver.getSecondName());
            }
        }

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            addCarToGarage();
        } else if (Objects.equals(choose, "2")) {
            dispatcherMenu();
        } else {
            try {
                int intChoose = Integer.parseInt(choose);
                intChoose = intChoose - 2;
                if (intChoose <= orderOnAir.size()){
                    driverMenu(orderOnAir.get(intChoose-1));
                } else if (intChoose > orderOnAir.size()){
                    driverMenu(onRepairCars.get(intChoose-1));
                } else {
                    System.out.println("ВВЕДЕНО НЕВЕРНОЕ ЗНАЧЕНИЕ!");
                    mainMenu();
                }
            } catch (NumberFormatException e){
                System.out.println("ВВЕДЕНО НЕВЕРНОЕ ЗНАЧЕНИЕ!");
                mainMenu();
            }
        }
    }

    // Создание и добавление машины в гараж
    public static void addCarToGarage() {
        System.out.println("\nСОЗДАНИЕ МАШИНЫ В ГАРАЖ!");

        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Марка машины: ");
        String carMark = consoleChoose.nextLine();
        System.out.print("Пробег: ");
        String carMileage = consoleChoose.nextLine();
        try {
            int intCarMileage = Integer.parseInt(carMileage);
            Car newCar = new Car(carMark, intCarMileage);
            existedCars.add(newCar);
            mainMenu();
        }
        catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования значений");
            mainMenu();
        }
    }

    // Меню ДИСПЕТЧЕРА
    public static void dispatcherMenu() {
        System.out.println("\nМеню ДИСПЕТЧЕРА:");
        System.out.println("1) Отправить Водителя на вызов;");
        System.out.println("2) Посмотреть водителей и их состояние;");
        System.out.println("3) Отстранить Водителя от вызова;");
        System.out.println("4) Сохранить информацию;");
        System.out.println("5) Загрузить информацию;");
        System.out.println("6) В ГЛАВНОЕ МЕНЮ;");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createOrder();
        } else if (Objects.equals(choose, "2")) {
            new ShowDriver(orderOnAir, finishedOrders, onRepairCars);
            dispatcherMenu();
        } else if (Objects.equals(choose, "3")) {
            removeOrder();
        } else if (Objects.equals(choose, "4")) {
            saveDriversInFile();
        } else if (Objects.equals(choose, "5")) {
            loadFile();
        } else if (Objects.equals(choose, "6")) {
            mainMenu();
        } else {dispatcherMenu();}
    }


    public static void createOrder(){
        System.out.println("\nОТПРАВКА ВОДИТЕЛЯ НА ВЫЗОВ!");

        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Едем ОТКУДА: ");
        String startAddress = consoleChoose.nextLine();
        System.out.print("ЕДЕМ КУДА: ");
        String finishAddress = consoleChoose.nextLine();

        System.out.print("Имя водителя: ");
        String driverName = consoleChoose.nextLine();
        System.out.print("Фамилия водителя: ");
        String driverSecName = consoleChoose.nextLine();

        if (existedCars.size() != 0) {
            System.out.println("\nВыбери машину водителю:");
            int numeric = 0;
            for (Car car : existedCars) {
                numeric++;
                System.out.println(numeric + ") " + car.getMark() + " (" + car.getMileage() + " км.)");
            }
            System.out.print("Выбери: ");
            String chooseCar = consoleChoose.nextLine();
            try {
                int intChooseCar = Integer.parseInt(chooseCar);

                Application application = new Application(startAddress, finishAddress);  // создаем заявку
                Driver newDriver = new Driver(driverName, driverSecName);  // создаем водителя

                newDriver.setCar(existedCars.get(intChooseCar-1));  // устанавливаем машину
                existedCars.remove(existedCars.get(intChooseCar-1));  // удаляем машину из списка свободных машин
                newDriver.setApplication(application);  // отадем заявку водителю
                orderOnAir.add(newDriver);  // водиетль в пути
                dispatcherMenu();
            }
            catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования значений");
                dispatcherMenu();
            }
        } else {
            try {  // XATO
                throw new NoCarsException("В гараже нет машин!!!");
            } catch (NoCarsException e){
                System.err.println(e.getMessage());
                System.out.println(" ");
                dispatcherMenu();
            }
        }
    }


    public static void removeOrder() {
        System.out.println("\nУСТРАНИТЬ ВОДИТЕЛЯ И ОСТАНОВИТЬ ЗАЯВКУ");

        System.out.println("Выбери водителя:");

        if (orderOnAir.size() != 0) {
            int numeric = 0;
            for (Driver driver : orderOnAir) {
                numeric ++;
                System.out.println("\t" + numeric + ") Водитель: " + driver.getFirstName() + " " + driver.getSecondName());
                System.out.println("\t   Машина: " + driver.getCarName() + " (" + driver.getCarMileage() + " км.)");
                System.out.println("\t   Заявка: " + driver.getApplication().getStartAddress() + " ==> " + driver.getApplication().getFinishAddress() + "\n");
            }

            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String chooseDriver = consoleChoose.nextLine();

            try {
                int intChooseDriver = Integer.parseInt(chooseDriver);
                finishedOrders.add(orderOnAir.get(intChooseDriver-1));  // переводим заявку в завершенные заявки
                existedCars.add(orderOnAir.get(intChooseDriver-1).getCar());  // Возвращаем машину в гараж
                orderOnAir.remove(intChooseDriver-1);  // Удаляем заявку из рабочих заявок
                System.out.println("Готово! Водитель отстранен! Машину вернули в гараж!");
                dispatcherMenu();
            }
            catch (Exception e) {
                System.out.println("Ошибка преобразования значений");
                dispatcherMenu();
            }

        } else {System.out.println("НЕТ ВОДИТЕЛЕЙ НА ВЫЗОВЕ!"); dispatcherMenu();}
    }

    // Сериализация класса  failga yozish
    public static void saveDriversInFile(){  // Сохранение водителей в файл
        try {
            FileOutputStream fos= new FileOutputStream(GARAGE_CARS_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(existedCars);
            oos.close();
            FileOutputStream fos1= new FileOutputStream(DRIVERS_ON_ORDER_PATH);
            ObjectOutputStream oos1=new ObjectOutputStream(fos1);
            oos1.writeObject(orderOnAir);
            oos1.close();
            FileOutputStream fos2= new FileOutputStream(DRIVERS_ON_REPAIR_PATH);
            ObjectOutputStream oos2=new ObjectOutputStream(fos2);
            oos2.writeObject(onRepairCars);
            oos2.close();
            FileOutputStream fos3= new FileOutputStream(FINISHED_ORDERS_PATH);
            ObjectOutputStream oos3=new ObjectOutputStream(fos3);
            oos3.writeObject(finishedOrders);
            oos3.close();

            System.out.println("\nИнформация сохранена!\n");

            dispatcherMenu();
        } catch (IOException e) {
            e.printStackTrace();
            mainMenu();
        }
    }

    // Десерилизация класса  fayldan chixarish
    public static void loadFile(){  // Загрузка сохраненных водителей из файла
        System.out.println("ЗАГРУЗКА ИНФОРМАЦИИ...");
        try {
            FileInputStream fis = new FileInputStream(GARAGE_CARS_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            existedCars = (List<Car>) ois.readObject();
            FileInputStream fis1 = new FileInputStream(DRIVERS_ON_ORDER_PATH);
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            orderOnAir = (List<Driver>) ois1.readObject();
            FileInputStream fis2 = new FileInputStream(DRIVERS_ON_REPAIR_PATH);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            onRepairCars = (List<Driver>) ois2.readObject();
            FileInputStream fis3 = new FileInputStream(FINISHED_ORDERS_PATH);
            ObjectInputStream ois3 = new ObjectInputStream(fis3);
            finishedOrders = (List<Driver>) ois3.readObject();


            System.out.println("\nИнформация загружена!\n");

            dispatcherMenu();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            mainMenu();
        }
    }

    public static void driverMenu(Driver driver) {
        System.out.println("\nМеню ВОДИТЕЛЯ:");
        if (!driver.getCar().getOnRepairStatus()) {
            System.out.println("1) Завершить поездку");
            System.out.println("2) Отправить машину на ремонт");
            System.out.println("3) ГЛАВНОЕ МЕНЮ");
        } else {
            System.out.println("1) Завершить ремонт");
            System.out.println("2) ГЛАВНОЕ МЕНЮ");
        }


        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1") && !driver.getCar().getOnRepairStatus()) {
            finishDrive(driver);
        } else if (Objects.equals(choose, "1") && driver.getCar().getOnRepairStatus()) {
            stopRepair(driver);
        } else if (Objects.equals(choose, "2") && !driver.getCar().getOnRepairStatus()) {
            startRepair(driver);
        } else if (Objects.equals(choose, "2") && driver.getCar().getOnRepairStatus()) {
            mainMenu();
        } else if (Objects.equals(choose, "3") && !driver.getCar().getOnRepairStatus()) {
            mainMenu();
        } else {driverMenu(driver);}
    }

    public static void finishDrive(Driver driver) {
        System.out.println("\nЗАВЕРШИТЬ ПОЕЗДКУ (ВОДИТЕЛЬ)");
        finishedOrders.add(driver);  // переводим заявку в завершенные заявки
        existedCars.add(driver.getCar());  // Возвращаем машину в гараж
        orderOnAir.remove(driver);  // Удаляем заявку из рабочих заявок
        System.out.println("Готово! Водитель ЗАВЕРШИЛ ПОЕЗДКУ! Машину вернули в гараж!");
        mainMenu();
    }

    public static void startRepair(Driver driver) {
        orderOnAir.remove(driver);
        onRepairCars.add(driver);
        driver.getCar().setOnRepair(true);
        System.out.println("Машина заехала на ремонт!");
        driverMenu(driver);
    }

    public static void stopRepair(Driver driver) {
        onRepairCars.remove(driver);
        orderOnAir.add(driver);
        driver.getCar().setOnRepair(false);
        System.out.println("Машина покинула ремонт!");
        driverMenu(driver);
    }

}


