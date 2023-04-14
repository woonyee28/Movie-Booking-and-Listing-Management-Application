package tests;
import java.text.ParseException;

import managers.*;
import models.Movie;
public class testEOS {
    
    /** 
     * @param args
     */
    public static void main(String[] args){
        SessionManager sm = new SessionManager(0);
        MovieManager mm = new MovieManager(0);
        MovieBookingManager mbm = new MovieBookingManager(0, 0);
       mbm.showMovieListing("AAA");
       mm.modifyMovie();
       mbm.showMovieListing("AAA");
        try {
            mbm.bookings(0);
        } catch (ParseException e) {
           
            e.printStackTrace();
        }
        
    }
}
