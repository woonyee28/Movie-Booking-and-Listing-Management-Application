package managers;
import models.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import models.Cineplexes;
import serializers.HolidaySerializer;
import serializers.PriceSerializer;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import serializers.MovieGoerSerializer;
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

import javax.lang.model.element.Element;


// this class defines the movie booking of movies selected
public class MovieBookingManager {

    private String movieTitle;
    private String theatre;
    private String cineplex; // which of the 3 cineplex
    private static int movieid_selected;
    private static String movie_date;
    private static String movie_time;
    private static int movie_time_choice = 0;
    private static ArrayList<String> ss_datetime = new ArrayList<>();

    private static ArrayList<String> nowShowing = new ArrayList<>();
    private static ArrayList<String> preview = new ArrayList<>();
    private static ArrayList<String> endofShowing = new ArrayList<>();


    private int adminOrmember; // 1 == admin, 0 == member, -1 == Guest
    private int id; // -1 == Member

    public MovieBookingManager(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
        
    }



    private int amountofTicket;
    static CinemaSerializer cs = new CinemaSerializer();
    static CineplexSerializer cps = new CineplexSerializer();
    static SessionSerializer ss = new SessionSerializer();
    static MovieSerializer ms = new MovieSerializer();
    static TransactionSerializer ts = new TransactionSerializer();
    private static ArrayList<Cineplexes> Cineplex = cps.readFromCSV();
    private static ArrayList<Cinemas> Cinema = cs.readFromCSV();
    static MovieGoerSerializer mgs = new MovieGoerSerializer();
    static HolidaySerializer hs = new HolidaySerializer();
    static PriceSerializer ps = new PriceSerializer();

	
    /** 
     * run booking
     * @throws ParseException
     */
    public void run() throws ParseException {
		double price=0;
        int selection_choice;
        String book_choice;
        
       
        Scanner sc = new Scanner(System.in);
		System.out.println("\n Welcome to booking page! ");
        System.out.println("1: Show movie listings:");
        System.out.println("2: Book Tickets");
        System.out.println("3: Print Seating Plan");
        selection_choice =  sc.nextInt();
     
            switch(selection_choice)
            {
                case 0:
                    System.out.println("Going back to main page:..");
                    //function to go back main page
                    break;
                case 1: 
                    System.out.println("Now Showing:");
                    showMovieListing();
                    System.out.println("Would you like view more details?(Y/N)");
                    String c = sc.next().toLowerCase();
                    if(c.equals("y") || c.equals("yes")){
                        MovieSerializer mss = new MovieSerializer();
                        for (Movie v: mss.readFromCSV()){
                            
                            System.out.println(v.getMovieID()+": "+v.getTitle());

                        }
                        MovieInformation mi = new MovieInformation();
                        System.out.println("Which Movie ID you would like to know more? ");
                        int cc = sc.nextInt();
                        mi.returnMovInfo(cc);
                    }

                    if (this.adminOrmember==-1){
                        break;
                    }else{
                        System.out.println("Would you like to book a movie?(Y/N)");
                        book_choice = sc.next().toLowerCase();
                        if(book_choice.equals("y") || book_choice.equals("yes"))
                        {
                            int n;
                            
                            if (adminOrmember==1){
                                System.out.println("Choose which moviegoer to book for.\n");
                                for (MovieGoer mg: mgs.readFromCSV()){
                                    System.out.println(mg.toString());
                                }
                                n = sc.nextInt();
                                bookings(n);
                            }else{
                                bookings(this.id);
                            }
                            break;
                        }
                        else
                        {
                            System.out.println("Going back to main page..");
                            //call for main page function
                            break;
                        }
                    }
                    
    
                case 2: 
                    System.out.println("Welcome to booking ticket page: ");
                    if (adminOrmember==-1){
                        System.out.println("Guest is not allowed to book ticket, please return to main app and register yourself :D\n");
                        break;
                    }else{
                        int n = -1;
                        if (adminOrmember==1){
                            System.out.println("Choose which moviegoer to book for.\n");
                            for (MovieGoer mg: mgs.readFromCSV()){
                                System.out.println(mg.toString());
                            }
                            n = sc.nextInt();
                            bookings(n);
                        }else{
                            bookings(this.id);
                        }
                        break;
                    }

                case 3:
                    System.out.println("Please select a cineplex: ");
                    showSeatPlan();
                    break;
                default:
                    System.out.println("None selected.. Terminating back to main page");
                    //function to add to main page
                    break;
            }
            System.out.println("Going Back to main page...");
}

//Show all movie listings in the database
public static void showMovieListing()
    {
        preview.clear();
        endofShowing.clear();
        nowShowing.clear();
        for (Movie m: ms.readFromCSV()) {    
            //Add previews into arrayList       
            if (m.getShowingStatus().equals("Preview"))
            {
                preview.add(m.getTitle());
            }
            //Add End of showing into arrayList
            else if (m.getShowingStatus().equals("End Of Showing"))
            {
                endofShowing.add(m.getTitle());
            }
            ////Add Now showing into arrayList 
            else
            {
                nowShowing.add(m.getTitle());
            }
        }
            //Printing all movies in different categories
            System.out.println("Previews:");
            for(int i=0; i<preview.size(); i++)
            {
                System.out.println(preview.get(i));
            }
            System.out.println();
            System.out.println("Now Showing:");
            for(int i=0; i<nowShowing.size(); i++)
            {
                System.out.println(nowShowing.get(i));
            }
            System.out.println();
            System.out.println("End of showing:");
            for(int i=0; i<endofShowing.size(); i++)
            {
                System.out.println(endofShowing.get(i));
            }
    }

/** 
 * parse in cinema_code to get the sessionID, to print out relevant movies according to the cinema chose
 * Function that requires user to input Movie & Session Timing and will return the seatingPlan
 * @param cinema_code
 * @return ArrayList<Integer>
 */
//To show Preview & Now Showing when user select which cinema
public static ArrayList<Integer> showMovieListing(String cinema_code)
{
    //declare movieID & seatPlan
    ArrayList<Integer> movieID = new ArrayList<>();
    ArrayList<Integer> seatPlan = new ArrayList<>();
    movieID.clear();
    Scanner sc = new Scanner(System.in);
    int which_cine =-1;

    
   //add all the movies in the cinema into movieID
   for(int i =0; i<Cinema.size();  i++)
   {
       //get the cinema that its parsed in
       //to get the index of which cinema to later check the array of sessionID
       
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
   if(which_cine == -1)
   {
       System.out.println("No session availble from the movie you selected");
       return null;
   }

   // show the movies showing for that cinema selected
   //print out in console

   //To clear previous data if the first method was called
   preview.clear();
   nowShowing.clear();
   endofShowing.clear();
   
   for (Movie m: ms.readFromCSV())
   {
       if(movieID.contains(m.getMovieID()))
       {
           if (m.getShowingStatus().equals("Preview"))
           {
               preview.add(m.getMovieID()+1 + ": "+ m.getTitle());
           }
           else if (m.getShowingStatus().equals("Now Showing"))
           {
               nowShowing.add(m.getMovieID()+1 + ": "+ m.getTitle());
           }
    
           // System.out.println(m.getMovieID()+1 + ": "+ m.getTitle());
       }

   }
   


   System.out.println("Previews:");
   for(int i=0; i<preview.size(); i++)
   {
       System.out.println(preview.get(i));
   }
   
   System.out.println();
   System.out.println("Now Showing:");
   for(int i=0; i<nowShowing.size(); i++)
   {
       System.out.println(nowShowing.get(i));
   }
   System.out.println();
   System.out.println("Please select the movie");
    
    movieid_selected = sc.nextInt() - 1;
    // movieid_choice = sc.nextInt() - 1 ;
    System.out.println("Please select the movie time");
    int count =0;
    //Store all the movie session timing to ss_datetime ArrayList
    ss_datetime.clear();
    for(Sessions m : ss.readFromCSV())
    {
        //movieIDs are unique, compare the movieID to the sessionsData
        if(movieid_selected == m.getMovieID())
        {
            count++;
            ss_datetime.add(m.getSessionDate()+m.getSessionTime());
 
        }
    }
    //Sort the timing array
    Collections.sort(ss_datetime);


    // print the session timings (Sorted order)
    for(int i=0; i<ss_datetime.size(); i++)
    {
        System.out.println(i+1 +": Date: " + ss_datetime.get(i).substring(0,8)+ " Time: "+ ss_datetime.get(i).substring(8));
    }
    count =0;

    //user selected which session timing
    movie_time_choice = sc.nextInt();

    //get the movie session timing seating plan
    for(Sessions m:ss.readFromCSV())
    {
        if(m.getSessionDate().equals(ss_datetime.get(movie_time_choice-1).substring(0,8)))
        {
            if(m.getSessionTime().equals(ss_datetime.get(movie_time_choice-1).substring(8)))
            {
                //Stores unique sessionID user selected
                movie_date = m.getSessionDate();
                movie_time = m.getSessionTime();
                seatPlan = m.getSeatingPlan();
            }
            
        }
    }
    //return the seatplan of user selected session
    return seatPlan;
    
}

//Function to ask user which Cineplex & Cinema to show the seatPlan
public static void showSeatPlan()
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

/** 
 * book the ticket function
 * @param id
 * @throws ParseException
 */
public static void bookings(int id) throws ParseException
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
    System.out.println(seatingPlan);

    printSeatingPlan(seatingPlan);
    System.out.println("How many seats would you like?");
    
    int seat = 0;
    while(true)
    {
        noOfSeats = sc.nextInt();
        if (noOfSeats<1)
        {
            System.out.println("Please select at least 1 seat..");
            continue;
        }
        else if(noOfSeats >= 70-seatingPlan.size())
        {
            System.out.println("No of seat exceeded remaining seats left, Please select again");
            continue;
        }
        break;

    }
   
    if (noOfSeats == 1)
    {
        System.out.println("Which seat would you like?");
        seat = bookSeats(seatingPlan);
        //selectedSeat is the user select seat
        selectedSeat.add(seat);
        //Add user selected seat to main seatingPlan
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

    double cost = ticketTransact(movieid_selected, cinema_code, cinema_class, movie_date, movie_time, selectedSeat, id);
    System.out.printf("Total price: $%.2f \n", cost);


  

    // FORMAT OF SESSION ID: DATE+TIME (yyyyMMddHHmm) Year->Month>Day->Hours->Minutes
    
    //adds current time to SessionID

    //update data into Sessions CSV
    Sessions s = new Sessions();
    s.setMovieID(movieid_selected);
    s.setSessionDate(ss_datetime.get(movie_time_choice-1).substring(0,8));
    s.setSessionTime(ss_datetime.get(movie_time_choice-1).substring(8));
    s.setSeatingPlan(seatingPlan);
    ss.updateFromCSV(s);
    System.out.println("Successfully booked the seat you requested");
    

    //at this point can either call the function again or go back main page.

    }


/** 
 * reserve seat
 * @param seatingPlan
 * @return int
 */
public static int bookSeats( ArrayList<Integer> seatingPlan)
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


/** 
 * convert to string
 * @param cinema_class
 * @return String
 */
public static String toStringClass(int cinema_class)
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
 * print seating plan
 * @param seatingPlan
 */
public static void printSeatingPlan(ArrayList<Integer> seatingPlan)
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
 * get occupied seat in the session
 * @param cinema_code
 * @return ArrayList<Integer>
 */
// public static ArrayList<Integer> getOccupiedSeats_sess(Strin)

    //get getOccupiedSeats will return ArrayList of occupied seats with the cinema_choice input (aaa,bbb..)
public static ArrayList<Integer> getOccupiedSeats(String cinema_code)
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


/** 
 * get session id string
 * @param cinema_code
 * @return ArrayList<String>
 */
public static ArrayList<String> getSessionID(String cinema_code)
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
 * Parse in which cinmea user selected and return which class
 * Regular - 1, Gold - 2, Plat 3
 * @param cinema_code
 * @return int
 */
public static int getCinemaClass(String cinema_code)
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



/** 
 * This V1 version able to get directly from CSV, CINEPLEX cinema code.
 * @param cineplex_choice
 * @return String
 */
public static String getCineCode_V1(String cineplex_choice)
{
    String cinema_choice ="";
    Scanner sc = new Scanner(System.in);
    int error  =0;
    //Prints entire CinemaCode in the Cineplex
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



/** 
 * write to ticket transaction csv
 * @param movieID
 * @param cinema_code
 * @param cinema_class
 * @param movieDate
 * @param movieTime
 * @param seats
 * @param movieGoerID
 * @return double
 * @throws ParseException
 */
public static double ticketTransact(int movieID, String cinema_code, int cinema_class, String movieDate, String movieTime, ArrayList<Integer> seats, int movieGoerID) throws ParseException{
    double price=0.0;
    double totalPrice = 0.0;
    int checkDate =-1;
    double studentPrice=0.0;
    double seniorCitizenPrice=0.0;
    double standardPrice=0;
    double specialPrice=0;
    SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMdd");
    Date date2 = date1.parse(movieDate);
    DateFormat date3 = new SimpleDateFormat("E");
    String dayM = date3.format(date2);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  

    for(Holiday h:hs.readFromCSV()){
        if (movieDate.equals(h.getDate())){
            checkDate=1;
            break;
        }
    }
    for (Price p: ps.readFromCSV()){
        if(p.getCat().equals("Student") && p.getMovieType().equals("Blockbuster")){
            studentPrice = p.getPrice();
        }
        if(p.getCat().equals("SeniorCitizen") && p.getMovieType().equals("Blockbuster")){
            seniorCitizenPrice = p.getPrice();
        }
        if(p.getCat().equals("Standard") && p.getMovieType().equals("Blockbuster")){
            standardPrice = p.getPrice();
        }
        if(p.getCat().equals("Special") && p.getMovieType().equals("Blockbuster")){
            specialPrice = p.getPrice();
        }
    }

    System.out.println(dayM);
    //check if weekday or weekend
    if (dayM.equals("Sun") || dayM.equals("Sat")||checkDate==1){
        for (int y=0; y<seats.size();y++){
            int sit = seats.get(y);
            price = cinema_class*specialPrice;
            totalPrice = totalPrice + price;

            //update transaction ID
            LocalDateTime now = LocalDateTime.now();
            String TID = cinema_code.toUpperCase()+dtf.format(now);
            Transaction newTran = new Transaction(TID, movieGoerID, movieDate, movieTime, cinema_code, sit, price, movieID);
            ts.writeToCSV(newTran);
            MovieGoerSerializer mgs = new MovieGoerSerializer();
            for (MovieGoer aa: mgs.readFromCSV()){
                if (aa.getMovieGoersID()==movieGoerID){
                    MovieGoer add = new MovieGoer();
                    add.setMovieGoersID(movieGoerID);
                    add.setName(aa.getName());
                    add.setEmail(aa.getEmail());
                    add.setAge(aa.getAge());
                    add.setpasswordHashed(aa.getPasswordHashed());
                    add.setMobileNumber(aa.getMobileNumber());
                    add.setTID_List(aa.getTID_List()+"; "+TID);
                    mgs.updateFromCSV(add);
                }
            }
        }
    }
    else{
        for (int x=0; x<seats.size();x++){

            int sit = seats.get(x);
            Scanner input = new Scanner(System.in);
            System.out.printf("Please choose category for seat %d:\n", x);
            System.out.println("\t[1] Standard Weekday\n\t[2] Student\n\t[3] Senior Citizen");
            int catChoice = input.nextInt();
            if (catChoice == 1){
                price = cinema_class*standardPrice;
            }
            else if (catChoice==2){
                price = cinema_class*studentPrice;
            }
            else if (catChoice==3){
                price = cinema_class*seniorCitizenPrice;
            }
            
            totalPrice = totalPrice + price;

            //update transactionID
            LocalDateTime now = LocalDateTime.now();
            String TID = cinema_code.toUpperCase()+dtf.format(now);
            Transaction newTran = new Transaction(TID, movieGoerID, movieDate, movieTime, cinema_code.toUpperCase(), sit, price, movieID);
            ts.writeToCSV(newTran);
            MovieGoerSerializer mgs = new MovieGoerSerializer();
            for (MovieGoer aa: mgs.readFromCSV()){
                if (aa.getMovieGoersID()==movieGoerID){
                    MovieGoer add = new MovieGoer();
                    add.setMovieGoersID(movieGoerID);
                    add.setName(aa.getName());
                    add.setEmail(aa.getEmail());
                    add.setAge(aa.getAge());
                    add.setpasswordHashed(aa.getPasswordHashed());
                    add.setMobileNumber(aa.getMobileNumber());
                    add.setTID_List(aa.getTID_List()+"; "+TID);
                    mgs.updateFromCSV(add);
                }
            }
        }
    }


    return totalPrice;
    }
    
}



