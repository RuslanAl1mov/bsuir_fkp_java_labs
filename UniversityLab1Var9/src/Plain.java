public class Plain {
    private String plainName;
    private int seatsNum;
    private int maxWeight;
    private int tripLong;
    private int fuelUse;


    public Plain(String plainName, int seatsNum, int maxWeight, int tripLong, int fuelUse) {
        this.plainName = plainName;
        this.seatsNum = seatsNum;
        this.maxWeight = maxWeight;
        this.tripLong = tripLong;
        this.fuelUse = fuelUse;
    }

    // Задать новое название самолета
    public void setPlainName(String plainName) {
        this.plainName = plainName;
    }

    // Получить название самолета
    public String getPlainName() {
        return plainName;
    }

    // Изменить кол-во мест в самолете
    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    // Получить кол-во мест в самолете
    public int getSeatsNum() {
        return seatsNum;
    }

    // Изменить максимальную грузоподъемность самолета
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    // Получить максимальную грузоподъемность самолета
    public int getMaxWeight() {
        return maxWeight;
    }

    // Изменить дальность полета
    public void setTripLong(int tripLong) {
        this.tripLong = tripLong;
    }

    // Получить дальность полета
    public int getTripLong() {
        return tripLong;
    }

    // Изменить Потребление топлива
    public void setFuelUse(int fuelUse) {
        this.fuelUse = fuelUse;
    }

    // Получить Потребление топлива
    public int getFuelUse() {
        return fuelUse;
    }
}