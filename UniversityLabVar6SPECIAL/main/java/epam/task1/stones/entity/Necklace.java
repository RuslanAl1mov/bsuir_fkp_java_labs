package epam.task1.stones.entity;

import epam.task1.stones.entity.chain.Chain;
import epam.task1.stones.entity.stone.Stone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Necklace {

    private List<Stone> stonesList = new ArrayList<Stone>();
    private Chain chain;
    private String FILE_PATH = "Kamni.data";


    public Necklace() {
    }

    public List<Stone> getStonesList() {
        return stonesList;
    }

    public void setStonesList(List<Stone> stonesList) {
        this.stonesList = stonesList;
    }

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    @Override
    public String toString() {
        return "\nОжерелье{" +
                "цепочка = " + chain +
                ", каталог = " + stonesList +
                '}';
    }

    public String saveStonesInFile(){  // Сохранение существующих камней в файл
        try {
            FileOutputStream fos= new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(stonesList);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "mistake";

        }
        return "Done";
    }

    public void loadFile(){  // Загрузка сохраненных камней из файла
        System.out.println("Последние сохраненные камни:");
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Stone> stonesList = (List<Stone>) ois.readObject();
            setStonesList(stonesList);
            // stonesList.clear();
            for (Stone stone: stonesList){
                System.out.print(stone);
            }
            System.out.println("\n\n\t-----\t-----\t----\t----\t----\t-----\t-----\t-----\t----\t----\n");


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
