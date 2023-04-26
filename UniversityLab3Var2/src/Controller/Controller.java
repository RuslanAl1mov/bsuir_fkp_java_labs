package Controller;


import Administrator.CreateCandyWindow.entity.Candy;
import Administrator.CreatePresentWindow.entity.Present;

import java.io.*;
import java.util.*;

public class Controller {
    private List<Candy> candies = new ArrayList<>();
    private List<Present> presents = new ArrayList<>();
    private String Candies_FILE_PATH = "src\\Controller\\Memory\\Candies.data";
    private String Presents_FILE_PATH = "src\\Controller\\Memory\\Presents.data";

    public Controller() {
        try {
            loadCandiesFile();
            loadPresentsFile();
        } catch (Exception e){
            System.out.println("");
        }

    }

    public void createCandy(String newCandyName, int newSugarNum, int newCandyWeight) {
        Candy newCandy = new Candy(newCandyName, newSugarNum, newCandyWeight);
        candies.add(newCandy);
        saveCandiesInFile();
    }

    public void createPresent(String presentName, List<Candy> presentCandies){
        Present newPresent = new Present(presentName, presentCandies);
        presents.add(newPresent);
        savePresentsInFile();
    }

    public List<Present> getPresents() {
        return presents;
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public void sortCandiesBySugar(){
        Collections.sort(candies, new Comparator<Candy>() {
            @Override
            public int compare(Candy c1, Candy c2) {
                return Integer.compare(c1.getSugarNum(), (c2.getSugarNum()));
            }
        });
    }

    public void sortCandiesByWeight(){
        Collections.sort(candies, new Comparator<Candy>() {
            @Override
            public int compare(Candy c1, Candy c2) {
                return Integer.compare(c1.getCandyWeight(), (c2.getCandyWeight()));
            }
        });
    }

    public void sortPresentsByCandiesNum(){
        Collections.sort(presents, new Comparator<Present>() {
            @Override
            public int compare(Present p1, Present p2) {
                return Integer.compare(p1.getPresentCandies().size(), (p2.getPresentCandies().size()));
            }
        });
    }

    public void sortPresentsByWeight(){
        Collections.sort(presents, new Comparator<Present>() {
            @Override
            public int compare(Present p1, Present p2) {
                return Integer.compare(p1.getPresentWeight(), (p2.getPresentWeight()));
            }
        });
    }


    public void saveCandiesInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Candies_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(candies);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePresentsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Presents_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(presents);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadCandiesFile(){
        try {
            FileInputStream fis = new FileInputStream(Candies_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.candies = (List<Candy>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadPresentsFile(){
        try {
            FileInputStream fis = new FileInputStream(Presents_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.presents = (List<Present>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
