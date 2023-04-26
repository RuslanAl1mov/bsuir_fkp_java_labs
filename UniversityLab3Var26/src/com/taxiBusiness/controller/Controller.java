package com.taxiBusiness.controller;

import com.taxiBusiness.Administrator.CreateNewCarWindow.entity.Car;
import com.taxiBusiness.Administrator.SendDriverWindow.entity.Application;
import com.taxiBusiness.Driver.entity.Driver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private List<Car> cars = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private final String Cars_FILE_PATH = "src\\Cars.txt";
    private final String Drivers_FILE_PATH = "src\\Drivers.txt";

    public Controller() {
        loadCarsFile();
        loadDriversFile();
    }

    public void createDriver(String firstName, String secondName, Application application) {
        Driver newDriver = new Driver(firstName, secondName, application);
        drivers.add(newDriver);
        saveDriversInFile();
    }

    public void createCar(String mark, int mileage){
        Car newCar = new Car(mark, mileage);
        cars.add(newCar);
        saveCarsInFile();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void sortCarsByMileage(){
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return Integer.compare(c1.getMileage(), (c2.getMileage()));
            }
        });
    }

    public void saveCarsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Cars_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(cars);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDriversInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Drivers_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(drivers);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadCarsFile(){
        try {
            FileInputStream fis = new FileInputStream(Cars_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.cars = (List<Car>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadDriversFile(){
        try {
            FileInputStream fis = new FileInputStream(Drivers_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.drivers = (List<Driver>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
