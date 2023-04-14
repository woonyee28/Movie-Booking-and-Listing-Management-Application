package models;

public class Review {
    private int reviewsID;
    private int movieGoersID;
    private double rating;
    private String reviews;
    private int movieID;

    public Review(){}
    
    /**
     * Initialise the variables of an instance Review class
     * @param reviewsID
     * @param movieGoersID
     * @param rating
     * @param reviews
     * @param movieID
     */
    public Review(int reviewsID, int movieGoersID, double rating, String reviews, int movieID){
        this.reviewsID = reviewsID;
        this.movieGoersID = movieGoersID;
        this.rating = rating;
        this.reviews = reviews;
        this. movieID = movieID;
    }

    // get methods
    /**
     * returns the reviews ID of an review object
     * @return
     */
    public int getReviewsID(){
        return this.reviewsID;
    }

    /**
     * returns the movieGoersID of an review object (ID of customer who made a review)
     * @return
     */
    public int getMovieGoersID(){
        return this.movieGoersID;
    }

    /**
     * returns the rating of an review object
     * @return
     */
    public double getRating(){
        return this.rating;
    }

    /**
     * returns the reviews of an review object
     * @return
     */
    public String getReviews(){
        return this.reviews;
    }

    /**
     * returns the movieID of a review object (ID of the movie that has been reviewed)
     * @return
     */
    public int getMovieID(){
        return this.movieID;
    }

    // set methods

    /**
     * sets the ReviewID for a review object
     * @param reviewsID
     */
    public void setReviewsID(int reviewsID){
        this.reviewsID = reviewsID;
    }

    /**
     * sets the movieGoerID for a review object
     * @param movieGoersID
     */
    public void setMovieGoersID(int movieGoersID){
        this.movieGoersID = movieGoersID;
    }

    /**
     * sets the rating for a review object
     * @param rating
     */
    public void setRating(double rating){
        this.rating = rating;
    }

    /**
     * sets the reviews for a review object
     * @param reviews
     */
    public void setReviews(String reviews){
        this.reviews = reviews;
    }

    /**
     * sets the movieID for a review object
     * @param movieID
     */
    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    private static final String CSV_SEPARATOR = ",";
    /**
     * Generate String representation of the Object
     * appends all returns all variables of a review object as a string
     */
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getReviewsID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieGoersID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getRating());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getReviews());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieID());

        return oneLine.toString();
    }
}
