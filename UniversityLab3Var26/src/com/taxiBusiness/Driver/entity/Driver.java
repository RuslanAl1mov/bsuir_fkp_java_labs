package com.taxiBusiness.Driver.entity;

import com.taxiBusiness.Administrator.SendDriverWindow.entity.Application;

import java.io.Serializable;

public class Driver implements Serializable {
    private String firstName;
    private String secondName;
    private Application application;


    public Driver (String firstName, String secondName, Application application){
        this.firstName = firstName;
        this.secondName = secondName;
        this.application = application;
    }


    // Получить имя водителя
    public String getFirstName() {
        return firstName;
    }

    // Получить фамилию водителя
    public String getSecondName() {
        return secondName;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

}

