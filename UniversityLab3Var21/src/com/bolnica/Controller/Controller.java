package com.bolnica.Controller;

import com.bolnica.Doctor.AddNewDrugWindow.entity.Drugs;
import com.bolnica.Patien.entity.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Drugs> drugs = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    private final String Drugs_FILE_PATH = "src\\com\\bolnica\\Controller\\Memory\\Drugs.txt";
    private final String Patients_FILE_PATH = "src\\com\\bolnica\\Controller\\Memory\\Patients.txt";

    public Controller() {
        loadDrugsFile();
        loadPatientsFile();
    }

    public void createDrug(String drugName) {
        Drugs newDrug = new Drugs(drugName);
        drugs.add(newDrug);
        saveDrugsInFile();
    }

    public void createPatient(String firstName, String secondName, int age){
        Patient newPatient = new Patient(firstName, secondName, age);
        patients.add(newPatient);
        savePatientsInFile();
    }

    public List<Drugs> getDrugs() {
        return drugs;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void prescribeTreatment(Patient patient, Drugs drugs){
        patient.addPatientDrug(drugs);
        savePatientsInFile();
    }

//    public void sortBucketsByPrice(){
//        Collections.sort(buckets, new Comparator<Bucket>() {
//            @Override
//            public int compare(Bucket b1, Bucket b2) {
//                return Integer.compare(b1.getBucketPrice(), (b2.getBucketPrice()));
//            }
//        });
//    }

    public void saveDrugsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Drugs_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(drugs);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePatientsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Patients_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(patients);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadDrugsFile(){
        try {
            FileInputStream fis = new FileInputStream(Drugs_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.drugs = (List<Drugs>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadPatientsFile(){
        try {
            FileInputStream fis = new FileInputStream(Patients_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.patients = (List<Patient>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
