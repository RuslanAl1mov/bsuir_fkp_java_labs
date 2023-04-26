import java.util.List;
import java.util.Scanner;

public class UnblockSchet {
    public UnblockSchet(List<Schet> blockedSchets) {
        System.out.println("\nРАЗБЛОКИРОВАТЬ СЧЕТ:");
        if (blockedSchets.size() !=0) {
            int numeric = 0;

            System.out.println("\nСписок заблокированных счетов:");
            for (Schet clSchet : Main.blockedSchets) {
                numeric++;
                System.out.println(numeric + ") " + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.");
            }

            Scanner consoleChoose = new Scanner(System.in);

            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            try {
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число
                if (intChoose <= Main.blockedSchets.size()) {
                    Schet chosenSchet = Main.blockedSchets.get(intChoose - 1);
                    Main.blockedSchets.get(intChoose - 1).setBlock(false);
                    Main.existedSchets.add(Main.blockedSchets.get(intChoose - 1));
                    Main.blockedSchets.remove(intChoose-1);

                    System.out.println("Счет :: " + chosenSchet.getSchetName() + " :: - разблокирован!");
                } else {
                    System.err.println("Индекс вне диапазона списка");
                }
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }

        } else {
            System.out.println("Список блокированных счетов пуст!");
        }
    }
}
