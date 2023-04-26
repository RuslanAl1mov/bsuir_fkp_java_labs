package epam.task1.stones.logic;

import epam.task1.stones.entity.stone.Stone;
import epam.task1.stones.enums.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Actions {
    public static void stonesSortByPrice(List<Stone> stones){
        Collections.sort(stones, new Comparator<Stone>() {
            public int compare(Stone o1, Stone o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
    }

    public static void stonesSortByWeight(List<Stone> stones){
        Collections.sort(stones, new Comparator<Stone>() {
            public int compare(Stone o1, Stone o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        });
    }

    public static List<Stone> findStoneByColor(String color, List<Stone> stones){
        List<Stone> foundStones = new ArrayList();
        for (Stone stone : stones){
            if ((Color.valueOf(color)).equals(stone.getColor())){
                foundStones.add(stone);
            }
        }
        return foundStones;
    }
}
