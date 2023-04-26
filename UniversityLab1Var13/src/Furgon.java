import java.util.ArrayList;

public class Furgon {
    private String name;  // название фургона
    private int maxWeight;  // максимальный вес
    private int sumToLoad;  // сумма на которую загружают фургон
    private ArrayList<Coffee> coffees = new ArrayList<>();


    public Furgon (String name, int maxWeight, int sumToLoad){
        this.name = name;
        this.maxWeight = maxWeight;
        this.sumToLoad = sumToLoad;
    }

    public String getName() {
        return name;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getSumToLoad() {
        return sumToLoad;
    }

    public void addCoffee(Coffee coffee) {
        this.coffees.add(coffee);
    }

    public ArrayList<Coffee> getFurgonCoffees(){
        return coffees;
    }
}
