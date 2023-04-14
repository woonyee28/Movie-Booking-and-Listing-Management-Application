package tests;
import java.text.ParseException;

import managers.*;;
public class testBookEOS {
    
    /** 
     * @param args
     */
    public static void main(String [] args){
        MovieBookingManager mbm = new MovieBookingManager(0, 0);
        mbm.showMovieListing("AAA");
        try {
            mbm.bookings(0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
