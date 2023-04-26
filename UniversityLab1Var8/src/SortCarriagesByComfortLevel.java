import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сортировка вагонов по уровню комфорта
public class SortCarriagesByComfortLevel {

    public SortCarriagesByComfortLevel(List<Carriage> trainCarriages) {

        if (trainCarriages.size() != 0) {
            Collections.sort(trainCarriages, new Comparator<Carriage>() {
                @Override
                public int compare(Carriage c1, Carriage c2) {
                    return Integer.compare(c1.getComfortLevel(), (c2.getComfortLevel()));
                }
            });

            for (Carriage carriage : trainCarriages) {
                System.out.println(" - Уникальный номер вагона:" + carriage.getName() + "\n\tКол-во мест: " + carriage.getSeatsNumber() +
                        " \n\tВместимость багажа: " + carriage.getPackageNumber() + " кг.\n\tУровень комфорта: " + carriage.getComfortLevel() + "/10");
            }
        } else {
            System.out.println("В составе поезда нет вагонов!");
        }
    }
}
