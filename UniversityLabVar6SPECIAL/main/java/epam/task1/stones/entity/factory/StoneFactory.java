package epam.task1.stones.entity.factory;

import epam.task1.stones.entity.stone.PreciousStone;
import epam.task1.stones.entity.stone.SemipreciousStone;
import epam.task1.stones.entity.stone.Stone;
import epam.task1.stones.enums.Color;
import epam.task1.stones.enums.OpticalEffect;
import epam.task1.stones.enums.PreciousStoneType;
import epam.task1.stones.enums.SemipreciousStoneType;

import java.util.Random;


public class StoneFactory {
    public static Stone createStone(){
        Random random = new Random();
        switch (random.nextInt(2)){
            case 0:
                return new PreciousStone(
                        random.nextDouble() * 10 + 0.1,
                        random.nextDouble() * 1000,
                        Color.values()[(new Random().nextInt(Color.values().length))],
                        PreciousStoneType.values()[(new Random().nextInt(PreciousStoneType.values().length))]
                );
            case 1:
                return new SemipreciousStone(
                        random.nextDouble() * 10 + 0.1,
                        random.nextDouble() * 1000,
                        Color.values()[(new Random().nextInt(Color.values().length))],
                        SemipreciousStoneType.values()[(new Random().nextInt(SemipreciousStoneType.values().length))],
                        OpticalEffect.values()[(new Random().nextInt(OpticalEffect.values().length))]
                );

                default:
                    throw new IllegalArgumentException();
        }
    }
}
