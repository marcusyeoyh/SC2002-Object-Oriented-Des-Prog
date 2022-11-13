import java.util.Scanner;

/**
 * Module which allows for the creating, updating and removing of showtime objects
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class SysShowtimeModule implements Module{
    /**
     * Valid CinemaStaff account needed to access the various editing functions
     */
    private CinemaStaff cinemaStaff;
    /**
     * Scanner object created to accept user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Creates a new SysShowtimeModule with a verified Cinema Staff account
     * @param cinemaStaff CinemaStaff account to be used when editing showtimes
     */
    public SysShowtimeModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    /**
     * Main functionalities of the module
     */
    public void run() {
        System.out.println("\n-----System Showtime Module-----");
        System.out.println("Select an option below (1-4):");
        System.out.println("1 - Create new showtime");
        System.out.println("2 - Update showtime");
        System.out.println("3 - Remove showtime");
        System.out.println("4 - Print all showtimes");
        System.out.println("5 - Quit");
        System.out.println("---------------------------------");
        System.out.print("\nSelect an option: ");

        String choice = sc.nextLine();
        sc.nextLine();

        switch (choice) {
            case "1" -> cinemaStaff.addShowTime();
            case "2" -> cinemaStaff.updateShowTime();
            case "3" -> cinemaStaff.removeShowTime();
            case "4"-> System.out.println("Exiting System Showtime Module");
            default -> System.out.println("Invalid choice, exiting System Showtime module");
        }
    }
}
