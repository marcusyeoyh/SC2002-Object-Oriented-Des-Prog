// Done by Mingyang
import java.io.IOException;
import java.util.HashMap;

/**
 * Represents a Cineplex
 * Cineplexes can contain multiple Cinemas and have a given name and cineplexID
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class Cineplex{
    /**
     * The name of the this Cineplex
     */
    private String name;
    /**
     * The ID of this Cineplex in String datatype
     */
    private String cineplexID;
    /**
     * The HashMap of this Cineplex containing information about the Cinemas under this Cineplex
     */
    private HashMap<String, Cinema> cinemaHashMap;

    // Constructor: 3 cinemas are added by default
    /**
     * Creates a Cineplex with the given name and ID, with minimum 3 Cinemas added by default
     * @param name this Cineplex's new name
     * @param cineplexID this Cineplex's new ID
     */
    public Cineplex(String name, String cineplexID) {
        this.cineplexID = cineplexID;
        this.name = name;
        addCinema();
    }

    /**
     * Gets this Cineplex's ID 
     * @return this Cineplex's ID in String datatype
     */
    public String getCineplexID() {
        return cineplexID;
    }

    /**
     * Gets this Cineplex's Name
     * @return this Cineplex's name in String datatype
     */
    public String getCineplexName() {
        return name;
    }

    /**
     * Adds a Cinema to the Cineplex Hashmap
     */
    public void addCinema() {
        CinemaStore cinemaStore = CinemaStore.getInstance();
        this.cinemaHashMap = cinemaStore.getCinemaHashMap(cineplexID);
    }

    /**
     * Gets this Cineplex's HashMap
     * @return this Cineplex's Hashmap of Cinemas
     */
    public HashMap<String, Cinema> getCinemaHashMap() {
        return cinemaHashMap;
    }
}
