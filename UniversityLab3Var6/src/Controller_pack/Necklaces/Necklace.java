package Controller_pack.Necklaces;

import Controller_pack.Stones.Stone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Necklace implements Serializable {
    private String name;
    private List<Stone> necklaceStones = new ArrayList<>();


    public Necklace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNecklaceStones(List<Stone> stones) {
        this.necklaceStones = stones;
    }

    public List<Stone> getNecklaceStones() {
        return necklaceStones;
    }

    public float getNecklacePrice() {
        float necklaceCost = 0;
        for (Stone stone : necklaceStones){
            necklaceCost = necklaceCost + stone.getPrice();
        }
        return necklaceCost;
    }

    public float getNecklaceWeight() {
        float necklaceCost = 0;
        for (Stone stone : necklaceStones){
            necklaceCost = necklaceCost + stone.getWeight();
        }
        return necklaceCost;
    }


}

