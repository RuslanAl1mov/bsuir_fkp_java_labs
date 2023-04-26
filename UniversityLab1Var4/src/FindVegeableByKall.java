import java.util.List;
import java.util.Scanner;

public class FindVegeableByKall {
    public FindVegeableByKall(List<Vegetable> salad) {
        System.out.println("");
        System.out.println("Впишите кол-во калорий:");
        try {

            Scanner consoleChoose = new Scanner(System.in);

            System.out.print("От: ");
            String Ot = consoleChoose.nextLine();
            int OtInt = Integer.parseInt(Ot);  // Преобразование в целое число

            System.out.print("До: ");
            String Do = consoleChoose.nextLine();
            int DoInt = Integer.parseInt(Do);  // Преобразование в целое число

            for (Vegetable saladVeg : salad) {
                if (OtInt <= saladVeg.getKalNum() &  saladVeg.getKalNum() <= DoInt) {
                    System.out.println("-" + saladVeg.getVegetableName() + " " + saladVeg.getKalNum() + " калл.");

                }
            }
        }
        catch (Exception e) {
            System.err.println("Неправильный формат строки!");
        }
    }

}
