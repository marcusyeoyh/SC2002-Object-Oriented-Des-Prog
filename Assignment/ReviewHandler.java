import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Revie handler Interface
 * Used to allow the Movie-Goer to write a new review
 * 
 * @author Marc Chern
 * @version 1.0.0 Nov 12, 2022
 */
public interface ReviewHandler {
    /**
     * Scanner to receive input
     */
    Scanner sc = new Scanner(System.in);
    /**
     * Method to write a new review
     * Will not return anything but will write the review into the OverallReviews
     */
    default void writeReview() {
        // Getting moviesStore
        MovieStore movieStore = MovieStore.getInstance();
        // Getting review information
        System.out.print("Enter movieID to write a review for (-1 to return): ");
        String movieID = sc.nextLine();
        if (movieID.equals("-1")){
            return;
        }
        Movie reviewMovie = movieStore.searchMovie(movieID);
        if (reviewMovie==null){
            System.out.println("Error: movie not found");
            return;
        }
        // Review Rating
        int reviewRating = 0;
        do {
            System.out.println("Enter Movie Rating (1 - 5 Stars): ");
            try{
                reviewRating = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid rating (1 - 5).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
        } while (reviewRating <= 0 || reviewRating > 5);

        // Review Description
        System.out.println("Enter Review Description:");
        String reviewDes = sc.nextLine();

        reviewMovie.getOverallReviews().addReview(reviewRating, reviewDes);
        System.out.println("Exiting review module...");
    }
 }