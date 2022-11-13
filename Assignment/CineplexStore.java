// Done by Mingyang

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a Store of all the Cineplexes created
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class CineplexStore {
    /**
     * HashMap containing all the Cineplexes created
     */
    private HashMap<String, Cineplex> cineplexHashMap;
    /**
     * Singleton instance of CineplexStore
     */
    private static CineplexStore single_instance = null;

    /**
     * Creates a new CineplexStore and HashMap to store Cineplex objects
     */
    private CineplexStore() {
        this.cineplexHashMap = new HashMap<String, Cineplex>();
        loadCineplexHashMap();
    }

    /**
     * Initializes Cineplex Singleton instance
     * @return Singleton Instance containing HashMap of all Cineplexes created
     */
    public static CineplexStore getInstance() {
        if (single_instance == null)
            single_instance = new CineplexStore();

        return single_instance;
    }

    /**
     * Loads cineplexHashMap with each unique Cineplex from cineplex.txt
     */
    private void loadCineplexHashMap() {
        ArrayList<String[]> temp = CineplexesReaderWriter.getInstance().getCineplexRawStore();
        for (String[] line: temp) {
            Cineplex cineplex = new Cineplex(line[1], line[0]);
            cineplexHashMap.put(line[0], cineplex);
        }
    }

    // This function is for Vendor class to get a Hashmap of cineplexes
    /**
     * Gets this CineplexStore's HashMap containing all created Cineplexes
     * @return this CineplexStore's HashMap cineplexHashMap
     */
    public HashMap<String, Cineplex> getCineplexHashMap() {
        return cineplexHashMap;
    }

    // For query of Cineplex by cineplexID
    /**
     * Gets a particular Cineplex object depending on ID of cineplex input
     * @param cineplexID the ID of cineplex desired in String datatype
     * @return the Cineplex object with the input ID.
     */
    public Cineplex getCineplex(String cineplexID) {
        return cineplexHashMap.get(cineplexID);
    }
}
