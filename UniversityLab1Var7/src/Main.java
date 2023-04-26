import java.util.*;
import java.util.Comparator;
import java.util.Collections;



public class Main {
    private static ArrayList<Ekipir> existedEkipir = new ArrayList<>();
    private static ArrayList<Ekipir> driverEkipir = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("1) Создать Экипировку");
        System.out.println("2) Добавить Экипировку водителю");
        System.out.println("3) Удалить Экипировку у водителя");
        System.out.println("4) Найти Экипировку по цене");
        System.out.println("5) Сортировать Экипировку по цене");
        System.out.println("6) Сортировать Экипировку по весу");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createEkipir();
        } else if (Objects.equals(choose, "2")) {
            addEkipirToDriver();
        } else if (Objects.equals(choose, "3")) {
            removeEkipFromDriver();
        } else if (Objects.equals(choose, "4")) {
            findEkipByPrice();
        }
        else if (Objects.equals(choose, "5")) {
            sortByPrice();
        }
        else if (Objects.equals(choose, "6")) {
            sortByWeight();
        }


    }

    public static void createEkipir() {
        int intEkipirCost = 0;
        int intEkipirWeight = 0;

        System.out.println("\nДобавить новую Экипировку в каталог:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Экипировки: ");
        String newEkipirName = consoleChoose.nextLine();

        System.out.print("Цена экипировки: ");
        String ekipirCost = consoleChoose.nextLine();
        try {
            intEkipirCost = Integer.parseInt(ekipirCost);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        System.out.print("Вес экипировки: ");
        String ekipirWeight = consoleChoose.nextLine();
        try {
            intEkipirWeight = Integer.parseInt(ekipirWeight);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }


        Ekipir newEkipir = new Ekipir(newEkipirName, intEkipirCost, intEkipirWeight);
        existedEkipir.add(newEkipir);
        System.out.println(newEkipirName + " теперь в каталоге!\n");
        mainMenu();

    }


    public static void addEkipirToDriver() {
        if (existedEkipir.size() != 0) {
            int driverEkipCost = 0;
            int numeric = 1;

            System.out.println("\nВыбери Экипировку чтобы добавить");
            for (Ekipir ekipir : existedEkipir) {
                System.out.println(numeric + ") " + ekipir.getEkipirName() + " " + ekipir.getEkipirCost() + " руб. (" + ekipir.getEkipirWeight() + " кг)");
                numeric++;
            }

            try {
                Scanner consoleChoose = new Scanner(System.in);
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

                // Выбираем и добавляем конфету в подарок
                if (intChoose <= existedEkipir.size()) {
                    driverEkipir.add(existedEkipir.get(intChoose - 1));
                } else {
                    mainMenu();
                }

                numeric = 1;
                System.out.println("\nОбновленный список Экипировки водителя:");
                for (Ekipir drEkipir : driverEkipir) {
                    System.out.println(numeric + ") " + drEkipir.getEkipirName() + " " + drEkipir.getEkipirCost() + " руб. (" + drEkipir.getEkipirWeight() + " кг)");
                    numeric++;
                    driverEkipCost += drEkipir.getEkipirCost();
                }

                System.out.println("Полная стоимость Экипировки: " + driverEkipCost + " руб.");
                mainMenu();
            } catch (Exception e) {
                System.err.println("Неправильный формат строки!");
            }
        }
        else {
            System.out.println("В каталоге нет Экипировки");
            mainMenu();}

    }

    private static void removeEkipFromDriver() {
        int driverEkipCost = 0;
        int numeric = 1;

        if (driverEkipir.size() !=0) {
            System.out.println("\nВыбери Экипировку чтобы удалить");
            for (Ekipir drEkip : driverEkipir) {
                System.out.println(numeric + ") " + drEkip.getEkipirName() + " " + drEkip.getEkipirCost() + " руб. (" + drEkip.getEkipirWeight() + " кг)");
                numeric++;
            }

            try {
                Scanner consoleChoose = new Scanner(System.in);
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

                // Выбираем и добавляем конфету в подарок
                if (intChoose <= existedEkipir.size()) {
                    driverEkipir.remove(intChoose - 1);
                } else {
                    mainMenu();
                }

                numeric = 1;
                System.out.println("\nОбновленный список Экипировки водителя:");
                for (Ekipir drEkipir : driverEkipir) {
                    System.out.println(numeric + ") " + drEkipir.getEkipirName() + " " + drEkipir.getEkipirCost() + " руб. (" + drEkipir.getEkipirWeight() + " кг)");
                    numeric++;
                    driverEkipCost += drEkipir.getEkipirCost();
                }

                System.out.println("Полная стоимость Экипировки: " + driverEkipCost + " руб.");
                mainMenu();
            } catch (Exception e) {
                System.err.println("Неправильный формат строки!");
            }
        }
        else {
            System.out.println("У Водителя нет Экипировки");
            mainMenu();}

    }

    public static void findEkipByPrice() {
        if (driverEkipir.size() != 0) {
            System.out.println("\nВпишите Цену:");
            try {
                Scanner consoleChoose = new Scanner(System.in);

                System.out.print("От: ");
                String Ot = consoleChoose.nextLine();
                int OtInt = Integer.parseInt(Ot);  // Преобразование в целое число

                System.out.print("До: ");
                String Do = consoleChoose.nextLine();
                int DoInt = Integer.parseInt(Do);  // Преобразование в целое число

                for (Ekipir drEkip : driverEkipir) {
                    if (OtInt <= drEkip.getEkipirCost() & drEkip.getEkipirCost() <= DoInt) {
                        System.out.println("-" + drEkip.getEkipirName() + " " + drEkip.getEkipirCost() + " руб.");

                    }
                }
                mainMenu();
            } catch (Exception e) {
                System.err.println("Неправильный формат строки!");
            }
        }
        else {
            System.out.println("У Водителя нет Экипировки");
            mainMenu();}
    }

    public static void sortByPrice(){
        Collections.sort(driverEkipir, new Comparator<Ekipir>(){
            @Override
            public int compare(Ekipir e1, Ekipir e2){
                return Integer.compare(e1.getEkipirCost(), (e2.getEkipirCost()));
            }
        });
        System.out.println("\nОтсортировано по цене:");
        for (Ekipir ekip: driverEkipir){
            System.out.println(ekip.getEkipirName() + " Цена: " + ekip.getEkipirCost());
        }
        mainMenu();
    }

    public static void sortByWeight(){
        Collections.sort(driverEkipir, new Comparator<Ekipir>(){
            @Override
            public int compare(Ekipir e1, Ekipir e2){
                return Integer.compare(e1.getEkipirWeight(), (e2.getEkipirWeight()));
            }
        });
        System.out.println("\nОтсортировано по весу:");
        for (Ekipir ekip: driverEkipir){
            System.out.println(ekip.getEkipirName() + " Вес: " + ekip.getEkipirWeight());
        }
        mainMenu();
    }

}
