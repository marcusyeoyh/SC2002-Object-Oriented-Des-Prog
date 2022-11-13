import java.util.Scanner;

/**
 * This represents the module that Guests are able to access, which allows for the viewing of Movies and lists but does not allow for the purchasing of tickets
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class GuestModule implements Module{
    /**
     * Creates a new scanner to accept user inputs
     */
    Scanner sc = new Scanner(System.in);
    /**
     * Displays options for guest user and accepts user input to redirect them accordingly
     */
    public void run() {
        Guest guest = new Guest();
        int choice = - 1;
        do {
            System.out.println("----Guest Panel---");
            System.out.println("(1) List all movies");
            System.out.println("(2) List top 5 movies"); // This one depends on admin setting to determine which is top 5
            System.out.println("(3) Search for movie to view details"); // including showtimes, reviews and ratings
            System.out.println("(4) Quit");
            System.out.println("---------------------");
            System.out.print("Choice: ");

            try{
                choice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 4).\n");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> guest.listAllMovies();
                case 2 -> guest.listBy();
                case 3 -> guest.searchMovie();
                case 4 -> System.out.println("Exiting guest module...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
