import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/**
 * Handles Special Occasions changes such as adding, updating and removing of special occasions from SpecialOccasionStore
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
interface SysSpecialOccasionHandler {
    /** 
     * Allows for user inputs
     */
    Scanner sc = new Scanner(System.in);
    /**
     * Allows for adding of a special occasion
     * @return True = Special Occasion has been added, False = Special Occasion has not been added
     */
    default boolean addSpecialOccasion() {
        SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();

        System.out.print("Enter occasion name: ");
        String name = sc.nextLine();
        System.out.print("Enter date (DD-MM): ");
        String newDate = sc.nextLine();

        // Check if date is of valid format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM").withResolverStyle(ResolverStyle.STRICT);
        SpecialOccasion specialOccasion;
        try {
            formatter.parse(newDate);
            specialOccasion = new SpecialOccasion(newDate, name);
        } catch (Exception e) {
            System.out.println("Invalid date format, returning...");
            return false;
        }

        return specialOccasionStore.add(specialOccasion);
    }

    /**
     * Removes a special occasion
     * @return True = successfully removed, False = failed to remove
     */
    default boolean removeSpecialOccasion() {
        SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();

        System.out.print("Enter occasion name: ");
        String name = sc.nextLine().toLowerCase();
        System.out.print("Enter date (DD-MM): ");
        String date = sc.nextLine();

        return specialOccasionStore.remove(new SpecialOccasion(date, name));
    }

    /**
     * Prints special occasions from SpecialOccasionStore
     */
    default void printSpecialOccasions() {
        SpecialOccasionStore.getInstance().printAll();
    }
}
