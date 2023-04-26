import java.util.List;
import java.util.Scanner;

public class UnblockSchet {
    public UnblockSchet(List<Schet> blockedSchets, List<Schet> existedSchets) {
        System.out.println("\nРАЗБЛОКИРОВАТЬ СЧЕТ:");
        if (blockedSchets.size() !=0) {
            int numeric = 0;

            System.out.println("\nСписок заблокированных счетов:");
            for (Schet clSchet : blockedSchets) {
                numeric++;
                System.out.println(numeric + ") " + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.");
            }

            Scanner consoleChoose = new Scanner(System.in);

            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            try {
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число
                if (intChoose <= blockedSchets.size()) {
                    Schet chosenSchet = blockedSchets.get(intChoose - 1);
                    blockedSchets.get(intChoose - 1).setBlock(false);
                    existedSchets.add(blockedSchets.get(intChoose - 1));
                    blockedSchets.remove(intChoose-1);

                    System.out.println("Счет :: " + chosenSchet.getSchetName() + " :: - разблокирован!");
                } else {
                    System.err.println("Индекс вне диапазона списка");
                }
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }
        } else {
            try {
                throw new NoSchetException("Список блокированных счетов пуст!");
            } catch (NoSchetException e) {
                System.err.println(e.getMessage());
                System.out.println("");
            }
        }
    }
}
