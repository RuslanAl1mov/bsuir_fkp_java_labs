public class Car{
    private String carName;
    private int carPrice;
    private int carMaxSpeed;
    private int carFuelUse;


    public Car(String carName, int carPrice, int carMaxSpeed, int carFuelUse) {
        this.carName = carName;
        this.carPrice = carPrice;
        this.carMaxSpeed = carMaxSpeed;
        this.carFuelUse = carFuelUse;
    }

    // Задать новое название машине
    public void setCarName(String carName) {
        this.carName = carName;
    }

    // Получить название машины
    public String getCarName() {
        return carName;
    }

    // Получить цену на машину
    public int getCarPrice() {
        return carPrice;
    }

    // Получить макисмальную скорость машины
    public int getCarMaxSpeed(){
        return carMaxSpeed;
    }

    // Получить макисмальную скорость машины
    public int getCarFuelUse(){
        return carFuelUse;
    }
}

