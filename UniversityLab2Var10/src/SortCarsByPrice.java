import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сортировка машин по цене
public class SortCarsByPrice {

    public SortCarsByPrice(List<Car> cars) {
        if (cars.size() == 0) {
            Collections.sort(cars, new Comparator<Car>() {
                @Override
                public int compare(Car c1, Car c2) {
                    return Integer.compare(c1.getCarPrice(), (c2.getCarPrice()));
                }
            });

            for (Car car : cars) {
                System.out.println("  - " + car.getCarName() + "\n\tСкорость: " + car.getCarMaxSpeed() + " км/ч.\n\tРасход: " + car.getCarFuelUse() + "\n\tЦена: " + car.getCarPrice());
            }
        } else {
            try {
                throw new NoCarsException("Список машин пуст!");
            } catch (NoCarsException e) {
                System.err.println(e.getMessage());
                System.out.println(" ");
            }
        }
    }
}
