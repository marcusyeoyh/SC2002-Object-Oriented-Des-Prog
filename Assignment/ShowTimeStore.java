// Done by Mingyang

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This Store class is a Singleton Instance where a unique instance of ShowTimeStore is created which will be used throughout MOBLIMA
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class ShowTimeStore {
    /**
     * HashMap storing all the different showtimes
     */
    private HashMap<String, ShowTime> showTimeHashMap = new HashMap<>();  // key=SHOWTIME_ID
    /**
     * File path for showtimes.txt
     */
    private final String FILE_SOURCE = "Classes/src/showtimes.txt";
    /**
     * Creates unique instance of ShowTimeStore
     */
    private static ShowTimeStore single_instance = null;
    /**
     * Creates scanner object to accept user input
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * Allows for reading of contents of showtimes.txt into String format
     */
    private TxtReaderWriter showtimeReaderWriter = new TxtReaderWriter(FILE_SOURCE);

    /**
     * Creates new ShowTimeStore if not already created.
     */
    private ShowTimeStore() {
        loadShowTimeHashMap(showtimeReaderWriter.getRawStringFromFile());
    }

    /**
     * Creates Singleton Instance of ShowTimeStore
     * @return ShowTimeStore with HashMap containing all showtimes available
     */
    public static ShowTimeStore getInstance(){
        if (single_instance == null)
            single_instance = new ShowTimeStore();

        return single_instance;
    }

    /**
     * Gets this showtime's HashMap
     * @return this showtime's HashMap
     */
    public HashMap<String, ShowTime> getShowTimeHashMap() {
        return showTimeHashMap;
    }

    // Returns null if showtimeID does not exist
    /**
     * Gets a oarticular movie's Showtime based on user input Showtime ID
     * @param showTimeID user input Showtime ID in String format
     * @return ShowTime object with same Showtime ID as input by user.
     */
    public ShowTime getShowTime(String showTimeID) {
        return showTimeHashMap.get(showTimeID);
    }


    /**
     * Loads this ShowTimeStore's HashMap with information from showtime.txt
     * @param showtimeRawStore contains String elements of Showtime data to be stored in the HashMap
     */
    private void loadShowTimeHashMap(ArrayList<String[]> showtimeRawStore) {
        for (String[] line: showtimeRawStore) {
            ShowTime showTime = new ShowTime(line[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            showTime.setStartTime(LocalDateTime.parse(line[2], formatter));
            showTime.setMovieID(line[1]);
            showTimeHashMap.put(line[0], showTime);
        }
    }

    // parseHashMap to ArrayList<String[]>
    /**
     * Parses HashMap for all Showtimes and returns as an ArrayList with String Array elements
     * @return an ArrayList with String Array elements
     */
    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        Set<String> keys = showTimeHashMap.keySet();

        // Iterate over each ShowTime item
        for (String key: keys) {
            ShowTime showTime = showTimeHashMap.get(key);
            ArrayList<String> line = new ArrayList<>();

            line.add(showTime.getShowtimeID());
            line.add(String.valueOf(showTime.getMovieID()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            line.add(showTime.getStartTime().format(formatter));

            String[] out = new String[line.size()];
            arrayListOut.add(line.toArray(out));
        }
        return arrayListOut;
    }

    // Destructor
    /**
     * Destructor when program terminates.
     */
    public void closeShowTimeStore() {
        showtimeReaderWriter.setRawStringFromFile(parseHashMap());
    }
}
