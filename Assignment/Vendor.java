// Done by Mingyang
import java.io.IOException;
import java.util.HashMap;

/**
 * Represents a Vendor, who owns and operates the Cineplexes
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class Vendor {
    /**
     * Name of Vendor
     */
    private String vendorName;
    /**
     * HashMap to store Cineplexes under the vendor
     */
    private HashMap<String, Cineplex> cineplexHashMap = new HashMap<>();

    // Default vendor name: Cathay
    /**
     * Default constructor has vendor name as "Cathay"
     */
    public Vendor() {
        this.vendorName = "Cathay Cineplexes";
        addCineplex();
    }

    /**
     * Method overloading for Vendor constructor allows for customisability for Vendor name
     * @param vendorName Vendor object's name
     */
    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * Gets this Vendor's name
     * @return this Vendor's name
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Adds a Cineplex to the HashMap
     */
    private void addCineplex() {
        CineplexStore cineplexStore = CineplexStore.getInstance();
        this.cineplexHashMap = cineplexStore.getCineplexHashMap();
    }

    /**
     * Gets this Vendor's HashMap
     * @return this Vendor's HashMap
     */
    public HashMap<String, Cineplex> getCineplexHashMap() {
        return cineplexHashMap;
    }

   /**
    * Changes Vendor Name based on name input
    * @param name new Vendor name in String format
    */
    public void setVendorName(String name){
        this.vendorName = name;
    }
}

// Notes: add changeVendorName() method in the future when there is time