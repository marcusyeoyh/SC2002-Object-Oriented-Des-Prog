import java.util.Scanner;

/**
 * Module allows for user to edit the ticket pricing
 * @author Marc Chern
 * @version 1.0.0 Nov 13, 2022
 */
public class TicketPricingModule implements Module{
    /**
     * Valid Cinema Staff account needed for editing capabilites
     */
    private CinemaStaff cinemaStaff;
    /**
     * Allows for user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Creates a new TicketPricingModule
     * @param cinemaStaff required parameter for editing ticket pricing information
     */
    public TicketPricingModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    /**
     * Main function for TicketPricingModule, allows for user to edit and change ticket pricing information
     */
    public void run() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\nWelcome to Ticket Pricing Module");
            System.out.println("-----------------------------");
            System.out.println("Select an option below (1-4):");
            System.out.println("1 - Create new pricing rule");
            System.out.println("2 - Update new pricing rule");
            System.out.println("3 - Remove pricing");
            System.out.println("4 - Print all pricings");
            System.out.println("5 - Quit");
            System.out.println("-----------------------------\n");
            System.out.print("Select an option: ");

            try{
                choice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 5).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1 -> cinemaStaff.addPricingRule();
                case 2 -> cinemaStaff.updatePricingRule();
                case 3 -> cinemaStaff.removePricingRule();
                case 4 -> cinemaStaff.printAllPricingRules();
                case 5-> System.out.println("Exiting Ticket Pricing Module");
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
