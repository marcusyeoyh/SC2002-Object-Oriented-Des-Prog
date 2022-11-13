import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * This represents the admin landing page after selecting the admin option in MOBLIMA Home Page
 * User will be required to log in as part of the basic security features
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class AdminModule implements Module{
    /**
    * Creates a Scanner object to allow for user inputs
    */
    Scanner sc = new Scanner(System.in);

    /**
     * This method is the main functionality of the AdminModule, which activates login functionalities and gives further options for the admin to use once login is verified.
     */
    public void run() {
        LoginHandler loginHandler = new LoginHandler();
        CinemaStaff cinemaStaff = loginHandler.login();

        // Login fails
        if (cinemaStaff == null) {
            System.out.println("Login failed, exiting admin module now");
            return;
        }

        // Initialize modules
        TicketPricingModule ticketPricingModule = new TicketPricingModule(cinemaStaff);
        SpecialOccasionModule specialOccasionModule = new SpecialOccasionModule(cinemaStaff);
        SysMovieModule sysMovieModule = new SysMovieModule(cinemaStaff);
        SysShowtimeModule sysShowtimeModule = new SysShowtimeModule(cinemaStaff);
        SysListingOptionsModule sysListingOptionsModule = new SysListingOptionsModule();

        // Menu of choices the customer can choose from
        int choice = -1;
        while (choice != 6) {
            System.out.println("\n--------------Admin Panel--------------");
            System.out.println("Option Available: (1-6):");
            System.out.println("(1) Ticket pricing ");
            System.out.println("(2) Special occasion");
            System.out.println("(3) Movies");
            System.out.println("(4) Showtimes");
            System.out.println("(5) Listing Options (System settings)");
            System.out.println("(6) Quit Admin Module");
            System.out.println("---------------------------------------");
            System.out.print("\nChoice: ");

            
            try{
                choice = sc.nextInt();
            } catch (InputMismatchException err){
                System.out.println("Error: Please input a valid number (1 - 6).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1 -> ticketPricingModule.run();
                case 2 -> specialOccasionModule.run();
                case 3 -> sysMovieModule.run();
                case 4 -> sysShowtimeModule.run();
                case 5 -> sysListingOptionsModule.run();
                case 6 -> System.out.println("Signing out of admin module...");
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
