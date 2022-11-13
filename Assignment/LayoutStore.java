import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a Store of Singleton instance which contains a Hashmap of Layouts depending on cinema
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class LayoutStore{
    /**
     * HashMap of all Layout objects
     */
    private HashMap<String, Layout> layoutHashMap;  // Key=cinemaID
    /**
     * Singleton instance of LayoutStore
     */
    private static LayoutStore single_instance = null;

    /**
     * Creates a new LayoutStore instance with a HashMap to store all Layouts
     */
    private LayoutStore() {
        layoutHashMap = new HashMap<>();
        loadLayoutHashMap();
    }

    /**
     * Creates a Singleton intance of LayoutStore to be referenced throughout the code if one does not exist already
     * @return Singleton instance which contains HashMap of all Layouts
     */
    public static LayoutStore getInstance() {
        if (single_instance == null)
            single_instance = new LayoutStore();

        return single_instance;
    }

    /**
     * Loads the HashMap with all the layouts present in cineplex.txt
     */
    private void loadLayoutHashMap() {
        ArrayList<String[]> temp = CineplexesReaderWriter.getInstance().getCineplexRawStore();
        for (String[] line: temp) {
            String cinemaID = line[0].concat(line[2]);
            int row = Integer.parseInt(line[4]);
            int column = Integer.parseInt(line[5]);
            int aisle = Integer.parseInt(line[6]);
            int mainStairway = Integer.parseInt(line[7]);

            Layout layout = new Layout(row, column, aisle, mainStairway);
            layoutHashMap.put(cinemaID, layout);
        }
    }

    /**
     * Gets the particular layout of a Cinema depending on ID input
     * @param cinemaID the user input CinemaID in int datatype
     * @return layout of the desired cinema in Layout datatype
     */
    public Layout getLayout(String cinemaID) {
        return layoutHashMap.get(cinemaID);
    }
}