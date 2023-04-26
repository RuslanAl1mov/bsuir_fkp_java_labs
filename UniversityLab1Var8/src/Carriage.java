public class Carriage {
    private final String name;
    private final int seatsNumber;
    private final int packageNumber;
    private int comfortLevel;


    public Carriage(String name, int seatsNumber, int packageNumber, int comfortLevel) {
        this.name = name;
        this.seatsNumber = seatsNumber;
        this.packageNumber = packageNumber;
        this.comfortLevel = comfortLevel;
    }

    public String getName() {
        return name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(int comfortLevel) {
        this.comfortLevel = comfortLevel;
    }
}

