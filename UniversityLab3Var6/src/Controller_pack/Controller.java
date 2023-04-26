package Controller_pack;

import Controller_pack.Necklaces.Necklace;
import Controller_pack.Stones.Stone;

import java.io.*;
import java.util.*;

public class Controller {
    private List<Stone> stones = new ArrayList<>();
    private List<Necklace> necklaces = new ArrayList<>();
    private String Necklaces_FILE_PATH = "src\\Controller_pack\\Memory\\Necklace.txt";
    private String Stones_FILE_PATH = "src\\Controller_pack\\Memory\\Stones.txt";

    public Controller() {
        loadStonesFile();
        loadNecklacesFile();
    }

    public void createStone(String name, String color, String type, float price, float weight) {
        Stone newStone = new Stone(name, color, type, price, weight);
        stones.add(newStone);
        saveStonesInFile();
    }

    public void createNecklace(String name, List<Stone> necklaceStones){
        Necklace newNecklace = new Necklace(name);
        newNecklace.setNecklaceStones(necklaceStones);
        necklaces.add(newNecklace);
        saveNicklacesInFile();
    }

    public List<Necklace> getNecklaces() {
        return necklaces;
    }

    public List<Stone> getStones() {
        return stones;
    }

    public void sortNecklacesByPrice(){
        Collections.sort(necklaces, new Comparator<Necklace>() {
            @Override
            public int compare(Necklace b1, Necklace b2) {
                return Float.compare(b1.getNecklacePrice(), (b2.getNecklacePrice()));
            }
        });
    }

    public void sortNecklacesByWeight(){
        Collections.sort(necklaces, new Comparator<Necklace>() {
            @Override
            public int compare(Necklace b1, Necklace b2) {
                return Float.compare(b1.getNecklaceWeight(), (b2.getNecklaceWeight()));
            }
        });
    }

    public void saveStonesInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Stones_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(stones);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNicklacesInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Necklaces_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(necklaces);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadStonesFile(){
        try {
            FileInputStream fis = new FileInputStream(Stones_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.stones = (List<Stone>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadNecklacesFile(){
        try {
            FileInputStream fis = new FileInputStream(Necklaces_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.necklaces = (List<Necklace>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
