import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class SortTicketsByPrice {

     public SortTicketsByPrice(List<Ticket> tickets) {
        System.out.println("\nСОРТИРОВКА Путевок ПО ЦЕНЕ:");
        if (tickets.size() != 0) {

            Collections.sort(tickets, new Comparator<Ticket>() {
                @Override
                public int compare(Ticket t1, Ticket t2) {
                    return Integer.compare(t1.getTicketCost(), (t2.getTicketCost()));
                }
            });

            for (Ticket exTicket : tickets) {
                System.out.println("\t- " + exTicket.getTicketName() + " (" + exTicket.getDaysNum() + " дней) - " + exTicket.getTicketCost() + " руб.");
            }
        } else {
            try{
                throw new NumberException("\nСПИСОК БИЛЕТОВ ПУСТ!\n");
            } catch (NumberException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
