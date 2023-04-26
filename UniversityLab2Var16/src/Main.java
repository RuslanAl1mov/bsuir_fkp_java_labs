
/*   Счета. Клиент может иметь несколько счетов в банке. Учитывать возможность блокировки/разблокировки счета.
        Реализовать поиск и сортировку счетов. Вычисление общей суммы по счетам. Вычисление суммы по всем счетам,
        имеющим положительный и отрицательный балансы отдельно.
* */

import java.io.*;
import java.util.*;

public class Main {
    public static List<Schet> existedSchets = new ArrayList<>();
    public static List<Schet> blockedSchets = new ArrayList<>();
    private static String FILE_PATH1 = "Blocked.data";
    private static String FILE_PATH2 = "Unblocked.data";


    public static void main(String[] args) {
        System.out.println("\nМеню:");
        System.out.println("1) Создать Счет;");
        System.out.println("2) Получить информацию о счетах;");
        System.out.println("3) Заблокировать счет;");
        System.out.println("4) Разблокировать счет;");
        System.out.println("5) Найти счет;");
        System.out.println("6) Сортировка счетов по сумме;");
        System.out.println("7) Разделить положительные и отрицательные счета;");
        System.out.println("8) Сохранить счета");
        System.out.println("9) Загрузить счета");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createSchet();
        } else if (Objects.equals(choose, "2")) {
            getSchetsInfo();
        } else if (Objects.equals(choose, "3")) {
           new BlockSchet(existedSchets, blockedSchets);
        } else if (Objects.equals(choose, "4")) {
            new UnblockSchet(blockedSchets, existedSchets);
        } else if (Objects.equals(choose, "5")) {
            searchSchet();
        } else if (Objects.equals(choose, "6")) {
            sortSchetBySum();
        } else if (Objects.equals(choose, "7")) {
            sortPositivNegativSchet();
        } else if (Objects.equals(choose, "8")) {
            saveCandiesInFile();
        } else if (Objects.equals(choose, "9")) {
            loadFile();
        }
    }


    public static void createSchet() {
        int intSchetCost = 0;

        System.out.println("\nДобавить новый счет:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название счета: ");
        String newSchetName = consoleChoose.nextLine();

        System.out.print("Сумма на счету: ");
        String schetCost = consoleChoose.nextLine();
        try {
            intSchetCost = Integer.parseInt(schetCost);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        Schet newSchet = new Schet(newSchetName, intSchetCost);
        existedSchets.add(newSchet);
        System.out.println(newSchetName + " теперь существует!\n");
    }


    public static void getSchetsInfo() {
        System.out.println("\nСписок счетов:");
        if (existedSchets.size() != 0 || blockedSchets.size() != 0) {
            int numeric = 0;

            for (Schet clSchet : existedSchets) {
                numeric++;
                System.out.println(numeric + ") " + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.  ==> РАЗБЛОКИРОВАНО!");

            }
            for (Schet clSchet : blockedSchets) {
                numeric++;
                System.out.println(numeric + ")" + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.  ==> ЗАБЛОКИРОВАНО!");
            }
        } else {
            try {
                throw new NoSchetException("Список счетов пуст!");
            } catch (NoSchetException e) {
                System.err.print(e.getMessage() + "!");
                System.out.println("_");
            }
        }
    }


    public static void searchSchet(){
        System.out.println("\nНАЙТИ СЧЕТ:");

        if (existedSchets.size() != 0 || blockedSchets.size() != 0) {

            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Введите название счета: ");
            String choose = consoleChoose.nextLine();

            int numeric = 0;
            for (Schet clSchet : existedSchets) {
                if (Objects.equals(choose, clSchet.getSchetName())) {
                    numeric++;
                    System.out.println("\t- " + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.  ==> РАЗБЛОКИРОВАНО!");
                }
            }
            for (Schet clSchet : blockedSchets) {
                if (Objects.equals(choose, clSchet.getSchetName())) {
                    numeric++;
                    System.out.println("\t- " + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.  ==> ЗАБЛОКИРОВАНО!");
                }
            }

            System.out.println("Всего совпадений найдено: " + numeric + " счетов.");
        } else {
            try {
                throw new NoSchetException("Список счетов пуст!");
            } catch (NoSchetException e) {
                System.err.println(e.getMessage());
                System.out.println(" ");
            }
        }
    }


    public static void sortSchetBySum(){
        System.out.println("\nСОРТИРОВКА СЧЕТОВ ПО СУММЕ:");

        Collections.sort(existedSchets, new Comparator<Schet>(){
            @Override
            public int compare(Schet s1, Schet s2){
                return Integer.compare(s1.getSchetSum(), (s2.getSchetSum()));
            }
        });
        System.out.println("\nОтсортировано по Сумме на счету:");

        System.out.println("НЕ ЗАБЛОКИРОВАННЫЕ СЧЕТА:");
        for (Schet clUnBlSchet: existedSchets){
            System.out.println("\t- " + clUnBlSchet.getSchetName() + " ::: " + clUnBlSchet.getSchetSum() + " руб.");
        }

        Collections.sort(blockedSchets, new Comparator<Schet>(){
            @Override
            public int compare(Schet s1, Schet s2){
                return Integer.compare(s1.getSchetSum(), (s2.getSchetSum()));
            }
        });

        System.out.println("\nЗАБЛОКИРОВАННЫЕ СЧЕТА:");
        for (Schet clBlSchet: blockedSchets){
            System.out.println("\t- " + clBlSchet.getSchetName() + " ::: " + clBlSchet.getSchetSum() + " руб.");
        }
    }


    public static void sortPositivNegativSchet() {
        System.out.println("\nСОРТИРОВКА ПОЛОЖИТЕЛНЫХ И ОТРИЦАТЕЛЬНЫХ СЧЕТОВ:");


        Collections.sort(existedSchets, new Comparator<Schet>(){
            @Override
            public int compare(Schet s1, Schet s2){
                return Integer.compare(s1.getSchetSum(), (s2.getSchetSum()));
            }
        });

        Collections.sort(blockedSchets, new Comparator<Schet>(){
            @Override
            public int compare(Schet s1, Schet s2){
                return Integer.compare(s1.getSchetSum(), (s2.getSchetSum()));
            }
        });

        System.out.println("НЕ ЗАБЛОКИРОВАННЫЕ СЧЕТА:");
        int posUnBlSchetsNum = 0;
        int negUnBlSchetsNum = 0;
        int posUnBlSchetsSum = 0;
        int negUnBlSchetsSum = 0;
        int numeric = 0;
        for (Schet clUnBlSchet: existedSchets){
            if (clUnBlSchet.getSchetSum() >= 0){
                posUnBlSchetsNum++;
                posUnBlSchetsSum += clUnBlSchet.getSchetSum();
            } else {
                negUnBlSchetsNum++;
                negUnBlSchetsSum += clUnBlSchet.getSchetSum();
            }
            numeric ++;
            System.out.println("\t" + numeric + ") " + clUnBlSchet.getSchetName() + " ::: " + clUnBlSchet.getSchetSum() + " руб.");
        }


        System.out.println("\nЗАБЛОКИРОВАННЫЕ СЧЕТА:");
        int posBlSchetsNum = 0;
        int negBlSchetsNum = 0;
        int posBlSchetsSum = 0;
        int negBlSchetsSum = 0;
        numeric = 0;
        for (Schet clBlSchet: blockedSchets){
            if (clBlSchet.getSchetSum() >= 0){
                posBlSchetsNum++;
                posBlSchetsSum += clBlSchet.getSchetSum();
            } else {
                negBlSchetsNum++;
                negBlSchetsSum += clBlSchet.getSchetSum();
            }
            numeric ++;
            System.out.println("\t" + numeric + ") " + clBlSchet.getSchetName() + " ::: " + clBlSchet.getSchetSum() + " руб.");
        }

        System.out.println("\nНЕ ЗАБЛОКИРОВАННЫЕ СЧЕТА:");
        System.out.println("Всего счетов: " + (posUnBlSchetsNum + negUnBlSchetsNum));
        System.out.println("Всего с положительным балансом: " + posUnBlSchetsNum + " СУММА ::: " + posUnBlSchetsSum + " руб.");
        System.out.println("Всего с отрицательным балансом: " + negUnBlSchetsNum + " СУММА ::: " + negUnBlSchetsSum + " руб.");

        System.out.println("\nЗАБЛОКИРОВАННЫЕ СЧЕТА:");
        System.out.println("Всего счетов: " + (posBlSchetsNum + negBlSchetsNum));
        System.out.println("Всего с положительным балансом: " + posBlSchetsNum + " СУММА ::: " + posBlSchetsSum + " руб.");
        System.out.println("Всего с отрицательным балансом: " + negBlSchetsNum + " СУММА ::: " + negBlSchetsSum + " руб.");
    }

    // Сериализация класса
    public static void saveCandiesInFile(){  // Сохранение существующих счетов в файл
        try {
            FileOutputStream fos= new FileOutputStream(FILE_PATH1);
            FileOutputStream fos2= new FileOutputStream(FILE_PATH2);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            ObjectOutputStream oos2=new ObjectOutputStream(fos2);
            oos.writeObject(existedSchets);
            oos2.writeObject(blockedSchets);
            oos.close();
            oos2.close();

            System.out.println("\nСчета сохранены!\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Десерилизация класса
    public static void loadFile(){  // Загрузка сохраненных счетов из файла
        System.out.println("Последние сохраненные счета:");
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH1);
            FileInputStream fis2 = new FileInputStream(FILE_PATH2);

            ObjectInputStream ois = new ObjectInputStream(fis);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            existedSchets = (List<Schet>) ois.readObject();
            blockedSchets = (List<Schet>) ois2.readObject();
            System.out.println("\nСчета загружены!\n");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

