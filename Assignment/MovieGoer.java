import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
/**
 * Represents a Movie-Goer object.
 * The class will implement the interfaces Person, ReviewHandler, MovieQuery and TicketInterface
 * 
 * @author Marc Chern
 * @version 1.0.0 Nov 12, 2022
 */
public class MovieGoer implements Person, ReviewHandler, MovieQuery, TicketInterface {
    /**
     * This movie goer's Name in String datatype
     */
    private String name;
    /**
     * This movie goer's email in String datatype
     */
    private String email;
    /**
     * This movie goer's mobile in String datatype
     */
    private String mobile;
    /**
     * This movie goer's tickets purchased in ArrayList containing Ticket objects
     */
    private ArrayList<Ticket> tickets; // key=TRANSACTION_ID
    /**
     * Scanner object to accept user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Creates a new movie goer, accepting input for the user's Name, Email and Mobile number.
     * This will later be tied to purchase history
     */
    public MovieGoer() {
        System.out.println("Welcome to Movie Goer registration module");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        int tempMobile = -1;
        do{
            System.out.print("Enter mobile number: ");
            try {
                tempMobile = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Please input a valid number.\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
        } while(tempMobile==-1);
        this.mobile = String.valueOf(tempMobile);

        System.out.print("Enter email address: ");
        this.email = sc.nextLine();
        setTickets();
    }
    
    /**
     * Gets this Movie-Goer's name
     * @return the name of the Movie-Goer
     */
    public String getName() {
        return this.name;
    }
    /**
     * Gets this Movie-Goer's email
     * @return the email of the Movie-Goer
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Gets this Movie-Goer's mobile number
     * @return the mobile number of the Movie-Goer
     */
    public String getMobile() {
        return this.mobile;
    }
    /**
     * Gets all the tickets which the Movie-Goer has bought previously
     * @return an ArrayList of all the Ticket objects
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    /**
     * Changes the name of the Movie-Goer
     * @param name new name of the Movie-Goer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Changes the email of the Movie-Goer
     * @param email new email of the Movie-Goer
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Changes the mobile number of the Movie-Goer
     * @param mobile new mobile number of the Movie-Goer
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * Sets all the previous tickets that the Movie-Goer bought in the past
     */
    public void setTickets(){
        this.tickets = TicketStore.getInstance().getTicketWithUserDetails(name, email, mobile);
    }
    /**
     * buyTicket() method allows Movie-Goer to pick a movie and purchase a movie ticket for the movie
     */
    public void buyTicket() {
        System.out.println("---Welcome to buy ticket module---");
        System.out.print("Enter MovieID (-1 to return): ");
        String movieID = sc.nextLine();
        if (movieID.equals("-1")){
            System.out.println("---Exiting buy ticket module---");
            return;
        }
        
        Movie curMovie = searchMovie(movieID);
        if (curMovie == null){
            return;
        }
        
        Set<String> keys = ShowTimeStore.getInstance().getShowTimeHashMap().keySet();
        System.out.println("-----------ShowTimes----------");
        for (String key : keys){
            // Get one showTime
            ShowTime showTime = ShowTimeStore.getInstance().getShowTime(key);
            if (showTime.getMovieID().equals(movieID)){
                System.out.println("ShowTimeID: "+ showTime.getShowtimeID());
                showTime.printShowTime();
                System.out.println("----------------------");
            }
        
        }
        
        System.out.print("Enter ShowTimeID of movie to purchase: ");
        String showtimeID = sc.nextLine();

        ShowTime curShowTime = ShowTimeStore.getInstance().getShowTime(showtimeID);

        // Check if showtime ID exists
        if (curShowTime == null) {
            System.out.println("Error: showtime does not exist!");
            System.out.println("---Exiting showtime module---");
            return;
        }
        // Handles ticket purchasing
        TicketHandler ticketHandler = new TicketHandler(curShowTime);
        ticketHandler.getMovieGoerDetails(this);
        // New ticket object to be added to TicketStore
        Ticket newTicket = ticketHandler.buyTicket();
        TicketStore.getInstance().addTicketToStore(newTicket);
        // Add new ticket to Movie-Goer object
        tickets.add(newTicket);
        // Increment movie sales
        curMovie.setMovieSales(curMovie.getMovieSales()+1);
}
}
