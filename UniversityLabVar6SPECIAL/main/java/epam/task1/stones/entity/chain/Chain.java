package epam.task1.stones.entity.chain;

import epam.task1.stones.enums.ChainType;
import epam.task1.stones.enums.Fineness;


public class Chain {

    private ChainType material;
    private double cost;
    private Fineness fineness;

    public Chain(){

    }

    public Chain(ChainType material, double cost, Fineness fineness) {
        super();
        this.material = material;
        this.cost = cost;
        this.fineness = fineness;
    }

    public ChainType getMaterial() {
        return material;
    }

    public void setMaterial(ChainType material) {
        this.material = material;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Fineness getFineness() {
        return fineness;
    }

    public void setFineness(Fineness fineness) {
        this.fineness = fineness;
    }

    public String toString() {
        return "Цепочка{" +
                "материал = " + this.getMaterial() +
                ", стоимость = " +  String.format("%8.3f",this.getCost()) +
                ", проба = " + this.getFineness() +
                '}';
    }
}
