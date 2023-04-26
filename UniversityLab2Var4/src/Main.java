import java.io.*;
import java.util.*;


public class Main {
    private static List<Vegetable> vegetables = new ArrayList<>();
    private static List<Vegetable> salad = new ArrayList<>();
    private static String FILE_PATH = "salad.data";

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("  1) Добавить новый овощь в список");
        System.out.println("  2) Добавить овощь в салат");
        System.out.println("  3) Показать список овощей в салате");
        System.out.println("  4) Найти овощи по калориям");
        System.out.println("  5) Сортировать овощ по каллориям");
        System.out.println("  6) Сохранить салат");
        System.out.println("  7) Загрузить салат");

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
        } else if (Objects.equals(choose, "5")) {
            sortSaladVegs();
        } else if (Objects.equals(choose, "6")) {
            saveSaladInFile();
        } else if (Objects.equals(choose, "7")) {
            loadFile();
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
            if (intVegetableKal > 999){
                try {
                    throw new VegetableException("Слишком калорийно!");
                } catch (VegetableException e){
                    System.err.println(e.getMessage());
                    System.out.println(" ");
                    mainMenu();
                }

            }
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }


        Vegetable newVegetable = new Vegetable(newVegetableName, intVegetableKal);
        vegetables.add(newVegetable);
        System.out.println(newVegetableName + " теперь в списке!\n");
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

    private static void sortSaladVegs(){
        Collections.sort(salad, new Comparator<Vegetable>() {
            @Override
            public int compare(Vegetable v1, Vegetable v2) {
                return Integer.compare(v1.getKalNum(), (v2.getKalNum()));
            }});
        showSaladVeg();
    }


    // Сериализация класса
    public static void saveSaladInFile(){  // Сохранение существующих салата в файл
        try {
            FileOutputStream fos= new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(salad);

            oos.close();
            System.out.println("\nПодарок сохранен!\n");

            mainMenu();
        } catch (IOException e) {
            e.printStackTrace();
            mainMenu();
        }
    }


    // Десерилизация класса
    public static void loadFile(){  // Загрузка сохраненного салата из файла
        System.out.println("Последний сохраненный салат:");
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            salad = (List<Vegetable>) ois.readObject();
            System.out.println("\nИнформация загружена!\n");

            mainMenu();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            mainMenu();
        }
    }



}
