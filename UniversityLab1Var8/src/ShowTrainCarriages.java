import java.util.List;

public class ShowTrainCarriages {
    public ShowTrainCarriages(List<Carriage> trainCarriages) {
        System.out.println("\nВАГОНЫ В СОСТАВЕ ПОЕЗДА:");
        if (trainCarriages.size() != 0) {
            int numeric = 0;
            int fullSeatsNumber = 0;
            int fullPackageNum = 0;
            for (Carriage carriage : trainCarriages) {
                numeric++;
                System.out.println(numeric + ") Уникальный номер вагона:" + carriage.getName() + "\n\tКол-во мест: " + carriage.getSeatsNumber() +
                        " \n\tВместимость багажа: " + carriage.getPackageNumber() + " кг.\n\tУровень комфорта: " + carriage.getComfortLevel() + "/10");
                fullSeatsNumber = fullSeatsNumber + carriage.getSeatsNumber();
                fullPackageNum = fullPackageNum + carriage.getPackageNumber();
            }
            System.out.println("\nОбщее кол-во мест: " + fullSeatsNumber);
            System.out.println("Общая вместимость багажа: " + fullPackageNum);
        } else {System.out.println("Список вагонов в составе пуст!");}
    }
}
