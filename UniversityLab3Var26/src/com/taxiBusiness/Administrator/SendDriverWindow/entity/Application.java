package com.taxiBusiness.Administrator.SendDriverWindow.entity;

import com.taxiBusiness.Administrator.CreateNewCarWindow.entity.Car;

import java.io.Serializable;

public class Application implements Serializable {
    private final String startAddress;  // начальный адрес
    private final String finishAddress;  // конечный адресс
    private final Car car;
    private boolean finished = false; // закрытая заявка
    private boolean onRepair = false; // на ремонте

    public Application (String startAddress, String finishAddress, Car car) {
        this.startAddress = startAddress;
        this.finishAddress = finishAddress;
        this.car = car;
    }

    // Получить начальный адрес
    public String getStartAddress() {
        return startAddress;
    }

    // Получить конечный адресс
    public String getFinishAddress() {
        return finishAddress;
    }

    // Получить название машины
    public String getCarName() {
        return car.getMark();
    }

    // Получить пробег машины
    public int getCarMileage() {
        return car.getMileage();
    }

    // Получить статус машины на ремонте

    public boolean getCarRepairStatus() {
        return car.getOnRepairStatus();
    }

    public Car getCar() {
        return car;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean status){
        this.finished = status;
    }

    public boolean getOnRepair(){
        return this.onRepair;
    }

    public void setOnRepair(boolean status){
        this.onRepair = status;
    }
}
