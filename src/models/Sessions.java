package models;

import java.util.ArrayList;

public class Sessions {
    int movieID;
    String sessionDate;
    String sessionTime;
    ArrayList<Integer> seatingPlan;

    public Sessions() {
    }

    public Sessions(int movieID, String sessionDate, String sessionTime, ArrayList<Integer> seatingPlan) {
        this.movieID = movieID;
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
        this.seatingPlan = seatingPlan;
    }

    
    /** 
     * get movie id
     * @return int
     */
    public int getMovieID() {
        return this.movieID;
    }

    
    /** 
     * set movie id
     * @param movieID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    
    /** 
     * get session date
     * @return String
     */
    public String getSessionDate() {
        return this.sessionDate;
    }

    
    /** 
     * set session date
     * @param sessionDate
     */
    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    
    /** 
     * get session time
     * @return String
     */
    public String getSessionTime() {
        return this.sessionTime;
    }

    
    /** 
     * set session time
     * @param sessionTime
     */
    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    
    /** 
     * get seating plan
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getSeatingPlan() {
        return this.seatingPlan;
    }

    
    /** 
     * set seating plan
     * @param seatingPlan
     */
    public void setSeatingPlan(ArrayList<Integer> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    
    /** 
     * Generate String representation of the Object
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " movieID='" + getMovieID() + "'" +
            ", sessionDate='" + getSessionDate() + "'" +
            ", sessionTime='" + getSessionTime() + "'" +
            ", seatingPlan='" + getSeatingPlan() + "'" +
            "}";
    }
    
}
