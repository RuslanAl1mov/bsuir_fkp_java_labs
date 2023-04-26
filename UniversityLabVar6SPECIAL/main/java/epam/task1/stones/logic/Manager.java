package epam.task1.stones.logic;

import epam.task1.stones.entity.Necklace;
import epam.task1.stones.entity.factory.ChainFactory;
import epam.task1.stones.entity.factory.StoneFactory;
import epam.task1.stones.entity.stone.Stone;

import java.util.ArrayList;
import java.util.List;


public class Manager {

    public Necklace createNecklace(Necklace necklace, int countStone){
        List<Stone> stones = new ArrayList<Stone>();
        for (int i = 0; i < countStone; i++){
            stones.add(StoneFactory.createStone());
        }
        necklace.setStonesList(stones);
        necklace.setChain(ChainFactory.createChain());
        return necklace;
    }

    public double calculateTotalCaratWeight(List<Stone> stones) {
        double totalCaratWeight = 0.0;
        for (Stone stone : stones) {
            totalCaratWeight += stone.getWeight();
        }
        return totalCaratWeight;
    }

    public int calculateTotalStonesCost(List<Stone> stones){
        int totalCost = 0;
        for (Stone stone : stones){
            totalCost += stone.getPrice() * stone.getWeight();
        }

        return totalCost;
    }
}
