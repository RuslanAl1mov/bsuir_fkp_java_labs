package com.ticketsOffice.controller;

import com.ticketsOffice.Administrator.CreateNewTicketWindow.entity.Ticket;
import com.ticketsOffice.Client.entity.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private List<Ticket> tickets = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private final String Tickets_FILE_PATH = "src\\com\\ticketsOffice\\Tickets.txt";
    private final String Clients_FILE_PATH = "src\\com\\ticketsOffice\\Clients.txt";

    public Controller() {
        loadTicketsFile();
        loadClientsFile();
    }

    public void createClient(String firstName, String secondName, Ticket clientsTickets) {
        Client newClient = new Client(firstName, secondName);
        newClient.setClientTicket(clientsTickets);
        clients.add(newClient);
        saveClientsInFile();
    }

    public void createTicket(String ticketName, String ticketVariant, int daysNum, float ticketCost){
        Ticket newTicket = new Ticket(ticketName, ticketVariant, daysNum, ticketCost);
        tickets.add(newTicket);
        saveTicketsInFile();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void sortTicketsByPrice(){
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket t1, Ticket t2) {
                return Float.compare(t1.getTicketCost(), (t2.getTicketCost()));
            }
        });
    }

    public void sortTicketsByDaysNum(){
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket t1, Ticket t2) {
                return Integer.compare(t1.getDaysNum(), (t2.getDaysNum()));
            }
        });
    }

    public void saveTicketsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Tickets_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(tickets);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveClientsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Clients_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(clients);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadTicketsFile(){
        try {
            FileInputStream fis = new FileInputStream(Tickets_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.tickets = (List<Ticket>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadClientsFile(){
        try {
            FileInputStream fis = new FileInputStream(Clients_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.clients = (List<Client>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
