import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTicketsByDays {

    public SortTicketsByDays(List<Ticket> tickets) {
        System.out.println("\nСОРТИРОВКА Путевок по кол-ву дней:");

        if (tickets.size() != 0) {
            Collections.sort(tickets, new Comparator<Ticket>() {
                @Override
                public int compare(Ticket t1, Ticket t2) {
                    return Integer.compare(t1.getDaysNum(), (t2.getDaysNum()));
                }
            });

            for (Ticket exTicket : tickets) {
                System.out.println("\t- " + exTicket.getTicketName() + " (" + exTicket.getDaysNum() + " дней) - " + exTicket.getTicketCost() + " руб.");
            }
        } else {
            try {
                throw new NumberException("\nСПИСОК БИЛЕТОВ ПУСТ!\n");
            } catch (NumberException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
