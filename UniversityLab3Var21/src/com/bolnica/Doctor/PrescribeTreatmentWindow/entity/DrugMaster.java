package com.bolnica.Doctor.PrescribeTreatmentWindow.entity;

import java.io.Serializable;

public class DrugMaster implements Serializable {
    private final String firstName;
    private final String secondName;
    private final String status;

    public DrugMaster(String firstName, String secondName, String status){
        this.firstName = firstName;
        this.secondName = secondName;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getStatus() {
        return status;
    }
}
