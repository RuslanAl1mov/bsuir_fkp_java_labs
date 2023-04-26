import java.util.ArrayList;

class Coffee {
    private String name;
    private String phisical;
    private int price;
    private int packageWeight;
    private int coffeeWeightInFurgon;

    public Coffee (String name, String phisical, int packageWeight, int price){
        this.name = name;
        this.phisical = phisical;
        this.packageWeight = packageWeight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPhisical() {
        return phisical;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setCoffeeWeightInFurgon(int coffeeWeightInFurgon) {
        this.coffeeWeightInFurgon = coffeeWeightInFurgon;
    }

    public int getCoffeeWeightInFurgon() {
        return coffeeWeightInFurgon;
    }

    public int getPrice() {
        return price;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }
}
