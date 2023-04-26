import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCarsBySpeed {

    // Сортировка машин по скорости
    public SortCarsBySpeed(List<Car> cars) {
        if (cars.size() != 0) {
            Collections.sort(cars, new Comparator<Car>() {
                @Override
                public int compare(Car c1, Car c2) {
                    return Integer.compare(c1.getCarMaxSpeed(), (c2.getCarMaxSpeed()));
                }
            });

            for (Car car : cars) {
                System.out.println("  - " + car.getCarName() + "\n\tСкорость: " + car.getCarMaxSpeed() + " км/ч.\n\tРасход: " + car.getCarFuelUse() + "\n\tЦена: " + car.getCarPrice());
            }
        } else{ System.out.println("Список машин пуст!");}
    }
}
