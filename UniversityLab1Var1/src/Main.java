import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    private static ArrayList<Flower> flowers = new ArrayList<>();
    private static ArrayList<Souvenir> souvenirs = new ArrayList<>();
    private static ArrayList<Bucket> buckets = new ArrayList<>();

    public static void main(String[] args) {
        Flower rose = new Flower("Роза", 6, 3, 12);
        Flower romashka = new Flower("Ромашка", 3, 1, 8);
        flowers.add(rose);
        flowers.add(romashka);
        Souvenir kartinka = new Souvenir("Картинка", 3);
        souvenirs.add(kartinka);
        Souvenir portret = new Souvenir("Портрет", 6);
        souvenirs.add(portret);

        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("Меню:");
        System.out.println("1) Добавить Цветок");
        System.out.println("2) Добавить Сувенир");
        System.out.println("3) Создать Букет");
        System.out.println("4) Посмотреть мои букеты");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            addFlower();
        } else if (Objects.equals(choose, "2")) {
            addSouvenir();
        } else if (Objects.equals(choose, "3")) {
            createNewBucket();
        } else if (Objects.equals(choose, "4")) {
            showAllBuckets();
        } else {mainMenu();}


    }

    public static void addFlower() {
        int newFlowerStemLength = 0;
        int newFlowerFresh = 0;
        int newFlowerPrice = 0;

        System.out.println("Добавить новый цветок в Базу:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название цветка: ");
        String newFlowerName = consoleChoose.nextLine();  // Вводим название цветка

        System.out.print("Длинна стебля (см.): ");
        String stemLength = consoleChoose.nextLine();  // Вводим длинну стебля
        try {
            newFlowerStemLength = Integer.parseInt(stemLength);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        System.out.print("Свежесть цветка(в днях:): ");
        String fresh = consoleChoose.nextLine();  // Вводим свежесть цветка в днях
        try {
            newFlowerFresh = Integer.parseInt(fresh);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }

        System.out.print("Цена за 1 цветок (" + newFlowerName + "): ");
        String price = consoleChoose.nextLine();  // Вводим цену за цветок
        try {
            newFlowerPrice = Integer.parseInt(price);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }


        Flower newFlower = new Flower(newFlowerName, newFlowerPrice, newFlowerFresh, newFlowerStemLength);
        flowers.add(newFlower);
        System.out.println("Новый Цветок добавлен!\n");
        mainMenu();

    }

    public static void addSouvenir() {
        int newSouvenirPrice = 0;

        System.out.println("Добавить новый Сувенир в Базу:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Сувенира: ");
        String newSouvenirName = consoleChoose.nextLine();  // Вводим название сувенира

        System.out.print("Цена Сувенира: ");
        String price = consoleChoose.nextLine();  // Вводим длинну стебля
        try {
            newSouvenirPrice = Integer.parseInt(price);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            mainMenu();
        }


        souvenirs.add(new Souvenir(newSouvenirName, newSouvenirPrice));
        System.out.println("Новый Сувенир добавлен!\n");
        mainMenu();

    }

    public static void createNewBucket() {
        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Введите название нового букета: ");
        String bucketName = consoleChoose.nextLine();

        Bucket bucket = new Bucket(bucketName);
        buckets.add(bucket);
        System.out.println("Добавьте первые цветы в ваш новый букет \"" + bucketName + "\":");
        addedNewBucket(bucket);
    }

    public static void addedNewBucket(Bucket createdBucket) {
        int flowerIndex = 0;
        int souvenirIndex = 0;

        System.out.println("Выбери что добавить:\n" +
                "1) Цветок\n" +
                "2) Сувенир\n\n" +
                "3) Сохранить букет");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            System.out.println("Выбери цветок из Базы:");
            for (int i = 0; i < flowers.size(); i++) {
                System.out.println((i + 1) + ") " + flowers.get(i).getFlowerName() +
                        " (" + flowers.get(i).getFlowerPrice() + ");");
            }

            System.out.print("Выбери: ");
            String flowerChoose = consoleChoose.nextLine();
            try {
                flowerIndex = Integer.parseInt(flowerChoose);
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
                mainMenu();
            }

            createdBucket.addFlower(flowers.get(flowerIndex - 1));
            System.out.println("Цветок добавлен!\n");
            showBucketInfo(createdBucket);
            addedNewBucket(createdBucket);
        }

        if (Objects.equals(choose, "2")) {
            System.out.println("Выбери сувенир из Базы:");
            for (int i = 0; i < souvenirs.size(); i++) {
                System.out.println((i + 1) + ") " + souvenirs.get(i).getSouvenirName() +
                        " (" + souvenirs.get(i).getSouvenirPrice() + ");");
            }

            System.out.print("Выбери: ");
            String souvenirChoose = consoleChoose.nextLine();
            try {
                souvenirIndex = Integer.parseInt(souvenirChoose);
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
                mainMenu();
            }

            createdBucket.addSouvenirs(souvenirs.get(souvenirIndex - 1));
            System.out.println("Сувенир добавлен!\n");
            showBucketInfo(createdBucket);
            addedNewBucket(createdBucket);

        }

        else if (Objects.equals(choose, "3")) {
            buckets.add(createdBucket);
            System.out.println("Новый Букет Сохранен!\n");
            mainMenu();
        }

    }

    private static void showBucketInfo(Bucket bucket) {
        int fullPrice = 0;
        int numeric = 1;

        if (bucket.souvenirs.size() != 0 || bucket.flowers.size() != 0) {
            System.out.println("Букет \"" + bucket.getBucketName() + "\"");
            for (int i = 0; i < bucket.flowers.size(); i++) {
                System.out.println(numeric + ") " + bucket.flowers.get(i).getFlowerName() +
                        " (" + bucket.flowers.get(i).getFlowerPrice() + ");");

                fullPrice = fullPrice + bucket.flowers.get(i).getFlowerPrice();
                numeric++;
            }
            for (int i = 0; i < bucket.souvenirs.size(); i++) {
                System.out.println(numeric + ") " + bucket.souvenirs.get(i).getSouvenirName() +
                        " (" + bucket.souvenirs.get(i).getSouvenirPrice() + ");");

                fullPrice = fullPrice + bucket.souvenirs.get(i).getSouvenirPrice();
                numeric++;
            }

            System.out.println("Цена за букет: " + fullPrice + "р.\n");
        } else {System.out.println("Букет пустой!"); mainMenu();}
    }

    public static void showAllBuckets() {
        for (Bucket bucket : buckets) {
            showBucketInfo(bucket);
        }
        mainMenu();

    }

}
