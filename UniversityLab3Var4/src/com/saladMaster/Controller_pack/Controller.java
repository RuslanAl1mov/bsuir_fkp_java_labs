package com.saladMaster.Controller_pack;

import com.saladMaster.Administrator.CreateSaladWindow.entity.Salad;
import com.saladMaster.Administrator.CreateVegetableWindow.entity.Vegetable;

import java.io.*;
import java.util.*;

public class Controller {
    private List<Vegetable> vegetables = new ArrayList<>();
    private List<Salad> salads = new ArrayList<>();
    private final String VEGETABLES_FILE_PATH = "src\\com\\saladMaster\\Controller_pack\\Memory\\Vegetables.txt";
    private final String SALADS_FILE_PATH = "src\\com\\saladMaster\\Controller_pack\\Memory\\Salads.txt";

    public Controller() {
        try {
            loadVegetablesFile();
            loadSaladsFile();
        } catch (Exception e) {
            System.out.println("");
        }
    }

    public void createVegetable(String name, int kal) {
        Vegetable newVegetable = new Vegetable(name, kal);
        vegetables.add(newVegetable);
        saveVegetablesInFile();
    }


    public void createSalad(String name, List<Vegetable> saladVegetables){
        Salad newSalad = new Salad(name, saladVegetables);
        salads.add(newSalad);
        saveSaladsInFile();
    }

    public void removeSalad(Salad salad){
        salads.remove(salad);
        saveSaladsInFile();
    }

    public List<Salad> getSalads() {
        return salads;
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public void sortSaladsByKal(){
        Collections.sort(salads, new Comparator<Salad>() {
            @Override
            public int compare(Salad s1, Salad s2) {
                return Integer.compare(s1.getKalNum(), (s2.getKalNum()));
            }
        });
    }

    public void sortSaladsByVegNum(){
        Collections.sort(salads, new Comparator<Salad>() {
            @Override
            public int compare(Salad s1, Salad s2) {
                return Integer.compare(s1.getSaladVegetables().size(), (s2.getSaladVegetables().size()));
            }
        });
    }

    public void saveVegetablesInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(VEGETABLES_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(vegetables);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSaladsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(SALADS_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(salads);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadVegetablesFile(){
        try {
            FileInputStream fis = new FileInputStream(VEGETABLES_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.vegetables = (List<Vegetable>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadSaladsFile(){
        try {
            FileInputStream fis = new FileInputStream(SALADS_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.salads = (List<Salad>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
