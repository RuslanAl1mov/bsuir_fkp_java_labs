import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    private static ArrayList<Plain> existedPlains = new ArrayList<>();
    private static ArrayList<Plain> companyPlains = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("1) Создать Самолет");
        System.out.println("2) Добавить Самолет в авиакомпнию");
        System.out.println("3) Посмотреть самолеты");
        System.out.println("4) Найти Самолет по потребляемому топливу");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createPlain();
        } else if (Objects.equals(choose, "2")) {
            addPlainToCompany();
        } else if (Objects.equals(choose, "3")) {
            showCompanyPlains();
        } else if (Objects.equals(choose, "4")) {
            findPlainByFuelUse();
        }


    }

    public static void createPlain() {
        int intSeatsNum = 0;
        int intMaxWeight = 0;
        int intTripLong = 0;
        int intFuelIUse = 0;

        System.out.println("\nДобавить новый самолет:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Самолета: ");
        String newPlainName = consoleChoose.nextLine();

        System.out.print("Кол-во мест: ");
        String seatsNum = consoleChoose.nextLine();
        try {
            intSeatsNum = Integer.parseInt(seatsNum);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        System.out.print("Максимальная грузоподъемность: ");
        String maxWeight = consoleChoose.nextLine();
        try {
            intMaxWeight = Integer.parseInt(maxWeight);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        System.out.print("Максимальная дальность полета(км.): ");
        String tripLong = consoleChoose.nextLine();
        try {
            intTripLong = Integer.parseInt(tripLong);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        System.out.print("Кол-во потребляемого топлива: ");
        String fuelUse = consoleChoose.nextLine();
        try {
            intFuelIUse = Integer.parseInt(fuelUse);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }


        Plain newPlain = new Plain(newPlainName, intSeatsNum, intMaxWeight, intTripLong, intFuelIUse);
        existedPlains.add(newPlain);
        System.out.println(newPlainName + " Создан!\n");
        mainMenu();

    }


    public static void addPlainToCompany() {
        if (existedPlains.size() != 0) {
            int fullSeatsNum = 0;
            int numeric = 1;

            System.out.println("\nВыбери Самолет чтобы добавить");
            for (Plain plain : existedPlains) {
                System.out.println(numeric + ") " + plain.getPlainName() + " " + plain.getFuelUse() + " л/км " + plain.getSeatsNum() + " мест");
                numeric++;
            }

            try {
                Scanner consoleChoose = new Scanner(System.in);
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

                if (intChoose <= existedPlains.size()) {
                    companyPlains.add(existedPlains.get(intChoose - 1));
                } else {
                    mainMenu();
                }

                numeric = 1;
                System.out.println("\nОбновленный список Самолетов в авиакомпании:");
                for (Plain comPlain : companyPlains) {
                    System.out.println(numeric + ") " + comPlain.getPlainName() + " - " + comPlain.getFuelUse() + " л/км " + comPlain.getSeatsNum() + " мест");
                    numeric++;
                    fullSeatsNum += comPlain.getSeatsNum();
                }

                System.out.println("Полная Вместимость всех самолетов: " + fullSeatsNum + " мест");
                mainMenu();
            } catch (Exception e) {
                System.err.println("Неправильный формат строки!");
            }
        }
        else {
            System.out.println("В каталоге нет Самолетов");
            mainMenu();}

    }

    private static void showCompanyPlains() {
        int fullSeatsNum = 0;
        int fullWeight = 0;
        int numeric = 1;

        if (companyPlains.size() !=0) {
            System.out.println("\nСписок самолетов в авиакомпании:");
            for (Plain comPlain : companyPlains) {
                System.out.println(numeric + ") " + comPlain.getPlainName() + " --  Дальность полета  --  " + comPlain.getTripLong() + " км");
                numeric++;
                fullSeatsNum += comPlain.getSeatsNum();
                fullWeight += comPlain.getMaxWeight();
            }

            System.out.println("Полная Вместимость всех самолетов: " + fullSeatsNum + " мест");
            System.out.println("Полная Грузоподъемность: " + fullWeight + " т.");

            mainMenu();
        }
        else {
            System.out.println("У Компании нет Самолетов");
            mainMenu();}

    }

    public static void findPlainByFuelUse() {
        if (companyPlains.size() != 0) {
            System.out.println("\nВпишите кол-во потребляемого топлива:");
            try {
                Scanner consoleChoose = new Scanner(System.in);

                System.out.print("От: ");
                String Ot = consoleChoose.nextLine();
                int OtInt = Integer.parseInt(Ot);  // Преобразование в целое число

                System.out.print("До: ");
                String Do = consoleChoose.nextLine();
                int DoInt = Integer.parseInt(Do);  // Преобразование в целое число

                for (Plain comPlain : companyPlains) {
                    if (OtInt <= comPlain.getFuelUse() & comPlain.getFuelUse() <= DoInt) {
                        System.out.println("-- " + comPlain.getPlainName() + " --  Потребление топлива  --  " + comPlain.getFuelUse() + " л/км");

                    }
                }
                mainMenu();
            } catch (Exception e) {
                System.err.println("Неправильный формат строки!");
            }
        }
        else {
            System.out.println("У Компаниии нет самолетов");
            mainMenu();}
    }

}
