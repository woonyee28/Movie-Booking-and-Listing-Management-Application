package tests;
import java.text.ParseException;

import managers.*;
public class testHolPriceConfig {
    
    /** 
     * @param args
     */
    public static void main(String[] args){
        SettingsManager sm = new SettingsManager(1);
        sm.printPriceList();
        sm.editPrices();
        sm.printHolidayList();
        MovieBookingManager mbm = new MovieBookingManager(1, 0);
        try {
            mbm.bookings(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
