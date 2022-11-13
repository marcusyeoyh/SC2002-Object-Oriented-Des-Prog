/**
 * This represents an individual Cinema inside a particular Cineplex
 * A Cinema object contains an ID, class of cinema and the layout of the particular cinema
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class Cinema {
    /**
     * The provided ID of the Cinema
     */
    private String cinemaID;
    /**
     * The class of the Cinema (Gold, Platinum)
     */
    private CinemaClass cinemaClass;
    /**
     * The assigned seat layout of the Cinema
     */
    private Layout layout;

    /**
     * Creates a new Cinema with the given ID and class
     * @param cinemaID this Cinema's allocated ID
     * @param cinemaClass this CInema's allocated class
     */
    public Cinema(String cinemaID, CinemaClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
        LayoutStore layoutStore = LayoutStore.getInstance();
        this.layout= layoutStore.getLayout(cinemaID);
    }

    /**
     * Gets this Cinema's ID
     * @return this Cinema's ID in String datatype
     */
    public String getCinemaID() {
        return cinemaID;
    }

    /**
     * Gets this Cinema's Class
     * @return this Cinema's class in CinemaClass enum datatype
     */
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Gets this Cinema's seat layout
     * @return this Cinema's seat layout in Layout datatype
     */
    public Layout getLayout() {
        return layout;
    }
}