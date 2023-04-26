/*
 * Система Автобаза. Диспетчер распределяет заявки на Рейсы между Водителями и назначает для этого Автомобиль.
 * Водитель может сделать заявку на ремонт. Диспетчер может отстранить Водителя от работы. Водитель делает
 * отметку о выполнении Рейса и состоянии Автомобиля.
 */


class Driver{
    private String firstName;
    private String secondName;
    private Car car;
    private Application application;


    public Driver (String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }


    // Получить имя водителя
    public String getFirstName() {
        return firstName;
    }

    // Получить фамилию водителя
    public String getSecondName() {
        return secondName;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    // Получить название машины
    public String getCarName() {
        return car.getMark();
    }

    // Получить пробег машины
    public int getCarMileage() {
        return car.getMileage();
    }

    // Получить статус машины на ремонте

    public boolean getCarRepairStatus() {
        return car.getOnRepairStatus();
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Car getCar() {
        return car;
    }
}
