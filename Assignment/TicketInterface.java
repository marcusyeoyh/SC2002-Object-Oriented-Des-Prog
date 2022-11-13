import java.util.ArrayList;
/**
 * Interface to display previous bookings
 * No other functionality
 * @author Marc
 * @version 1.0.0 Nov 12, 2022
 */
public interface TicketInterface {
    /**
     * Displays all the previous bookings made by the Movie-Goer
     * @param tickets - An ArrayList of Ticket objects which are the Movie-Goer's past bookings
     */
    default void showHistory(ArrayList<Ticket> tickets) {
        System.out.println("---Ticket booking history---");
        for (int i=0;i<tickets.size();i++) {
            Ticket ticket = tickets.get(i);
            System.out.printf("%2d. ",i+1);
            System.out.printf("Transaction ID: %-30s\n",ticket.getTransactionID());
            System.out.printf("    Price: %-5s Seat: %-5s Age: %-10s\n",ticket.getPrice(),ticket.getSeatID(),ticket.getAgeGroup());
        }

    }
}
