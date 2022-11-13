import java.util.Scanner;
/**
 * Main function and landing page for users. Users will be able to select if they would like to access MOBLIMA as a Admin, Customer or Guest
 * @author Marc Chern
 * @version 1.0.0 Nov 13, 2022
 */
public class MOBLIMA {
    /**
    * This is the entry point for the MOBLIMA program
    */
    public static void main(String[] args){
        /**
         * Creates a new scanner to accept user input
         */
        Scanner sc = new Scanner(System.in);
        // Create vendor
        /**
         * Creates a new Vendor class
         */
        Vendor vendor = new Vendor();

        /**
         * Loads a enw Admin Module upon startup
         */
        AdminModule adminModule= new AdminModule();
        /**
         * Loads a enw Movie Goer Module upon startup
         */
        MovieGoerModule movieGoerModule = new MovieGoerModule();
        /**
         * Loads a enw Guest Module upon startup
         */
        GuestModule guestModule = new GuestModule();


        System.out.println("\nStarting app...");

        System.out.println("Welcome to " + vendor.getVendorName() + " Movie Booking System");
        int loginChoice = 0;

        while(loginChoice != 4){
            System.out.println("\n----------Login Panel----------");
            System.out.println("(1) Admin");
            System.out.println("(2) Customer (Sign in required)");
            System.out.println("(3) Guest");
            System.out.println("(4) Quit ");
            System.out.println("-------------------------------");
            System.out.print("\nChoice: ");

            
            try{
                loginChoice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 4).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            /**
             * Depending on what user inputs, the respective modules will run
             */
            switch (loginChoice) {
                case 1 -> adminModule.run();
                case 2 -> movieGoerModule.run();
                case 3 -> guestModule.run();
                case 4 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid Choice.");
            }
        }
        StoreManager.closeAllStores();
    }
}
