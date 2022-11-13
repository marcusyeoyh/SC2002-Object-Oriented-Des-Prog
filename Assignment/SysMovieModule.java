import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * SysMovieModuel works closely with SysMovieHandler to enable the user to update, create and remove Movies from MovieStore with ease
 * @author Marcus Yeo, Low Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */
public class SysMovieModule implements Module{
    /**
     * Cinemastaff user and their details
     */
    private CinemaStaff cinemaStaff;
    /**
     * Allows for user input to be received
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Creates a new SysMovieModule object using a registered CinemaStaff user
     * @param cinemaStaff validated account of Cinema Staff
     */
    public SysMovieModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    /**
     * Main functionality of SysMovieModule class, gives the user the choice of adding, updating or removing Movies from MovieStore, with functionalities from SysMovieHandler
     */
    public void run() {
        System.out.println("-----System Movie Module-----");

        int choice = -1;
        while (choice != 5) {
            System.out.println("-----System Movie Module-----");
            System.out.println("Select an option below (1-5):");
            System.out.println("1 - Create new movies");
            System.out.println("2 - Update movies");
            System.out.println("3 - Remove movies");
            System.out.println("4 - View all movies");
            System.out.println("5 - Quit");
            System.out.println("-----------------------------");
            System.out.print("Select an option: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option! Enter (1-5)");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> cinemaStaff.createNewMovie();
                case 2 -> cinemaStaff.updateMovie();
                case 3 -> cinemaStaff.removeMovie();
                case 4 -> cinemaStaff.printAllMovies();
                case 5-> System.out.println("Exiting System Movie Module");
                default -> System.out.println("Invalid choice, exiting System Movie module");
            }
        }
    }
}
