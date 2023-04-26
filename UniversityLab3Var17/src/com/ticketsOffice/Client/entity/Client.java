package com.ticketsOffice.Client.entity;

import com.ticketsOffice.Administrator.CreateNewTicketWindow.entity.Ticket;

import java.io.Serializable;


public class Client implements Serializable{
    private final String firstName;
    private final String secondName;
    private Ticket clientTicket;


    public Client(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setClientTicket(Ticket clientTicket) {
        this.clientTicket = clientTicket;
    }

    public Ticket getClientTicket() {
        return clientTicket;
    }
}
