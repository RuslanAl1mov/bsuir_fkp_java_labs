package com.bolnica.Patien.entity;

import com.bolnica.Doctor.AddNewDrugWindow.entity.Drugs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable {
    private final String firstName;
    private final String secondName;
    private final int age;
    private boolean status = false;
    private List<Drugs> patientDrugs = new ArrayList<>();

    public Patient(String firstName, String secondName, int age){
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public List<Drugs> getPatientDrugs() {
        return patientDrugs;
    }

    public void setPatientDrugs(List<Drugs> patientDrugs) {
        this.patientDrugs = patientDrugs;
    }

    public void addPatientDrug(Drugs patientDrug) {
        this.patientDrugs.add(patientDrug);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
