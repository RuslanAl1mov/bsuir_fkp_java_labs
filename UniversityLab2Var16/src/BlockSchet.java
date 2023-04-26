import java.util.List;
import java.util.Scanner;

public class BlockSchet {

    public BlockSchet(List<Schet> existedSchets, List<Schet> blockedSchets) {
        System.out.println("\nЗАБЛОКИРОВАТЬ СЧЕТ:");

        if (existedSchets.size() !=0) {
            int numeric = 0;

            System.out.println("\nСписок счетов:");
            for (Schet clSchet : existedSchets) {
                numeric++;
                System.out.println(numeric + ") " + clSchet.getSchetName() + " ::: " + clSchet.getSchetSum() + " руб.");
            }

            Scanner consoleChoose = new Scanner(System.in);

            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            try {
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число
                if (intChoose <= Main.existedSchets.size()) {
                    Schet chosenSchet = existedSchets.get(intChoose - 1);
                    existedSchets.get(intChoose - 1).setBlock(true);
                    blockedSchets.add(existedSchets.get(intChoose - 1));
                    existedSchets.remove(intChoose-1);

                    System.out.println("Счет :: " + chosenSchet.getSchetName() + " :: - заблокирован!");
                } else {
                    System.err.println("Индекс вне диапазона списка");
                }
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }

        }else {
            try {
                throw new NoSchetException("Список счетов пуст!");
            } catch (NoSchetException e) {
                System.err.println(e.getMessage());
                System.out.println("");
            }
        }
    }
}
