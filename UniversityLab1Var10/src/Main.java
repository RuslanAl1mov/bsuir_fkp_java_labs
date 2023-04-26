import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<Car> cars = new ArrayList<>();


    public static void main(String[] args) {

        while (true){
            System.out.println("\nМеню:");
            System.out.println("  1) Добавить машину");
            System.out.println("  2) Список машин");
            System.out.println("  3) Найти машину по диапазону скорости");
            System.out.println("  4) Сортировать машины по кол-ву расхода топлива");
            System.out.println("  5) Сортировать машины по максимальной скорости");
            System.out.println("  6) Сортировать машины по цене");

            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            switch (choose) {
                case "1": {addCar(); break;}
                case "2": {showCars(); break;}
                case "3": {findCarsBySpeed(); break;}
                case "4": {new SortCarsByFuelUse(cars); break;}
                case "5": {new SortCarsBySpeed(cars); break;}
                case "6": {new SortCarsByPrice(cars); break;}
            }
        }
    }


    public static void addCar() {
        System.out.println("\nДобавить новую машину:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Марка Машины: ");
        String newCarName = consoleChoose.nextLine();  // Вводим с кансоли Марку машины

        try {
            System.out.print("Цена машины: ");
            String carPrice = consoleChoose.nextLine();  // Вводим с кансоли Цену машины
            int intCarPrice = Integer.parseInt(carPrice);  // Преобразование в целое число

            System.out.print("Максимальная скорость: ");
            String carSpeed = consoleChoose.nextLine();  // Вводим с кансоли максимальную скорость машины
            int intCarSpeed = Integer.parseInt(carSpeed);  // Преобразование в целое число

            System.out.print("Расход топлива: ");
            String carFuelUse = consoleChoose.nextLine();  // Вводим с кансоли Расход топлива
            int intCarFuelUse = Integer.parseInt(carFuelUse);  // Преобразование в целое число

            Car newCar = new Car(newCarName, intCarPrice, intCarSpeed, intCarFuelUse);
            cars.add(newCar);
            System.out.println(newCarName + " теперь в списке!\n");

        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }
    }


    private static void showCars() {
        System.out.println("\nМАШИНЫ В ТАКСОПАРКЕ:");
        if (cars.size() != 0) {
            int numeric = 0;
            int carsFullPrice = 0;
            for (Car car : cars) {
                numeric++;
                System.out.println(numeric + ") Марка машины:" + car.getCarName() + "\nСтоимость машины: " + car.getCarPrice() +
                        " $\nМаксимальнаяс корость: " + car.getCarMaxSpeed() + " км/ч\nРасход топлива: " + car.getCarFuelUse());
                carsFullPrice = carsFullPrice + car.getCarPrice();
            }
            System.out.println("\nОбщая стоимость таксопарка: " + carsFullPrice);

        } else {System.out.println("Список машин пуст!");}
    }


    public static void findCarsBySpeed() {
        System.out.println("\nВпишите диапазон скорости:");
        try {
            Scanner consoleChoose = new Scanner(System.in);

            System.out.print("От: ");
            String Ot = consoleChoose.nextLine();
            int OtInt = Integer.parseInt(Ot);  // Преобразование в целое число

            System.out.print("До: ");
            String Do = consoleChoose.nextLine();
            int DoInt = Integer.parseInt(Do);  // Преобразование в целое число

            for (Car car : cars) {
                if (OtInt <= car.getCarMaxSpeed() &  car.getCarMaxSpeed() <= DoInt) {
                    System.out.println("-" + car.getCarName() + " " + car.getCarMaxSpeed() + " км/ч");

                }
            }
        }
        catch (Exception e) {
            System.out.println("Неправильный формат строки!");
        }
    }

}
