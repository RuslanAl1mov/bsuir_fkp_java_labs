import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сортировка машин по расходу топлива
public class SortCarsByFuelUse {

    public SortCarsByFuelUse(List<Car> cars){
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return Integer.compare(c1.getCarFuelUse(), (c2.getCarFuelUse()));
            }});

        for (Car car : cars) {
            System.out.println("  - "+car.getCarName()+"\n\tСкорость: "+car.getCarMaxSpeed()+" км/ч.\n\tРасход: " + car.getCarFuelUse() + "\n\tЦена: " + car.getCarPrice());}
    }



}
