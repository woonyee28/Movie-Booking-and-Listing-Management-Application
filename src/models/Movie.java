package models;

import java.util.*;

public class Movie{
    private int movieID;
    private String title; 
    private String type;
    private String synopsis;
    private Double rating;
    private String showingStatus;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<Integer> reviewsID;


    public Movie(){};
    
    /**
     * Initialise variables of an instance of Movie Class
     * @param movieID
     * @param title
     * @param type
     * @param synopsis
     * @param rating
     * @param showingStatus
     * @param director
     * @param cast
     * @param reviewsID
     */
    public Movie(int movieID, String title, String type, String synopsis, Double rating, String showingStatus, String director, ArrayList<String> cast, ArrayList<Integer> reviewsID)
    {
        this.movieID = movieID;
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.rating = rating;
        this.showingStatus = showingStatus;
        this.director = director;
        this.cast = cast;
        this.reviewsID = reviewsID;   
    }

    //get methods
    /**
     * return movieID of a movie in a Movie object
     * @return
     */
    public int getMovieID(){
        return this.movieID;
    }

    /**
     * return title of movie in a Movie object
     * @return
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * return type of movie in a Movie Object
     * @return
     */
    public String getType(){
        return this.type;
    }

    /**
     * return synopsis of movie in a Movie object
     * @return
     */
    public String getSynopsis(){
        return this.synopsis;
    }

    /**
     * return ratings of movie in a Movie object
     * @return
     */
    public Double getRating(){
        return this.rating;
    }

    /**
     * return showing status of movie in a Movie object
     * @return
     */
    public String getShowingStatus(){
        return this.showingStatus;
    }

    /**
     * return the name of director of movie in a Movie object
     * @return
     */
    public String getDirector(){
        return this.director;
    }

    /**
     * return the names of cast of movie in a Movie object
     * @return
     */
	public ArrayList<String> getCasts() {
		return this.cast;
	}

    /**
     * return the reviews of movie in a Movie object
     * @return
     */
	public ArrayList<Integer> getReviewsID() {
		return this.reviewsID;
	}

    //set methods
    /**
     * set movieID of movie in a Movie object
     * @param movieID
     */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

    /**
     * set title of movie in a Movie object
     * @param title
     */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * set type of movie in a Movie object
     * @param type
     */
	public void setType(String type) {
		this.type = type;
	}

    /**
     * set synopis of movie in a Movie object
     * @param synopsis
     */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

    /**
     * set rating of movie in a Movie object
     * @param rating
     */
	public void setRating(Double rating) {
		this.rating = rating;
	}

    /**
     * set showing status of movie in a Movie object
     * @param showingStatus
     */
	public void setShowingStatus(String showingStatus) {
		this.showingStatus = showingStatus;
	}

    /**
     * set name of director of movie in a Movie object
     * @param director
     */
	public void setDirector(String director) {
		this.director = director;
	}

    /**
     * set names of cast of movie in a Movie object
     * @param cast
     */
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

    /**
     * set reviews of movie in a Movie object
     * @param reviewsID
     */
	public void setReviewID(ArrayList<Integer> reviewsID) {
		this.reviewsID = reviewsID;
	}

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * convert to string
     * @return String
     */
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getMovieID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getTitle());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getType());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getSynopsis());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getRating());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getShowingStatus());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getDirector());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getCasts());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getReviewsID()); 
        return oneLine.toString();
    }
}