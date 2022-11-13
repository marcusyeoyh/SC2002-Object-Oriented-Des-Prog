import java.util.HashMap;
import java.util.Scanner;
/**
 * MovieQuery Interface which extends the SysSettings Interface
 * Used for all forms of Movie related listing and searching which the Movie-Goer will utilise
 * @author Marc Chern
 * @version 1.0.0 Nov 12, 2022
 */
public interface MovieQuery extends SysSettings{
    /**
    * Creates a Scanner object which allows for user inputs
    */
    Scanner sc = new Scanner(System.in);
    /**
     * Lists the top 5 movies based on either rating or sales
     * Options will change depending on the Admin settings
     */
    default void listBy() {
        MovieStore movStore = MovieStore.getInstance();
        String listing_option = getListBy();
        switch (listing_option) {
            case "NIL" -> listByOptions();
            case "SALES" -> movStore.ListTop5(0);
            case "AVG_RTG" -> movStore.ListTop5(1);
        }
    }
    /**
     * Lists all movies, while not showing those which should not be shown to the Movie-Goer, such as ENDOFSHOWING and COMINGSOON
     */
    default void listAllMovies() {
        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        for (String key: movies.keySet()) {
            if (movies.get(key).getShowingStatus() == Status.NOWSHOWING || movies.get(key).getShowingStatus() == Status.PREVIEW){
                System.out.printf(" %-5s| %-12s|%s\n",movies.get(key).getMovieID(),movies.get(key).getShowingStatus(),movies.get(key).getMovieName());
            }
        }
    }
    /**
     * Overloaded listAllMovies
     * Lists all movies including ENDOFSHOWING and COMINGSOON
     */
    default void listAllMovies(int toggle) {
        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        System.out.println("MovieID|Showing Status|Title");
        for (String key: movies.keySet()) {
            System.out.printf("  %-5s|  %-12s|%s\n",movies.get(key).getMovieID(),movies.get(key).getShowingStatus(),movies.get(key).getMovieName());
        }
    }

    /**
     * Default searchMovie method
     * Used to ask for the movie requested and output the details
     */
    default void searchMovie() {
        MovieStore movStore = MovieStore.getInstance();

        System.out.println("-------Searching Movies-------");
        System.out.println("Enter MovieID (-1 to return): ");
        String movieID = sc.nextLine();

        if (movieID.equals("-1")){
            return;
        }

        Movie movie = movStore.searchMovie(movieID);
        if (movie == null || movie.getShowingStatus() == Status.COMINGSOON || movie.getShowingStatus() == Status.ENDOFSHOWING ){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            printMovieInfo(movieID);
        }

    }
    /**
     * Overloaded searchMovie method
     * Used during the buying ticket process
     * @param movieID - takes in the movieID which the Movie-Goer selects, and directly displays the movie details 
     */
    default Movie searchMovie(String movieID) {
        MovieStore movStore = MovieStore.getInstance();
        Movie movie = movStore.searchMovie(movieID);
        if (movie == null || movie.getShowingStatus() == Status.COMINGSOON || movie.getShowingStatus() == Status.ENDOFSHOWING){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            printMovieInfo(movieID);
        }
        return movie;
    }
    /**
     * Private method used to display movie details 
     * @param movieID - takes in movieID to know which movie to output
     */
    private void printMovieInfo(String movieID) {
        Movie movie = MovieStore.getInstance().searchMovie(movieID);
        movie.printMovie();
    }
    /**
     * Private method to display menu to prompt Movie-Goer to select which type of ranking they would like to see
     */
    private void listByOptions() {
        MovieStore movStore = MovieStore.getInstance();

        System.out.println("List movies by: ");
        System.out.println("(1) Sales ");
        System.out.println("(2) Average Rating ");
        System.out.println("Enter choice: ");

        int choice = -1;

        try{
            choice = sc.nextInt();
        } catch (Exception err){
            System.out.println("Error: Please input a valid number (1 - 4).\n");
        }
        sc.nextLine();

        switch(choice) {
            case 1:
                movStore.ListTop5(0);
                break;
            case 2:
                movStore.ListTop5(1);
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
}
