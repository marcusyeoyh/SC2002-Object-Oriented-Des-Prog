import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This represents all the Cinema objects that have been loaded in Cineplexes.txt
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class CinemaStore{
    /**
     * The Hashmap which stores each individual Cinema object
     */
    private HashMap<String, Cinema> cinemaHashMap;  // Key=cinemaID
    /** 
     * Static instance of CinemaStore which contains the Hashmap containing all the Cinema objects within cineplex.txt
     */
    private static CinemaStore single_instance = null;

    /**
     * Upon creating the first instance of CinemaStore, a new Hashmap is created
     */
    private CinemaStore() {
        this.cinemaHashMap = new HashMap<String, Cinema>();
        loadCinemaHashMap();
    }

    // Singleton
    /**
     * Creates a CinemaStore instance if it had previously not existed, if it has, then it returns a static variable single_instance with a Hashmap with Cinema objects inside
     * This is the initialization point/calling point for the CinemaStore Singleton Class
     * @return a static CinemaStore object
     */
    public static CinemaStore getInstance() {
        if (single_instance == null)
            single_instance = new CinemaStore();

        return single_instance;
    }

    /**
     * Populates the cinemaHashMap with Cinema objects obtained from CineplexesReaderWriter
     * .getCineplexRawStore() from CineplexesReaderWriter returns an ArrayList of String array elements. Each of these String Arrays represent a Cinema with its own attributes.
     */
    private void loadCinemaHashMap() {
        ArrayList<String[]> temp = CineplexesReaderWriter.getInstance().getCineplexRawStore();
        for (String[] line: temp) {
            String cinemaID = line[0].concat(line[2]);
            Cinema cinema = new Cinema(cinemaID, CinemaClass.valueOf(line[3]));
            cinemaHashMap.put(cinemaID, cinema);
        }
    }


    // Return the cinemaHashMap for the corresponding CineplexID
    /**
     * Gets a HashMap of Cinemas that have a particular cineplexID
     * @param cineplexID the ID of the desired cineplex in String datatype
     * @return the HashMap containing all Cinema objects with the input CineplexID
     */
    public HashMap<String, Cinema> getCinemaHashMap(String cineplexID) {
        // Find intersection between keys and cineplexID
        Set<String> keys = cinemaHashMap.keySet();
        // Create new HashMap with only relevant keys
        HashMap<String, Cinema> tempHashMap = new HashMap<>();
        for (String key: keys) {
            if(key.substring(0, 2).equals(cineplexID)){
                Cinema cinema = cinemaHashMap.get(key);
                tempHashMap.put(key, cinema);
            }
        }
        return tempHashMap;
    }

    /**
     * Gets the full Hashmap containing all Cinema objects in cineplex.txt
     * @return this CinemaStore's HashMap containing all Cinemas in cineplex.txt
     */
    public HashMap<String, Cinema> getCinemaHMap(){
        return cinemaHashMap;
    }

    /**
     * Gets the particular Cinema corresponding with the provided ID
     * @param cinemaID the ID of Cinema to be returned in String datatype
     * @return the Cinema object in this CinemaStore's HashMap which contains the corresponding Cinema ID
     */
    public Cinema getCinema(String cinemaID) {
        return cinemaHashMap.get(cinemaID);
    }

    // Returns true if cinemaID is valid (existing cinema)
    /**
     * isValidCinema is a check to see if the input cinema ID is valid and exists within this CinemaStores's HasMap
     * @param cinemaID the ID of the Cinema to be checked in String datatype
     * @return a Boolean true if the cinemaID exists and false if it does not.
     */
    public boolean isValidCinema(String cinemaID) {
        return cinemaHashMap.containsKey(cinemaID);
    }
}
