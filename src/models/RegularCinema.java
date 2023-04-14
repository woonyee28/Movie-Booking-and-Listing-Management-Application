package models;

import java.util.ArrayList;

public class RegularCinema extends Cinemas{
    private String cinemaClass;

    /**
     * Initialise variables of any instance of Gold Cinemas class
     * @param cinemaCode
     * @param cinemaClass
     * @param seatingPlan
     * @param sessionsID
     */
    public RegularCinema(String cinemaCode, ArrayList<Integer> seatingPlan, ArrayList<String> sessionsID, String cinemaClass) {
		super(cinemaCode,seatingPlan,sessionsID);
        this.cinemaClass = cinemaClass;
	}
    /**
     * get Cinema Class of the Cinema
     */
    @Override
    public String getCinemaClass() {
        return this.cinemaClass;
    }
    /**
     * set Cinema Class of the Cinema
     */
    @Override
    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }
    
}
