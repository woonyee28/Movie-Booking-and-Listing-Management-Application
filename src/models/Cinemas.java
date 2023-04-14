package models;

import java.util.ArrayList;

public abstract class Cinemas {
    String cinemaCode;
    ArrayList<Integer> seatingPlan;
    ArrayList<String> sessionsID;

    public Cinemas() {
    }

    /**
     * Initialise variables of any instance of Cinemas class
     * @param cinemaCode
     * @param cinemaClass
     * @param seatingPlan
     * @param sessionsID
     */
    public Cinemas(String cinemaCode, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID) {
        this.cinemaCode = cinemaCode;
        this.seatingPlan = seatingPlan;
        this.sessionsID = sessionsID;
    }

    /**
     * return cinemaCode
     * @return
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

    /**
     * set cinemaCode
     * @param cinemaCode
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * returns cinemaClass of a Cinema object - regular, gold, platinum
     * @return
     */
    public abstract String getCinemaClass(); 

    /**
     * set cinemaClass
     * @param cinemaClass
     */
    public abstract void setCinemaClass(String cinemaClass);

    /**
     * returns seating plan of a Cinema object
     * @return
     */
    public ArrayList<Integer> getSeatingPlan() {
        return this.seatingPlan;
    }

    /**
     * set seatingPlan for a Cinema object
     * @param seatingPlan
     */
    public void setSeatingPlan(ArrayList<Integer> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    /**
     * returns sessionID of a Cinema Object
     * @return
     */
    public ArrayList<String> getSessionsID() {
        return this.sessionsID;
    }

    /**
     * set sessionID of a Cinema Object
     * @param sessionsID
     */
    public void setSessionsID(ArrayList<String> sessionsID) {
        this.sessionsID = sessionsID;
    }

    
    /** 
     * convert to string
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " cinemaCode='" + getCinemaCode() + "'" +
            ", cinemaClass='" + getCinemaClass() + "'" +
            ", seatingPlan='" + getSeatingPlan() + "'" +
            ", sessionsID='" + getSessionsID() + "'" +
            "}";
    }

    
}
