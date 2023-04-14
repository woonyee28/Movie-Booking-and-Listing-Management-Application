package managers;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import models.Cinemas;
import models.Cineplexes;
import models.MovieGoer;
import models.Sessions;
import models.Transaction;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import serializers.MovieGoerSerializer;
import serializers.SessionSerializer;
import serializers.TransactionSerializer;


public class AmendBookingManager {
    private int id;
    private int adminOrmember;
    static CinemaSerializer cs = new CinemaSerializer();
    static CineplexSerializer cps = new CineplexSerializer();
    private static ArrayList<Cineplexes> Cineplex = cps.readFromCSV();
    private static ArrayList<Cinemas> Cinema = cs.readFromCSV();
    static TransactionSerializer trans = new TransactionSerializer();
    static SessionSerializer sss = new SessionSerializer();
    static MovieGoerSerializer mgs = new MovieGoerSerializer();
    
    public AmendBookingManager(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }

    public void run() {
        String cineplex;
        String cineplex_choice;
        String cinema_code =null;
        int cinema_class;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  

        ArrayList<String> SessionID = new ArrayList<>();
        ArrayList<Integer> seatingPlan = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amend Booking Page:");
        // System.out.println("Please select which Cineplex would you like to amend");
        int delseatnum = -1;
        int movieid = -1;

        while(cinema_code==null)
        {
            if (adminOrmember==1){
                Scanner n = new Scanner(System.in);
                for (MovieGoer mg: mgs.readFromCSV()){
                    System.out.println(mg.toString());
                }
                System.out.println("\nWhich MovieGoerID you would like to ammend: \n");
                int movieGoerID = n.nextInt();
                this.id = movieGoerID;
            }
            BookingHistManager bhm = new BookingHistManager(this.id);
            bhm.showBookingHistory(this.id);
            System.out.println("Please select which TID you would like to cancel booking:\n");
            String tid = sc.nextLine();

            
            for (Transaction t: trans.readFromCSV()){
                if (tid.equals(t.getTID())){
                    movieid = t.getMovieID();
                    delseatnum = t.getSeatingNum();
                    cinema_code = t.getCinemaCode().toLowerCase();
                    Transaction del = new Transaction();
                    del.setTID(tid);
                    trans.deleteFromCSV(del);
                    //DELETE THE TRANSACTION
                }
            }
        }
        
        SessionID = getSessionID(cinema_code);
        seatingPlan = getOccupiedSeats(SessionID);
        String one = getOneSessionID(SessionID);
        

        System.out.println("Here is the seating plan BEFORE amending for Cinema " + cinema_code.toUpperCase()+":");
        System.out.println("------------SCREEN------------");
        printSeatingPlan(seatingPlan);
    
        int noOfSeats=1;
        int check_seat;
        while(true)
        {
            if (noOfSeats == 1)
            {
                check_seat = amendSeat(seatingPlan, one,delseatnum,movieid);
                
                //if not in db, means user selected a seat that is already empty
            }
            else
            {
                for (int i=1; i<=noOfSeats; i++)
                {
                    System.out.println("Selecting seat " + i + "..");
                    check_seat = amendSeat(seatingPlan, one, delseatnum,movieid);
              
                    
                }
            }
            break;
        }
        System.out.println(seatingPlan);
        cinema_class = getCinemaClass(cinema_code);
        System.out.println("Here is the seating plan AFTER amending for Cinema " + cinema_code.toUpperCase()+":");
        System.out.println("------------SCREEN------------");
        printSeatingPlan(seatingPlan);
        // System.out.println("Please select Which seat would you like to cancel?");

        // seat = sc.nextInt();
    }
        
        

    

        
    
    /** 
     * Wil ask user for which seat do they wan to remove , and remove the data locally
     * @param seatingPlan
     * @param SessionID
     * @param seat
     * @param movieid
     * @return int
     */
    
    private static int amendSeat(ArrayList<Integer> seatingPlan, String SessionID, int seat,int movieid)
    {
        
        int getIndex;
        Scanner sc = new Scanner(System.in);
        
        for(Integer i : seatingPlan)
        {
            // seat is inside the DB
            if (i==seat)
            {
                //need to add integer.value of to remove that integer value else will remove by the index
                getIndex = seatingPlan.indexOf(seat);
                seatingPlan.remove(getIndex);

                
                String date = SessionID.substring(0, 8);
                String time = SessionID.substring(8,12);
                              
                
                Sessions s = new Sessions();
                s.setMovieID(movieid);
                s.setSessionDate(date);
                s.setSessionTime(time);
                s.setSeatingPlan(seatingPlan);
                sss.updateFromCSV(s);
    
                // seatingPlan.remove(Integer.valueOf(seat));
                // System.out.println("Successfully remove seat");
                return 1;
            }
            // return -1
        }

    // seat not in db
        System.out.println("The seat you selected is already empty");
        System.out.println("Please select the seats with 'X' ");
        
        return -1;
        // return -1;
    }

    
    /** 
     * @param cinema_class
     * @return String
     */
    private static String toStringClass(int cinema_class)
    {
        if(cinema_class == 1)
        {
            return "Regular";
        }
        else if (cinema_class==2)
        {
            return "Gold";
        }
        else
        {
            return "Platinum";
        }
    }

    
    
    /** 
     * get getOccupiedSeats will return ArrayList of occupied seats with the cinema_choice input (aaa,bbb..)
     * get from session
     * @param cinema_code
     * @return ArrayList<Integer>
     */
    
    private static ArrayList<Integer> getOccupiedSeats(ArrayList<String> cinema_code)
    {

        SessionSerializer ss = new SessionSerializer();
        for(Sessions m : ss.readFromCSV())
        {
            if(cinema_code.contains(m.getSessionDate()+m.getSessionTime()))
            {
                return m.getSeatingPlan();
                // System.out.println(m.getSessionDate()+m.getSessionTime());
            }
        }
        return null;
    }

        
    /** 
     * get OneSessionID will return Session String
     * @param cinema_code
     * @return String
     */
    private static String getOneSessionID(ArrayList<String> cinema_code)
    {

        SessionSerializer ss = new SessionSerializer();
        for(Sessions m : ss.readFromCSV())
        {
            if(cinema_code.contains(m.getSessionDate()+m.getSessionTime()))
            {
                return m.getSessionDate()+m.getSessionTime();
                // System.out.println(m.getSessionDate()+m.getSessionTime());
            }
        }
        return null;
    }

    
    /** 
     * return session ID
     * @param cinema_code
     * @return ArrayList<String>
     */
    private static ArrayList<String> getSessionID(String cinema_code)
    {
        for(Cinemas c : Cinema)
        {
            if(c.getCinemaCode().toLowerCase().equals(cinema_code))
            {
                return c.getSessionsID();
            }
        }
        return null;

    }

        
    
    /** 
     * print seating plan
     * @param seatingPlan
     */
    private static void printSeatingPlan(ArrayList<Integer> seatingPlan)
    {
        int count =0;
        final  int TOTALSEAT = 70;

        Collections.sort(seatingPlan);


        
        for (int i =1; i<=TOTALSEAT; i++)
        {
            //check for every occupied seat while looping through the seat.
            //if occupied, print "X" 
            for (Integer c : seatingPlan)
            {

                if (i == c)
                {
                    System.out.print("X  ");
                    i++;
                    count++;
                    if (count==10)
                        {
                        System.out.println();
                        count=0;
                        break;
                        }
                    continue;

                    
                }
            }
            //Printing layout to be easily visualized in console
            if(i<10)
            {
                System.out.print("0"+i+ " ");
                count++;
            }
            else
            {
                System.out.print(i + " ");
                count++;
                if (count==10)
                {
                System.out.println();
                count=0;
                }
                
            }
        
        
        }

    }
    
    /** 
     * archive getcinemacode_V1
     * @param cineplex_choice
     * @return String
     */
    private static String getCineCode_V1(String cineplex_choice)
    {
        String cinema_choice ="";
        Scanner sc = new Scanner(System.in);
        int error  =0;
        //Prints entire CinemaCode in the Cineplex
        System.out.println(Cineplex.size());
        for(int i =0; i<Cineplex.size(); i++)
        {
            if(Cineplex.get(i).getCineplexCode().equals(cineplex_choice.toUpperCase()))
            {
                System.out.println(Cineplex.get(i).getCinemasCode());
                System.out.println("Please select which Cinema you would like to cancel: ");
                cinema_choice = sc.next();
                if( Cineplex.get(i).getCinemasCode().contains(cinema_choice.toUpperCase()))
                {
                    return cinema_choice.toLowerCase();
                }
                else
                {
                    System.out.println("Please Enter Correctly");
                    return null;
                }
            }
        }
        //if nothing is triggered, likely error, not found in DB or user enter incorrectly

        System.out.println("Some error occured");
        return null;

    }


    
    /** 
     * Parse in which cinmea user selected and return which class
     * Regular - 1, Gold - 2, Plat 3
     * @param cinema_code
     * @return int
     */
    private static int getCinemaClass(String cinema_code)
    {
        for(Cinemas c : Cinema)
        {
            // System.out.println("A" + c.getCinemaCode());
            if(c.getCinemaCode().toLowerCase().equals(cinema_code))
            {
                if(c.getCinemaClass().equals("Gold"))
                {
                    return 2;
                }
                else if (c.getCinemaClass().equals("Platinum"))
                {
                    return 3;
                }
                else if (c.getCinemaClass().equals("Regular"))
                {
                    return 1;
                }
                // System.out.println(c.getCinemaClass());
                // return c.getCinemaClass();
            }

        }
        //if not found in csv
        return -1;

    }

}