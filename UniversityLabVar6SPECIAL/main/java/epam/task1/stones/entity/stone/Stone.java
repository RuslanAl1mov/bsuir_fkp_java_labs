package epam.task1.stones.entity.stone;

import epam.task1.stones.enums.Color;

import java.io.Serializable;


public abstract class Stone implements Serializable {

    private double weight;
    private double price;
    private Color color;

    public Stone(){

    }

    public Stone(double weight, double price, Color color) {
        this.weight = weight;
        this.price = price;
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "\nКамень{" +
                "цвет= " + color +
                ", вес= " + String.format("%8.3f",weight) +
                ", стоимость= " + String.format("%8.2f",price) +
                '}';
    }
}
