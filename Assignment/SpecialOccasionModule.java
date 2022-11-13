import java.util.Scanner;

/**
 * This module provides functionalities for the user to declare/remove or print all special occasions which will affect ticketing pricings
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class SpecialOccasionModule implements Module{
    /**
     * The CinemaStaff with authorization to edit special occasions
     */
    private CinemaStaff cinemaStaff;
    /**
     * Scanner object created to recieve user unputs
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Creates a new SpecialOccasionModule object with a verified CinemaStaff account
     * @param cinemaStaff the account of a verified Cinema Staff
     */
    public SpecialOccasionModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
      
        int choice = -1;
        while (choice != 4) {
            System.out.println("\n----Special Occasion Module----");
            System.out.println("Select an option below (1-4):");
            System.out.println("1 - Add new special occasion ");
            System.out.println("2 - Remove special occasion ");
            System.out.println("3 - Print all special occasions");
            System.out.println("4 - Quit");
            System.out.println("-------------------------------\n");
            System.out.print("Select an option: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error: please enter (1-4)");
                sc.nextLine();
                continue;
            }
            sc.nextLine();


            switch (choice) {
                case 1 -> cinemaStaff.addSpecialOccasion();
                case 2 -> cinemaStaff.removeSpecialOccasion();
                case 3 -> cinemaStaff.printSpecialOccasions();
                case 4 -> System.out.println("Exiting Special Occasion Module...");
                default -> System.out.println("Invalid choice, please enter (1-4)");
            }
        }
    }
}
