

package managers;
import models.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import models.Cineplexes;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import serializers.TransactionSerializer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;    
import models.Movie;
import serializers.MovieSerializer;
import serializers.SessionSerializer;
import models.Sessions;
import models.Transaction;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;



// this class defines the movie booking of movies selected
public class MovieBookingManager {

    private static int movieid_selected;
    private static String movie_date;
    private static String movie_time;
    private int adminOrmember; // 1 == admin, 0 == member, -1 == Guest
    private int id; // -1 == Member

    public MovieBookingManager(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }


    static CinemaSerializer cs = new CinemaSerializer();
    static CineplexSerializer cps = new CineplexSerializer();
    static SessionSerializer ss = new SessionSerializer();
    static MovieSerializer ms = new MovieSerializer();
    static TransactionSerializer ts = new TransactionSerializer();
    private static ArrayList<Cineplexes> Cineplex = cps.readFromCSV();
    private static ArrayList<Cinemas> Cinema = cs.readFromCSV();

    public void showMovieListing()
    {
        for (Movie m: ms.readFromCSV()) {           
            m.toString();
            System.out.println(m.getMovieID()+1 +": " +  m.getTitle()); 
        }
    }

    // parse in cinema_code to get the sessionID, to print out relevant movies according to the cinema chose
    public ArrayList<Integer> showMovieListing(String cinema_code)
    {
        ArrayList<Integer> movieID = new ArrayList<>();
        ArrayList<Integer> seatPlan = new ArrayList<>();
        // for(Cinemas cine : cs.readFromCSV())
        // {
        //     System.out.println(cine.getSessionsID());
        //     for(Sessions m : ss.readFromCSV());
        //     {
        //         System.out.println(m);
        //     }
        // }
        Scanner sc = new Scanner(System.in);
        int which_cine =-1;
        for(int i =0; i<Cineplex.size();  i++)
        {
            if(Cinema.get(i).getCinemaCode().equals(cinema_code.toUpperCase()))
            {
                for(Sessions m : ss.readFromCSV())
                {
                    if(Cinema.get(i).getSessionsID().contains(m.getSessionDate()+m.getSessionTime()))
                    {
                        which_cine = i;
                        // System.out.println(m.getSessionDate()+m.getSessionTime());
                        movieID.add(m.getMovieID());
                    }
                }
            }
        }
        for (Movie m: ms.readFromCSV())
        {
            if(movieID.contains(m.getMovieID()))
            {
                System.out.println(m.getMovieID()+1 + ": "+ m.getTitle());
            }
        }
        if(which_cine == -1)
        {
            System.out.println("No session availble from the movie you selected");
            return null;
        }
        System.out.println("Please select the movie");
        movieid_selected = sc.nextInt() - 1;
        // movieid_choice = sc.nextInt() - 1 ;
        System.out.println("Please select the movie time");
        int count =0;
        for(Sessions m : ss.readFromCSV())
        {
            
            if(movieid_selected == m.getMovieID())
            {
                count++;
                
                System.out.println(count + ": Date: " + m.getSessionDate() + " Time: " + m.getSessionTime());
            }
        }
        count =0;
        int movie_time_choice = 0;
        movie_time_choice = sc.nextInt();
        for (Sessions m :  ss.readFromCSV())
        {
            if (movieid_selected == m.getMovieID())
            {
                count++;
                if (movie_time_choice == count)
                {
                    movie_date = m.getSessionDate();
                    movie_time = m.getSessionTime();
                    System.out.println(m.getSeatingPlan());
                    seatPlan = m.getSeatingPlan();
                }
            }
        }
        return seatPlan;
    }

    public void showSeatPlan()
    {
        String cineplex_choice;
        String cinema_code =null;
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> seatingPlan = new ArrayList<>();
        while(cinema_code==null)
        {
        for (Cineplexes m : Cineplex)
        {
            System.out.print(m.getCineplexCode() + ": " + m.getName() + "  ");
        
        }
        System.out.println();

        System.out.print("Selection(AA,BB,CC):");
        cineplex_choice = sc.next();
        
        
        cinema_code = getCineCode_V1(cineplex_choice);
        }
        System.out.println("Which movie would you like to view?");
        seatingPlan =  showMovieListing(cinema_code);
        printSeatingPlan(seatingPlan);


    }
    public void bookings() throws ParseException 
    {   
        String cineplex_choice;
        String cinema_code =null;
        int movie_choice;
        int cinema_class;
        ArrayList<Integer> seatingPlan = new ArrayList<>();
        ArrayList<String> SessionID = new ArrayList<>();
        ArrayList<Integer> selectedSeat = new ArrayList<>();

        Boolean loop_seat = true;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        int noOfSeats;
        

        Scanner sc = new Scanner(System.in);
        while(cinema_code==null)
        {
            System.out.println("Please select which Cineplex you would like to book:\n");
            for (Cineplexes m : Cineplex)
            {
                System.out.print(m.getCineplexCode() + ": " + m.getName() + "  ");
            
            }
            System.out.println();
        
            System.out.print("Selection(AA,BB,CC):");
            cineplex_choice = sc.next();
            
            cinema_code = getCineCode_V1(cineplex_choice);
        }
        SessionID = getSessionID(cinema_code);
        
        System.out.println("Which movie would you like to book?");
        seatingPlan =  showMovieListing(cinema_code);
        System.out.println(seatingPlan);

        cinema_class = getCinemaClass(cinema_code);
        if (cinema_class == -1)
        {
            System.out.println("Some Error Occured, data might not be in database");
        }

        System.out.println("Here is the seating plan for Cinema " + cinema_code.toUpperCase()+":");
        System.out.println("------------SCREEN------------");
        printSeatingPlan(seatingPlan);
        System.out.println("How many seats would you like?");
        
        int seat = 0;
        while(true)
        {
            noOfSeats = sc.nextInt();
            // System.out.println(seatingPlan.size());
            if (noOfSeats<1)
            {
                System.out.println("Please select at least 1 seat..");
                // seat = 0;
                continue;
            }
            else if(noOfSeats >= 70-seatingPlan.size())
            {
                System.out.println("No of seat exceeded remaining seats left, Please select again");
                // seat = 0;
                continue;
            }
            break;

        }
    
        if (noOfSeats == 1)
        {
            System.out.println("Which seat would you like?");
            seat = bookSeats(seatingPlan);
            //Here to add Tarun's requested seat. selectedSeat is an array consists of user selected seats
            selectedSeat.add(seat);

            seatingPlan.add(seat);
            // LocalDateTime now = LocalDateTime.now();
            // SessionID.add(dtf.format(now));
            //TO IMPLEMENT NO: OF SeATS
        }
        else
        {
            //If seats is more than 1, allow user to book multiple seats
            for(int z =1; z<=noOfSeats; z++)
            {
                // display which seat user is selecting
                System.out.println("Please select the seat you would like for seat "+ z  );
                seat = bookSeats(seatingPlan);
                //Here to add Tarun's requested seat. selectedSeat is an array consists of user selected seats
                selectedSeat.add(seat);

                seatingPlan.add(seat);
                // LocalDateTime now = LocalDateTime.now();
                // SessionID.add(dtf.format(now));
            }
        }
        double cost = ticketTransact(movieid_selected, cinema_code, cinema_class, movie_date, movie_time, selectedSeat, 1);
        System.out.printf("Total price: $%.2f \n", cost);


    

        // FORMAT OF SESSION ID: DATE+TIME (yyyyMMddHHmm) Year->Month>Day->Hours->Minutes
        
        //adds current time to SessionID

        //update data into Sessions CSV
        Sessions s = new Sessions();
        s.setMovieID(movieid_selected);
        s.setSessionDate(movie_date);
        s.setSessionTime(movie_time);
        s.setSeatingPlan(seatingPlan);
        ss.updateFromCSV(s);
        System.out.println("Successfully booked the seat you requested");
        
        
        // //Update data into CSV
        // Cinemas up = new Cinemas();
        // up.setCinemaClass(toStringClass(cinema_class));
        // up.setCinemaCode(cinema_code.toUpperCase());
        // up.setSeatingPlan(seatingPlan);
        // up.setSessionsID(SessionID);
        // cs.updateFromCSV(up);
        // System.out.println("Successfully booked the seat you requested");


        //at this point can either call the function again or go back main page.

        }

        public int bookSeats( ArrayList<Integer> seatingPlan)
    {
        Boolean loop_seat = true;
        Scanner sc = new Scanner(System.in);
        int seat = 0;
        while(loop_seat)
            {
                int check_seat =0;
                if(sc.hasNextInt())
                {
                    seat = sc.nextInt();
                if(seat>70 || seat <1)
                {
                    System.out.println("Please choose seat that are available");
                    continue;
                }
                for(Integer i : seatingPlan)
                {
                    if(seat == i)
                    {
                        check_seat++;
                        System.out.println("Seat Taken. Please select another seat");
                        break;
                    }
                                
                }
                if(check_seat == 0)
                {
                    loop_seat = false;
                }
                }
                

                else
                {
                    sc.nextLine();
                    System.out.println("Enter a valid Integer value");
                    // System.out.println("Please enter the valid integer");

                }
            
            }
            return seat;
    }

    public String toStringClass(int cinema_class)
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
        
    public void printSeatingPlan(ArrayList<Integer> seatingPlan)
    {
        int count =0;
        final  int TOTALSEAT = 70;
        // for (int i=1; i<=6; i++)
        // {
        //     for (int j=1; j<=10; j++)
        //     {
        //         System.out.print(j);
        //     }
        //     System.out.println("");
        // }
        Collections.sort(seatingPlan);
        // for(Integer z :seatingPlan)
        // {
        //     System.out.println(z);
        //     System.out.println( z.getClass());
        // }

        
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

    // private static ArrayList<Integer> getOccupiedSeats_sess(Strin)

        //get getOccupiedSeats will return ArrayList of occupied seats with the cinema_choice input (aaa,bbb..)
        public ArrayList<Integer> getOccupiedSeats(String cinema_code)
    {
        for(Cinemas c : Cinema)
        {
            if(c.getCinemaCode().toLowerCase().equals(cinema_code))
            {
                return c.getSeatingPlan();
            }
        }
        return null;
    }

    public ArrayList<String> getSessionID(String cinema_code)
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

    // Parse in which cinmea user selected and return which class
    //Regular - 1, Gold - 2, Plat 3
    public int getCinemaClass(String cinema_code)
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


    //This V1 version able to get directly from CSV, CINEPLEX cinema code.
    //It is not hard coded

    public String getCineCode_V1(String cineplex_choice)
    {
        String cinema_choice ="";
        Scanner sc = new Scanner(System.in);
        int error  =0;
        //Prints entire CinemaCode in the Cineplex
        // System.out.println(Cineplex.size());
        for(int i =0; i<Cineplex.size(); i++)
        {
            if(Cineplex.get(i).getCineplexCode().equals(cineplex_choice.toUpperCase()))
            {
                System.out.println(Cineplex.get(i).getCinemasCode());
                System.out.println("Please select which Cinema you would like to view: ");
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


    public double ticketTransact(int movieID, String cinema_code, int cinema_class, String movieDate, String movieTime, ArrayList<Integer> seats, int movieGoerID) throws ParseException{
        double price=0.0;
        double totalPrice = 0.0;

        SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMdd");
        Date date2 = date1.parse(movieDate);
        DateFormat date3 = new SimpleDateFormat("E");
        String dayM = date3.format(date2);

        //check if weekday or weekend
        if (dayM != "Sun" || dayM!="Sat"){
            for (int x=0; x<seats.size();x++){

                int sit = seats.get(x);
                Scanner input = new Scanner(System.in);
                System.out.printf("Please choose category for seat %d:\n", x);
                System.out.println("\t[1] Standard Weekday\n\t[2] Student\n\t[3] Senior Citizen");
                int catChoice = input.nextInt();
                if (catChoice == 1){
                    price = cinema_class*10;
                }
                else if (catChoice==2){
                    price = cinema_class*8;
                }
                else if (catChoice==3){
                    price = cinema_class*7.50;
                }
                
                totalPrice = totalPrice + price;

                //update transactionID
                String TID = cinema_code.toUpperCase()+movieDate+movieTime;
                Transaction newTran = new Transaction(TID, movieGoerID, movieDate, movieTime, cinema_code.toUpperCase(), sit, price, movieID);
                ts.writeToCSV(newTran);
            }
        }
        else{
            for (int y=0; y<seats.size();y++){
                int sit = seats.get(y);
                price = cinema_class*12.50;
                totalPrice = totalPrice + price;

                //update transaction ID
                String TID = cinema_code+movieDate+movieTime;
                Transaction newTran = new Transaction(TID, movieGoerID, movieDate, movieTime, cinema_code, sit, price, movieID);
                ts.writeToCSV(newTran);
            }
        }


        return totalPrice;
    }























    //Deprecated function, hard coded the section of row
    // gets the Cineplex choice and returns the cinema choice in small case format
    //returns aaa,aab,aac/ bba,bbb,bbc, cca,ccb,ccc

    private static String getCineCode(String cineplex_Choice)
    {
        String cinema_choice ="";
        Scanner sc = new Scanner(System.in);
        
        if(cineplex_Choice.toLowerCase().equals("aa")) // selected first 3
        {
            System.out.println("Please select which Cinemas you would like to book:");
            for(int i =0; i<3; i++)
            {
                System.out.print(Cinema.get(i).getCinemaCode() + " ");
            }
            
            // cinema_choice = sc.next();
            System.out.println("Selection: ");
            return sc.next().toLowerCase();
        }
        else if (cineplex_Choice.toLowerCase().equals("bb"))
        {
            System.out.println("Please select which Cinemas you would like to book:");
            for(int i=3; i<6; i++)
            {
                System.out.print(Cinema.get(i).getCinemaCode() + " ");
            }
            System.out.println("Selection: ");
            return sc.next().toLowerCase();
        }
        else if (cineplex_Choice.toLowerCase().equals("cc"))
        {
            System.out.println("Please select which Cinemas you would like to book:");
            for(int i=6; i<9; i++)
            {
                System.out.print(Cinema.get(i).getCinemaCode() + " ");
            }
            System.out.println("Selection: ");
            return sc.next().toLowerCase();
        }
        else{
            System.out.println("Invalid selection made..");
            return null;
        }
        
    }
}
