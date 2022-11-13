import java.util.ArrayList;

/**
 * The OverallReviews class stores reviews and keeps track of the
 * number of reviews, as well as the average rating as reviews are added.
 * 
 * @author Marcus Yeo, Low Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */

public class OverallReviews {
    /**
     * ArrayList of containing IndividualReviews objects
     */
    private ArrayList<IndividualReview> reviews = new ArrayList<>();
    /**
     * count of the number of reviews for a particular movie
     */
    private int reviewCount=0;
    /**
     * Average rating for a particular movie
     */
    private double avgRating=0;

    /**
     * Gets the number of reviews of a particular movie
     * @return This returns the number of reviews in OverallReviews object in int datatype
     */
    public int getReviewCount() {
        return this.reviewCount;
    }

    /**
     * Gets the average rating of a particular movie
     * @return This returns the average rating of the reviews in the OverallReviews object in double datatype
     */
    public double getAvgRating() {
        return this.avgRating;
    }

    /**
     * Gets the particular IndividualReview object from the ArrayList depending on the index input
     * @param index This is the index of the individual review in the list.
     * @return IndividualReview This returns the individual review at the specified index.
     */
    public IndividualReview getReview(int index){
        return this.reviews.get(index);
    }

    /**
     * This method creates an IndividualReview object and adds it to the OverallReviews object.
     * The average rating and review count are updated within the method.
     * 
     * @param reviewRating The rating of the movie (1-5).
     * @param reviewDescription The description of the review.
     */
    public void addReview(int reviewRating, String reviewDescription) {
        reviews.add(new IndividualReview(reviewRating, reviewDescription));
        this.avgRating = (this.avgRating * this.reviewCount + reviewRating)/(this.reviewCount+1);
        this.reviewCount++;
    }
}
