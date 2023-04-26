import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    private static List<Vegetable> vegetables = new ArrayList<>();
    private static List<Vegetable> salad = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("  1) Добавить новый овощь в список");
        System.out.println("  2) Добавить овощь в салат");
        System.out.println("  3) Показать список овощей в салате");
        System.out.println("  4) Найти овощи по калориям");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createVegetable();
        } else if (Objects.equals(choose, "2")) {
            addVegToSalad();
        } else if (Objects.equals(choose, "3")) {
            showSaladVeg();
        } else if (Objects.equals(choose, "4")) {
            new FindVegeableByKall(salad);
            mainMenu();
        } else {mainMenu();}
    }

    public static void createVegetable() {
        int intVegetableKal = 0;

        System.out.println("\nДобавить новый овощь:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Овоща: ");
        String newVegetableName = consoleChoose.nextLine();  // Вводим название Овоща

        System.out.print("Кол-во калорий в овоще: ");
        String vegKal = consoleChoose.nextLine();

        try {
            intVegetableKal = Integer.parseInt(vegKal);  // Преобразование в целое число
            Vegetable newVegetable = new Vegetable(newVegetableName, intVegetableKal);
            vegetables.add(newVegetable);
            System.out.println(newVegetableName + " теперь в списке!\n");

        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        mainMenu();
    }


    public static void addVegToSalad() {
        int numeric = 1;
        int saladKal = 0;

        System.out.println("\nВыбери овощь чтобы добавить его в салат:");
        try {
            for (Vegetable vegetable : vegetables) {
                System.out.println(numeric + ") " + vegetable.getVegetableName() + " " + vegetable.getKalNum() + " калл.");
                numeric++;
            }

            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();
            int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

            // Выбираем и добавляем конфету в подарок
            if (intChoose <= vegetables.size()){
                salad.add(vegetables.get(intChoose-1));}


            numeric = 1;
            System.out.println("");
            System.out.println("Обновленный список овощей в салате:");
            for (Vegetable saladVeg : salad) {
                System.out.println(numeric+") "+saladVeg.getVegetableName()+" "+saladVeg.getKalNum() + " калл.");
                numeric++;
                saladKal += saladVeg.getKalNum();
            }

            System.out.println("Калорийность салата: " + saladKal);

            System.out.println("");
            mainMenu();
        }
        catch (Exception e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

    }

    private static void showSaladVeg() {
        int saladKal = 0;
        int numeric = 1;

        System.out.println("\nСостав:");
        for (Vegetable saladVeg : salad) {
            saladKal += saladVeg.getKalNum();
            System.out.println(numeric+") "+saladVeg.getVegetableName()+" "+saladVeg.getKalNum()+" калл.");
            numeric++;
        }

        System.out.println("\nКалорийность салата: " + saladKal + " калл.");

        mainMenu();
    }
}
