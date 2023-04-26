import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<Candy> candies = new ArrayList<>();
    private static List<Candy> present = new ArrayList<>();

    public static void main(String[] args) {
        while(true){
            System.out.println("\nМеню:");
            System.out.println("  1) Создать конфету");
            System.out.println("  2) Добавить конфету в подарок");
            System.out.println("  3) Список конфет");
            System.out.println("  4) Список конфет в подарке");
            System.out.println("  5) Найти конфету по кол-ву сахара");
            System.out.println("  6) Сортировать конфеты по кол-ву сахара");
            System.out.println("  7) Сортировать конфеты по весу");


            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            switch (choose){
                case "1": {addCandy(); break;}
                case "2": {addCandyToPresent(); break;}
                case "3": {showCandies(); break;}
                case "4": {showPresent(); break;}
                case "5": {findCandyBySugar(); break;}
                case "6": {new SortCandiesSugar(candies); break;}
                case "7": {new SortCandiesWeight(candies); break;}
            }
        }
    }


    public static void addCandy() {  // функция создания новой конфеты
        int intCandySugar = 0;
        int intCandyWeight = 0;

        System.out.println("\nДобавить новую конфету:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Конфеты: ");
        String newCandyName = consoleChoose.nextLine();  // Вводим название Конфеты

        System.out.print("Кол-во сахара в конфете: ");
        String candySugar = consoleChoose.nextLine();  // Вводим кол-во сахара в конфете
        try {
            intCandySugar = Integer.parseInt(candySugar);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат строки!");
        }

        System.out.print("Вес конфет(ы): ");
        String candyWeight = consoleChoose.nextLine();  // Вводим вес конфет(ы)
        try {
            intCandyWeight = Integer.parseInt(candyWeight);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        Candy newCandy = new Candy(newCandyName, intCandySugar, intCandyWeight);
        candies.add(newCandy);
        System.out.println(newCandyName + " теперь в списке!\n");
    }


    public static void addCandyToPresent() {  // Выбираем и добавляем конфету в подарок
        int numeric = 1;
        int presentWeight = 0;

        System.out.println("\nВыбери конфету чтобы добавить ее в подарок:");
        if (candies.size() != 0) {
            for (Candy candy : candies) {
                System.out.println(numeric + ") " + candy.getCandyName() + " " + candy.getCandyWeight() + " гр.");
                numeric++;
            }
            try {

                Scanner consoleChoose = new Scanner(System.in);
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

                // Выбираем и добавляем конфету в подарок
                if (intChoose <= candies.size()) {
                    present.add(candies.get(intChoose - 1));
                }

                numeric = 1;
                System.out.println("\nОбновленный список конфет в подарке:");
                for (Candy presentCandy : present) {
                    System.out.println(numeric + ") " + presentCandy.getCandyName() + " " + presentCandy.getCandyWeight() + " гр.");
                    numeric++;
                    presentWeight += presentCandy.getCandyWeight();
                }

                System.out.println("Полный вес подарка: " + presentWeight + "\n");
            } catch (Exception e) {
                System.out.println("Неправильный формат строки!");
            }
        } else {System.out.println("Подарок пустой!");}
    }

    private static void showCandies() {  // Функция выводи весь список созданных конфет
        System.out.println("\nВСЕ КОНФЕТЫ:");
        if (candies.size() != 0) {
            int numeric = 0;
            for (Candy candy : candies) {
                numeric++;
                System.out.println(numeric + ") " + candy.getCandyName() + " " + candy.getCandyWeight() + " гр.  Сахар: " + candy.getSugarNum());
            }
        } else {System.out.println("Список конфет пуст!"); }
    }

    private static void showPresent() {  // Функция выводит список конфет, которые находятся в подарке
        int presentWeight = 0;
        int presentSugarNum = 0;
        int numeric = 1;

        System.out.println("\nКонфеты:");
        if (present.size() != 0) {
            for (Candy presentCandy : present) {
                presentWeight += presentCandy.getCandyWeight();
                presentSugarNum += presentCandy.getSugarNum();
                System.out.println(numeric + ") " + presentCandy.getCandyName() + " " + presentCandy.getCandyWeight() + " гр.");
                numeric++;
            }
            System.out.println("\nВес подарка: " + presentWeight + " гр.");
            System.out.println("Кол-во сахара в подарке: " + presentSugarNum + "\n");

        } else {
            System.out.println("Подарок пустой!");
        }
    }

    public static void findCandyBySugar() {  // функция поиска конфет В ПОДАРКЕ по вписанному диапазону сахара в конфете
        System.out.println("\nВпишите кол-во сахара:");
        try {
            Scanner consoleChoose = new Scanner(System.in);

            System.out.print("От: ");
            String Ot = consoleChoose.nextLine();
            int OtInt = Integer.parseInt(Ot);  // Преобразование в целое число

            System.out.print("До: ");
            String Do = consoleChoose.nextLine();
            int DoInt = Integer.parseInt(Do);  // Преобразование в целое число

            for (Candy presentCandy : present) {
                if (OtInt <= presentCandy.getSugarNum() &  presentCandy.getSugarNum() <= DoInt) {
                    System.out.println("-" + presentCandy.getCandyName() + " " + presentCandy.getSugarNum());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Неправильный формат строки!");
        }
    }
}
