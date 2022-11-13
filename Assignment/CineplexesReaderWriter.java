// Done by Mingyang

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Singleton Class CineplexesReaderWriter, which reads and writes  from cineplexes.txt, converting the individual Cinemas and their attributes into Arrays to be used by other Store classes to be stored into HashMaps
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class CineplexesReaderWriter {
    /**
     * ArrayList of String elements arrays which contains information of each Cinema
     */
    private ArrayList<String[]> cineplexRawStore = new ArrayList<>();
    /**
     * Final String element which directs to file path of cineplexes.txt
     */
    private final String FILE_SOURCE = "Classes/src/cineplexes.txt";
    /**
     * String given to Header of txt file. This String stores the header and prevents it from being read into the cineplexRawStore ArrayList
     */
    private String HEADER;
    /**
     * Singleton instance of CineplexesReaderWriter
     */
    private static CineplexesReaderWriter single_instance = null;

    // Implementation: during inialization, it immediately reads file into ArrayList
    /**
     * Creation of CineplexesReaderWriter
     * During intialization, it immediately reads credentials.txt file into ArrayList and seperates each attribute as its own String element.
     */
    private CineplexesReaderWriter() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.HEADER = reader.readLine();

            String line = reader.readLine();
            while (line != null) {
                cineplexRawStore.add(line.split("\\|"));     // Add raw String from .txt
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error: cineplexes.txt not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Singleton
    /**
     * Initializes the CineplexesReaderWriter Singleton
     * @return cineplexRawStore ArrayList is returned with each Cinema object in cineplexes.txt
     */
    public static CineplexesReaderWriter getInstance() {
        if (single_instance == null)
            single_instance = new CineplexesReaderWriter();

        return single_instance;
    }

    // Write from ArrayList into .txt
    /**
     * Writes each String array from cineplexRawStore back into cineplexes.txt
     */
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(HEADER);
            for (String[] line : cineplexRawStore) {
                writer.write("\n" + String.join("|", line));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets this CineplexesReaderWriter's cineplexRawStore attribute, which contains String Array elements of each Cinema and their attributes
     * @return the ArrayList of String Array elements of each Cinema and their attributes stored in cineplexes.txt
     */
    public ArrayList<String[]> getCineplexRawStore() {
        return cineplexRawStore;
    }

    // addCineplexData() should be called only from the admin module, and it will append the ArrayList
    /**
     * Add method to add a new Cinema into the cineplexRawStore, creating a new Cinema if desired by admin
     */
    public void addCineplexData() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> out = new ArrayList<>();
        System.out.println("Welcome to add cineplex module");

        System.out.println("Enter Cineplex ID: ");
        out.add(sc.next("[0-9][0-9]"));
        sc.nextLine();

        System.out.println("Enter Cineplex name: ");
        out.add(sc.nextLine().trim().replace('|', ' '));

        System.out.println("Enter Cinema no.: ");
        out.add(sc.next("[0-9][0-9]"));
        sc.nextLine();

        System.out.println("Enter cinema class (STANDARD/ GOLD/ PLATINUM): " );
        String cinemaClass = sc.nextLine().trim().toUpperCase();
        // Check if cinemaClass is valid
        if (!CinemaClass.isValid(cinemaClass)) {
            System.out.println("Invalid cinema class input, default of STANDARD is chosen");
            cinemaClass = "STANDARD";
        }
        out.add(cinemaClass);

        System.out.println("Enter number of rows: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Enter number of columns: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Enter aisle number: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Enter main stairway number: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Entry success!");
        System.out.println(String.join("|", out));       // for debugging, remove
        String[] outFormatted = new String[out.size()];
        outFormatted = out.toArray(outFormatted);
        cineplexRawStore.add(outFormatted);
    }
}
