package com.saladMaster.Administrator.CreateSaladWindow.entity;

import com.saladMaster.Administrator.CreateVegetableWindow.entity.Vegetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Salad implements Serializable {
    private final String saladName;
    private List<Vegetable> saladVegetables = new ArrayList<>();

    public Salad(String saladName, List<Vegetable> saladVegetables){
        this.saladName = saladName;
        this.saladVegetables = saladVegetables;
    }

    public String getSaladName() {
        return saladName;
    }

    public List<Vegetable> getSaladVegetables() {
        return saladVegetables;
    }

    public void addVegetable(Vegetable vegetable){
        saladVegetables.add(vegetable);
    }

    public int getKalNum(){
        int kalNum = 0;
        for (Vegetable vegetable:saladVegetables){
            kalNum = kalNum + vegetable.getKalNum();
        }
        return kalNum;
    }
}
