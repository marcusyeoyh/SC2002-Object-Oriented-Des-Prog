import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* ShowTime class created by Mingyang
* Purpose of ShowTime class: ShowTime obj that has-a ShowTime layout
* ShowTime has no Seats
* */
/**
 * This represents a particular Showtime for a movie showing in a cinema, which contains a unique ShowtimeID, movieID and Layout for the cinema
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class ShowTime {
    /**
     * The showtime ID of a movie
     */
    private String showtimeID;
    /**
     * start time of the movie
     */
    private LocalDateTime startTime;
    /**
     * movie ID of the movie being shown
     */
    private String movieID;
    /**
     * Movie object of the movie being shown
     */
    private Movie movie;
    /**
     * Cinema object where the movie is shown
     */
    private Cinema cinema;
    /**
     * Layout for the particular showtime
     */
    private ShowTimeLayout showTimeLayout;

    /**
     * Creates a new Showtime with an assigned showtime ID
     * @param showtimeID ID of the new showtime
     */
    public ShowTime(String showtimeID) {
        this.showtimeID = showtimeID;
        this.showTimeLayout = new ShowTimeLayout(showtimeID);
        this.cinema = CineplexStore.getInstance().getCineplex(showtimeID.substring(0,2)).getCinemaHashMap().get(showtimeID.substring(0,4));
    }

    /**
     * Gets this showtime's layout
     * @return the showtime layout of this showtime
     */
    public ShowTimeLayout getShowTimeLayout() {
        return showTimeLayout;
    }

    /**
     * gets the ID of this showtime
     * @return Showtime ID of this showtime in String datatype
     */
    public String getShowtimeID() {
        return showtimeID;
    }

    /**
     * Change this showtime's ID
     * @param showtimeID new showtime ID to be changed to
     */
    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    /**
     * Gets this showtime's start time
     * @return this showtime's start time in LocalDateTime datatype
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Changes start time of this showtime
     * @param startTime the new start time of this showtime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Changes the Movie ID of this showtime
     * @param movieID the new movieID of this showtime in String datatype
     */
    public void setMovieID(String movieID) {
        this.movieID = movieID;
        this.movie = MovieStore.getInstance().searchMovie(movieID);
    }

    /**
     * Gets this showtime's movie ID
     * @return this showtime's movie ID in String datatype
     */
    public String getMovieID() {
        return movieID;
    }

    /**
     * Gets this showtime's cinema's class
     * @return this showtime's cinema's class in CinemaClass datatype
     */
    public CinemaClass getCinemaClass() {
        return cinema.getCinemaClass();
    }

    /**
     * Prints the details of this showtime
     */
    public void printShowTime() {
        System.out.println("Cineplex: "+ CineplexStore.getInstance().getCineplex(showtimeID.substring(0,2)).getCineplexName());
        System.out.println("Cinema: "+ showtimeID.substring(2, 4));
        System.out.println("Cinema Class: " + this.cinema.getCinemaClass());
        System.out.println("Movie ID: " + this.movie.getMovieID());
        System.out.println("Movie Name: " + this.movie.getMovieName());
        System.out.println("Date: " + getStartTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        System.out.println("Starting Time: " + getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}