package com.bolnica.Doctor.AddNewDrugWindow.entity;

import com.bolnica.Doctor.PrescribeTreatmentWindow.entity.DrugMaster;

import java.io.Serializable;

public class Drugs implements Serializable {
    private final String drugName;
    private DrugMaster drugMaster;


    public Drugs(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugName() {
        return drugName;
    }

    public DrugMaster getDrugMaster() {
        return drugMaster;
    }

    public void setDrugMaster(DrugMaster drugMaster) {
        this.drugMaster = drugMaster;
    }
}

