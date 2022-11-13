import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * Ticket Handler class to handle purchasing of the ticket
 * This class will be used to organise all the necessary attributes required to create a ticket
 * @author Marc
 * @version 1.0.0 Nov 12, 2022
 */
public class TicketHandler {
    /**
     * Attributes which are required to create a new Ticket object
     */
    private String seatID;
    private Ticket ticket;
    private AgeGroup ageGroup;
    private ShowTime showTime;
    private Movie movie;
    private Seat seat;
    Scanner sc = new Scanner(System.in);
    /**
     * Default constructor for the TicketHandler Class
     * @param showtime - The ShowTime object is used to construct the TicketHandler class
     */
    public TicketHandler(ShowTime showtime) {
        
        // Setting Showtime
        this.showTime = showtime;
        
        // Print showtime layout
        ShowTimeLayout showTimeLayout = showTime.getShowTimeLayout();
        showTimeLayout.printLayout();
        
        System.out.println(showtime.getShowtimeID() + " selected. Choose your seats (A1,B15...) ");
        getMovie();
        // Get seat and check if seat is available
        do {
            System.out.print("Seat choice: ");
            this.seatID = sc.nextLine();
            this.seat = showTimeLayout.getSeat(seatID);

            if (seat == null || !seat.getAvail())
                System.out.println("Seat is not available, please try again!");
        } while (seat == null || !seat.getAvail());
        

        // Get age group
        do {
            getAgeGroup();
        } while (ageGroup == null);
        
        // Generate ticket object
        ticket = new Ticket(generateTransactionID(showtime.getShowtimeID()));
        
        // Set seat and AgeGroup
        ticket.setSeatID(seatID);
        ticket.setAgeGroup(ageGroup);
        showTimeLayout.getSeat(seatID).setAvail(false);
        SeatStore.getInstance().occupySeat(showtime.getShowtimeID(), seatID);
        

        // Query price
        ticket.setPrice(getPrice());
        System.out.println("Ticket costs $" + ticket.getPrice());

        System.out.println("Ticket purchase successful!");
        System.out.println("Transaction ID: " + ticket.getTransactionID());
    }
    /**
     * Called when the Movie-Goer finalises the ticket purchase
     * @return ticket - The new ticket which will be stored in the TicketStore
     */
    public Ticket buyTicket() {
        return ticket;
    }
    /**
     * Used to set the Movie-Goer details from the MovieGoer class into the ticket class
     * @param movieGoer - the Movie-Goer class has these attributes
     */
    public void getMovieGoerDetails(MovieGoer movieGoer) {
        // Get movie goer details
        this.ticket.setMobile(movieGoer.getMobile());
        this.ticket.setEmail(movieGoer.getEmail());
        this.ticket.setUsername(movieGoer.getName());
    }

    /**
     * Generate transactionID in format CCCCSSSSSSyyyyMMddHHmm
     * @param showtimeID - used in the generation of the transactionID
     * @return String - the TransactionID which will be stored in the TicketStore
     */
    private String generateTransactionID(String showtimeID) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return showtimeID.concat(seatID).concat(LocalDateTime.now().format(formatter));
    }


    /**
     * Get age group for ticket from Movie-Goer through system input
     */
    private void getAgeGroup() {
        int choice = -1;
        boolean flag = false;
        do{
            System.out.println("Enter type of ticket you wish to buy: ");
            System.out.println("(1) Child ticket");
            System.out.println("(2) Adult ticket");
            System.out.println("(3) Senior ticket");
            System.out.print("Enter choice: ");

            try{
                choice = sc.nextInt();
                flag = false;
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 3).");
                flag = true;
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                    case 1: 
                        this.ageGroup = AgeGroup.CHILD;
                        return;
                    case 2: 
                        this.ageGroup = AgeGroup.ADULT;
                        return;
                    case 3: 
                        this.ageGroup = AgeGroup.SENIOR;
                        return;
                    default:
                        System.out.println("Invalid choice!");
                        flag = true;
            }
        } while (flag);
    }

    /**
     * Get movie info for the ticket
     */
    private void getMovie() {
        String movieID = showTime.getMovieID();
        this.movie = MovieStore.getInstance().getMovieHashMap().get(movieID);
    }
    /**
     * Get the price of the ticket through the PricingStore queryPrice method
     * @return double - Price in double data type
     */
    private double getPrice() {
        Hype hype = movie.getMovieHype();
        CinemaClass cinemaClass = showTime.getCinemaClass();
        boolean isPH = SpecialOccasionStore.getInstance().isSpecialOccasion(LocalDate.from(showTime.getStartTime()));
        Integer dayOfWeek = showTime.getStartTime().getDayOfWeek().getValue();
        LocalTime startTime = showTime.getStartTime().toLocalTime();
        View view = movie.getViewingMode();

        return PricingStore.getInstance().queryPrice(ageGroup, hype, cinemaClass, isPH, dayOfWeek, startTime, view);
    }
}