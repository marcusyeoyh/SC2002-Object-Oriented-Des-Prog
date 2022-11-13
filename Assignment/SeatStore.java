import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * SeatStore is a Store using the Singleton Class to intitalize one unique instance variable for SeatStore which will be constantly referred to when assessing Seats in a given Cinema
 * It also has the responsibility of reading and writing to seats.txt
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class SeatStore {
    /**
     * HashMap storing the seats in a Cinema.
     */
    private Map<String, ArrayList<String>> seatHashMap = new HashMap<>();    // key=SHOWTIME_ID
    /**
     * Singleton Instance variable which creates a unique instance of SeatStore
     */
    private static SeatStore single_instance = null;
    /**
     * ArrayList containing the information from seats.txt in String form
     */
    private ArrayList<String[]> seatRawStore = new ArrayList<>();
    /**
     * File path for seats.txt
     */
    private final String FILE_SOURCE = "Classes/src/seats.txt";
    /**
     * String variable to store the header for seats.txt
     */
    private String HEADER;

    /**
     * Creates a new SeatStore if there is not one already
     */
    private SeatStore() {
        readFile();
        loadSeatStoreHashMap();
    }

    /**
     * Creates the Singleton Instance of SeatStore
     * @return SeatStore object
     */
    public static SeatStore getInstance() {
        if (single_instance == null)
            single_instance = new SeatStore();

        return single_instance;
    }

    /**
     * Loads the HashMap with information from seats.txt
     */
    private void loadSeatStoreHashMap() {
        for (String[] line: seatRawStore) {
            ArrayList<String> occupied = new ArrayList<>();
            occupied.addAll(Arrays.asList(line[1].split("~")));
            seatHashMap.put(line[0], occupied);
        }
    }

    // Read from .txt into raw ArrayList<String[]>
    /**
     * Reads the seats.txt file and produces a ArrayList with String Array elements containing information about each seat.
     */
    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.HEADER = reader.readLine();    // Header

            String line = reader.readLine();
            while (line != null) {
                seatRawStore.add(line.split("\\|"));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets Seats which share the same show time as input by the user
     * @param showTimeID the desired showtime input by user in String datatype
     * @return the List of string elements of Seats with the same showtime ID as that input by the user.
     */
    public List<String> getSeatsForShowTime(String showTimeID){
        return seatHashMap.get(showTimeID);
    }

    /**
     * Indicates that a Seat is occupied for a particular Showtime ID as input by the user
     * @param showTimeID the showtime ID of the occupied seat
     * @param seatID seat ID of the seat occupied
     */
    public void occupySeat(String showTimeID, String seatID){
        if(seatHashMap.get(showTimeID) == null){
            ArrayList<String> occupied = new ArrayList<>(); 
            occupied.add(seatID);
            seatHashMap.put(showTimeID, occupied);
        } else {
            seatHashMap.get(showTimeID).add(seatID);
        }
    }

    // Write from SeatStore into .txt
    // Call before terminating the program!!!
    /**
     * Writes the final version of SeatStore back to seats.txt before the program terminates.
     */
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(HEADER);
            for (Map.Entry<String, ArrayList<String>> entry : seatHashMap.entrySet()) {
                writer.write( "\n" + entry.getKey() + "|" + String.join("~", entry.getValue()));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
