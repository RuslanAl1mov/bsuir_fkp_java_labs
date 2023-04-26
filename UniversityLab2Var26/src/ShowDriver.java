import java.util.List;

public class ShowDriver {
    public ShowDriver(List<Driver> orderOnAir, List<Driver> finishedOrders, List<Driver> onRepairCars) {
        System.out.println("\nПОКАЗАТЬ ВСЕХ ВОДИТЕЛЕЙ");

        System.out.println("Водители на вызове:");
        if (orderOnAir.size() != 0) {
            for (Driver driver : orderOnAir) {
                System.out.println("\tВодитель: " + driver.getFirstName() + " " + driver.getSecondName());
                System.out.println("\tМашина: " + driver.getCarName() + " (" + driver.getCarMileage() + " км.)");
                System.out.println("\tЗаявка: " + driver.getApplication().getStartAddress() + " ==> " + driver.getApplication().getFinishAddress() + "\n");
            }
        } else System.out.println("НЕТ ВОДИТЕЛЕЙ НА ВЫЗОВЕ!");

        System.out.println("Водители завершившие заявки:");
        if (finishedOrders.size() != 0) {
            for (Driver driver : finishedOrders) {
                System.out.println("\tВодитель: " + driver.getFirstName() + " " + driver.getSecondName());
                System.out.println("\tМашина: " + driver.getCarName() + " (" + driver.getCarMileage() + " км.)");
                System.out.println("\tЗаявка: " + driver.getApplication().getStartAddress() + " ==> " + driver.getApplication().getFinishAddress() + "\n");
            }
        } else System.out.println("НЕТ ВОДИТЕЛЕЙ ЗАВЕРШИВШИХ ЗАКАЗ!");

        System.out.println("Водители на ремонте:");
        if (onRepairCars.size() != 0) {
            for (Driver driver : onRepairCars) {
                System.out.println("\tВодитель: " + driver.getFirstName() + " " + driver.getSecondName());
                System.out.println("\tМашина: " + driver.getCarName() + " (" + driver.getCarMileage() + " км.)\n");
            }
        } else System.out.println("НЕТ МАШИН НА РЕМОНТЕ!");
    }
}
