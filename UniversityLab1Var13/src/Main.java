import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    private static ArrayList<Furgon> furgons = new ArrayList<>();
    private static ArrayList<Coffee> existedCoffees = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("1) Добавить кофе в каталог");
        System.out.println("2) Создать фургон");
        System.out.println("3) Добавить кофе в фургон");
        System.out.println("4) Показать все фургоны");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createCoffee();
        } else if (Objects.equals(choose, "2")) {
            createFurgon();
        } else if (Objects.equals(choose, "3")) {
            addCoffeeInFurgon();
        }
    }

    public static void createCoffee() {
        boolean setPhisics = false;
        String coffeePhisics = "";
        int intPackageWeight = 0;
        int intPrice = 0;


        System.out.println("\nДОБАВИТЬ НОВЫЙ КОФЕ:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название: ");
        String coffeeName = consoleChoose.nextLine();

        do {
            System.out.println("Физическое состояние: ");
            System.out.println("1) Зерно");
            System.out.println("2) Молотый");
            System.out.println("3) Растворимый в банке");
            System.out.println("4) Растворимый в пакетике");

            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            if (Objects.equals(choose, "1")){
                coffeePhisics = "Зерно";
                setPhisics = true;
            } else if (Objects.equals(choose, "2")){
                coffeePhisics = "Молотый";
                setPhisics = true;
            } else if (Objects.equals(choose, "3")){
                coffeePhisics = "Растворимый в банке";
                setPhisics = true;
            } else if (Objects.equals(choose, "4")){
                coffeePhisics = "Растворимый в пакетике";
                setPhisics = true;
            }
        } while (!setPhisics);

        System.out.print("Вес упаковки: ");
        String packageWeight = consoleChoose.nextLine();
        try {
            intPackageWeight = Integer.parseInt(packageWeight);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        System.out.print("Цена за кг.: ");
        String price = consoleChoose.nextLine();
        try {
            intPrice = Integer.parseInt(packageWeight);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        Coffee newCoffee = new Coffee(coffeeName, coffeePhisics, intPackageWeight, intPrice);
        existedCoffees.add(newCoffee);
        System.out.println("Новый кофе добавлен!");
        mainMenu();
    }

    public static void createFurgon() {
        int intMaxWeight = 0;
        int intMaxSum = 0;


        System.out.println("\nДОБАВИТЬ НОВЫЙ ФУРГОН:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Фургона: ");
        String furgonName = consoleChoose.nextLine();

        System.out.print("Максимальный вес в фургоне: ");
        String maxWeight = consoleChoose.nextLine();
        try {
            intMaxWeight = Integer.parseInt(maxWeight);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        System.out.print("Максимальный вес в фургоне: ");
        String maxSum = consoleChoose.nextLine();
        try {
            intMaxSum = Integer.parseInt(maxSum);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        Furgon newFurgon = new Furgon(furgonName, intMaxWeight, intMaxSum);
        furgons.add(newFurgon);
        System.out.println("Фургон создан!");
        mainMenu();
    }

    public static void addCoffeeInFurgon() {
     System.out.println("\nДОБАВИТЬ КОФЕ В ФУРГОН");
        System.out.println("\nВыбери Фургон:");
        int numeric = 0;
        for (Furgon furgon : furgons) {
            numeric ++;
            System.out.println(numeric + ") " + furgon.getName() + " Макс.вес: " + furgon.getMaxWeight() + " кг. Макс.сумма: " + furgon.getSumToLoad() + " руб.");

            int weightIn = 0;
            int priceIn = 0;
            for (Coffee coffee: furgon.getFurgonCoffees()){
                weightIn = weightIn + coffee.getPackageWeight();
                priceIn = priceIn + coffee.getPrice();
            }
            if (weightIn >= furgon.getMaxWeight() || priceIn >= furgon.getSumToLoad()){
                System.out.println("Фургон переполнен!");
            }
        }


        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();
        try {
            int intChoose = Integer.parseInt(choose);  // Преобразование в целое число
            Furgon furgon = furgons.get(intChoose-1);

            int cofNumeric = 0;
            for (Coffee coffee: existedCoffees) {
                cofNumeric++;
                System.out.println(numeric + ") " + coffee.getName() + " Вид: " + coffee.getPhisical() + " Цена: " + coffee.getPrice() + " руб.");
                mainMenu();

            }
        } catch (Exception e) {
            System.err.println("Неправильный формат строки!");
        }


    }

}
