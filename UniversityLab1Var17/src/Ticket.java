public class Ticket {
    String ticketName;
    String ticketVariant;
    String transportType;
    String foodType;
    int daysNum;
    int ticketCost;

    public Ticket(String ticketName, String ticketVariant, int daysNum, int ticketCost) {
        this.ticketName = ticketName;
        this.ticketVariant = ticketVariant;
        this.daysNum = daysNum;
        this.ticketCost = ticketCost;
    }

    public String getTicketName() {
        return ticketName;
    }

    public String getTicketVariant() {
        return ticketVariant;
    }

    public void setTicketVariant(String ticketVariant) {
        this.ticketVariant = ticketVariant;
    }

    public int getDaysNum() {
        return daysNum;
    }

    public void setDaysNum(int daysNum) {
        this.daysNum = daysNum;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getTicketCost() {
        return ticketCost;
    }
}

