import java.time.Duration;
import java.util.ArrayList;

/**
 * Represents a Movie shown in a Cinema
 * The Movie may have mulitple MoviePersonnels
 * @author Marcus Yeo, Low Zhe Kai
 * @version 1.0.0 Nov 12, 2022
 */
public class Movie {
    /**
     * The full name of the movie
     */
    private String movieName;
    /**
     * movie ID of the movie
     */
    private final String movieID;
    /**
     * Duration of the movie
     */
    private Duration movieDuration;
    /**
     * Showing status of the movie, eg Now Showing, End Of Showing, Preview, Coming Soon
     */
    private Status showingStatus;
    /**
     * Full synopsis of the movie
     */
    private String synopsis;
    /**
     * Viewing Mode of the movie ie 2D or 3D
     */
    private View viewingMode;
    /**
     * The category of movie ie Blockbuster or Regular
     */
    private Hype movieHype;
    /**
     * Number of tickets sold for the movie
     */
    private int movieSales;
    /**
     * Summary of reviews for the movie
     */
    private OverallReviews  overallReviews;
    /**
     * ArrayList of people involved in the movie, including Director and Cast
     */
    private ArrayList<MoviePersonnel> moviePersonnelList = new ArrayList<>();
    /**
     * Age rating of the movie
     */
    private AgeEnum ageRating;

    /**
     * Creates a new Movie with the given ID
     * ID should be a string containing only numbers
     * @param movieID the ID of the movie in String data type
     */
    public Movie(String movieID){
        this.movieID = movieID;
        this.overallReviews = new OverallReviews();
    }
    /**
     * Gets the name of the movie
     * @return this Movie's name in String datatype
     */
    public String getMovieName() {
        return movieName;
    }
    /**
     * Gets the ID of the movie
     * @return this Movie's ID in String datatype
     */
    public String getMovieID() {
        return movieID;
    }
    /**
     * Gets the duration of the movie in minutes
     * @return this Movie's Duration in Duration datatype
     */
    public Duration getMovieDuration() {
        return movieDuration;
    }
    /**
     * Gets the showing status of the movie (Preivew, Now Showing, End Of Showing, Coming Soon)
     * @return this Movie's Showing Status in Status enum datatype
     */
    public Status getShowingStatus() {
        return showingStatus;
    }
    /**
     * Gets the synopsis of the movie
     * @return this Movie's Synopsis in String datatype
     */
    public String getSynopsis() {
        return synopsis;
    }
    /**
     * Gets the viewing mode of the movie (2D, 3D)
     * @return this Movie's ViewingMode in View enum datatype
     */
    public View getViewingMode() {
        return viewingMode;
    }
    /**
     * Gets the MovieHype of the Movie (Blockbuster, Regular)
     * This denotes the category of the movie
     * @return this Movie's MovieHype in the Hype enum datatype
     */
    public Hype getMovieHype() {
        return movieHype;
    }
    /**
     * Gets the ArrayList of MoviePersonnel for this Movie
     * Each element in the Arraylist is of MoviePersonnel datatype, denoting the personnel involved in the Movie and their associated Role
     * @return this Movie's MoviePersonnel Arraylist with MoviePersonnel datatype
     */
    public ArrayList<MoviePersonnel> getMoviePersonnelList() {
        return moviePersonnelList;
    }
    /**
     * Gets the number of tickets sold for this Movie
     * @return this Movie's movie sales in int datatype
     */
    public int getMovieSales(){
        return movieSales;
    }
    /**
     * Gets the overall reviews of this Movie
     * @return this Movie's overall reviews in OverallReviews datatype
     */
    public OverallReviews getOverallReviews() {
        return this.overallReviews;
    }
    /**
     * Gets the average rating of this Movie
     * @return the average rating of this Movie in double datatype
     */
    public double getAvgRating() {
        return getOverallReviews().getAvgRating();
    }
    /**
     * Gets the individual MoviePersonnel object in moviePersonnelList depending on index chosen
     * @param index the index of MoviePersonnel object desired
     * @return the MoviePersonnel chosen
     */
    public MoviePersonnel getMoviePersonnel(int index) {
        return moviePersonnelList.get(index);
    }
    /**
     * Gets the age rating for this movie (G, PG, PG13, M18, R21)
     * @return this Movie's age rating
     */
    public AgeEnum getAgeRating() {
        return this.ageRating;
    }
    /**
     * Changes the name of the Movie
     * @param movieName this Movie's new name, should be in String datatype
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    /**
     * Changes the duration of the movie
     * Movie Duration is in minutes
     * @param movieDuration this movie's duration in minutes
     */
    public void setMovieDuration(Duration movieDuration) {
        this.movieDuration = movieDuration;
    }
    /**
     * Changes the Showing Status of the Movie (Preivew, Now Showing, End Of Showing, Coming Soon)
     * @param showingStatus the movie's new ShowingStatus, an enum with Status datatype
     */
    public void setShowingStatus(Status showingStatus) {
        this.showingStatus = showingStatus;
    }
    /**
     * Changes the synopsis of the movie
     * @param synopsis this movie's new synopsis in String datatype
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    /**
     * Changes the ViewingMode of the movie (2D, 3D)
     * This is the format in which the movie is displayed
     * @param viewingMode this Movie's new ViewingMode
     */
    public void setViewingMode(View viewingMode) {
        this.viewingMode = viewingMode;
    }
    /**
     * Changes the MovieHype of the movie (Blockbuster, Regular)
     * @param movieHype this Movie's new MovieHype
     */
    public void setMovieHype(Hype movieHype) {
        this.movieHype = movieHype;
    }
    /**
     * Changes the OverallReviews of the Movie
     * Allows for the changing of the OverallReviews attribute in Movies, which grants greater flexibility to Admins in creating and editing movies
     * @param overallReviews this Movie's new OverallReviews
     */
    public void setOverallReviews(OverallReviews overallReviews) {
        this.overallReviews = overallReviews;
    }
    /**
     * Changes the ArrayList of MoviePersonnel objects in this Movie
     * Each MoviePersonnel object contains the name of a person involved in the movie and their associated role.
     * @param moviePersonnelList this Movie's ArrayList of MoviePersonnel
     */
    public void setMoviePersonnelList(ArrayList<MoviePersonnel> moviePersonnelList) {
        this.moviePersonnelList = moviePersonnelList;
    }
    /**
     * Changes the AgeRating of this Movie. (G, PG, PG13, M18, R21)
     * AgeRating determines the demographic of customers that are allowed to purchase a ticket
     * @param ageRating this Movie's AgeRating with AgeEnum datatype
     */
    public void setAgeRating(AgeEnum ageRating) {
        this.ageRating = ageRating;
    }
    /**
     * Changes the number of tickets sold for this Movie
     * @param movieSales this Movie's new ticket sales in int datatype
     */
    public void setMovieSales(int movieSales){
        this.movieSales = movieSales;
    }
    /**
     * Removes a MoviePersonnel object in moviePersonnelList based on index provided
     * @param index the index of MoviePersonnel object to be removed
     */
    public void removeMoviePersonnel(int index) {
        moviePersonnelList.remove(index);
    }
    /**
     * Adds a MoviePersonnel object into moviePersonnelList
     * @param name the name of the new movie personnel
     * @param role the role of the new movie personnel
     */
    public void addMoviePersonnel(String name, Role role) {
        moviePersonnelList.add(new MoviePersonnel(name, role));
    }
    /**
     * Prints the Name and Role of each MoviePersonnel object in the moviePersonnel ArrayList, showcasing people involved in the movie and their roles in the movie
     * Method is private as it is only used internally as part of the printMovie() function.
     */
    private void printMoviePersonnel() {
        for (MoviePersonnel moviePersonnel : this.moviePersonnelList) {
            System.out.println("Name: " + moviePersonnel.getName() + "\t Role: " + moviePersonnel.getRole());
        }
    }
    /**
     * Prints the Name and Role of each MoviePersonnel object in the moviePersonnel ArrayList including the index, showcasing people involved in the movie and their roles in the movie. This will be used externally to edit the individual MoviePersonnel objects 
     * @return returns the index of each MoviePersonnel
     */
    public int printMoviePersonnelIndex() {
        int i = 1;
        for (MoviePersonnel moviePersonnel : this.moviePersonnelList) {
            System.out.println(i + " - Name: " + moviePersonnel.getName() + "\t Role: " + moviePersonnel.getRole());
            i++;
        }
        return (i-1);
    }
    /**
     * Prints out all the details of the movie
     * If number of reviews are fewer than 2, NIL is displayed instead of the average ratings
     */
    public void printMovie() {
        System.out.println("=======================");
        System.out.println("Movie name: " + movieName);
        System.out.println("Movie ID: "+ movieID);
        System.out.printf("Movie details: %s, %s\n", movieHype, viewingMode);
        System.out.println("Movie is rated: " + ageRating);
        System.out.println("Sypnosis: " + synopsis);
        System.out.println("Number of reviews: " + overallReviews.getReviewCount());
        if (overallReviews.getReviewCount() < 2){
            System.out.println("Movie avg rating: NIL");
        }
        else{
            System.out.printf("Movie avg rating: %.1f\n" ,overallReviews.getAvgRating());
            System.out.println("Reviews:\n");
            for (int i=0;i<overallReviews.getReviewCount();i++){
                System.out.println("Rating:" + overallReviews.getReview(i).getReviewRating());
                System.out.println("Description:");
                System.out.println(overallReviews.getReview(i).getReviewDescription() + "\n");
            }
        }
        System.out.println("Showing status: " + showingStatus);
        System.out.println("Movie sales: " + movieSales);
        System.out.println("Movie duration: " + movieDuration.toMinutes() + " minutes");
        printMoviePersonnel();
        System.out.println("=======================");
    }

}
