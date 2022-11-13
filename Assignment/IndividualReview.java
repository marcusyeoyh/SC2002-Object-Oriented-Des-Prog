/**
 * The individual review class stores a review done by a movie goer.
 * It includes a string and a rating from (0-5).
 * 
 * @author Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */
public class IndividualReview {
    /**
     * The review score given to the movie by a user in int datatype
     */
    private int reviewRating;

    /**
     * The review description given to the movie by a user in String datatype
     */
    private String reviewDescription;

    /**
     * Creates a new IndividualReview object
     * @param reviewRating Movie rating from 0-5 in int datatype
     * @param reviewDescription Movie review in String datatype
     */
    public IndividualReview(int reviewRating, String reviewDescription) {
        this.reviewRating = reviewRating;
        this.reviewDescription = reviewDescription;
    }

    /**
     * Gets the review score of this review
     * @return review score of this review in int datatype
     */
    public int getReviewRating() {
        return this.reviewRating;
    }

    /**
     * Gets the review description of this review
     * @return review description of this review in String datatype
     */
    public String getReviewDescription() {
        return this.reviewDescription;
    }

    /**
     * Changes review rating of this review
     * @param reviewRating new review rating of this review in int datatype
     */
    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    /**
     * Changes review description of this review
     * @param reviewDescription new review description of this review in String datatype
     */
    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
