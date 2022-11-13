/**
 * An enum class representing the different status of each movie
 * @author Marcus Yeo , Low Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */
public enum Status {
    /**
     * Movie is Coming Soon, not in theatres yet
     */
    COMINGSOON,
    /**
     * Movie is out for Preview
     */
    PREVIEW,
    /**
     * Movie is Now Showing
     */
    NOWSHOWING,
    /**
     * Movie has reached its End Of Showing and will not be displayed
     */
    ENDOFSHOWING;
}