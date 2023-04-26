package epam.task1.stones.entity.factory;

import epam.task1.stones.entity.chain.Chain;
import epam.task1.stones.enums.ChainType;
import epam.task1.stones.enums.Fineness;

import java.util.Random;


public class ChainFactory {
    public static Chain createChain(){
        Random random = new Random();
        return new Chain(
                ChainType.values()[(new Random().nextInt(ChainType.values().length))],
                random.nextDouble() * 10 + 0.1,
                Fineness.values()[(new Random().nextInt(Fineness.values().length))]);
    }
}

