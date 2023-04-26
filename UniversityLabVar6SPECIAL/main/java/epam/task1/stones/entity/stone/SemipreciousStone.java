package epam.task1.stones.entity.stone;

import epam.task1.stones.enums.Color;
import epam.task1.stones.enums.OpticalEffect;
import epam.task1.stones.enums.SemipreciousStoneType;


public class SemipreciousStone extends Stone {

    private SemipreciousStoneType name;
    private OpticalEffect opticalEffect;

    public SemipreciousStone() {
        super();
    }

    public SemipreciousStone(double weight, double price, Color color, SemipreciousStoneType name, OpticalEffect opticalEffect) {
        super(weight, price, color);
        this.name = name;
        this.opticalEffect = opticalEffect;
    }

    public SemipreciousStoneType getName() {
        return name;
    }

    public void setName(SemipreciousStoneType name) {
        this.name = name;
    }

    public OpticalEffect getOpticalEffect() {
        return opticalEffect;
    }

    public void setOpticalEffect(OpticalEffect opticalEffect) {
        this.opticalEffect = opticalEffect;
    }

    @Override
    public String toString() {
        return "\nПолудрагоценный камень{" +
                "название = " + this.getName() +
                ", цвет = " + this.getColor() +
                ", дисперсия = " + this.getOpticalEffect()+
                ", вес = " + String.format("%8.3f",this.getWeight()) +
                ", стоимость = " +  String.format("%8.2f",this.getPrice()) +
                '}';
    }
}
