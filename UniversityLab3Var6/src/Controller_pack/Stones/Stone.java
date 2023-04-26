package Controller_pack.Stones;

import java.io.Serializable;

public class Stone implements Serializable {
    private  String name;  // Название камня
    private float price;  // Цена
    private String color;  // цвет
    private String type;  // тип камня
    private float weight;  // тип камня

    public Stone(String name, String color, String type, Float price, Float weight) {
        this.name = name;
        this.color = color;
        this.type = type;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public float getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}

