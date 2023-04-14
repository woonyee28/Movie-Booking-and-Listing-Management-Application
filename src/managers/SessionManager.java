package managers;

import models.*;
import serializers.*;
import java.util.*;

public class SessionManager {
    /**
     * The SessionSerilizer of this SessionManager.
     */
    static SessionSerializer ss = new SessionSerializer();
    /**
     * The adminID of this SessionManager.
     */
    private int adminID;
    /**
     * Creates a new SessionManager with given adminID.
     * @param adminID This SessionManager's adminID.
     */
    public SessionManager(int adminID){
        this.adminID = adminID;
    }
    /**
     * Prints all Sessions.
     */
    public void printAllSessions(){
        for (Sessions m: ss.readFromCSV()) {           
            System.out.println(m.toString()); 
        }
    }
    /**
     * Creates new Session and writes it to database.
     * seatingPlan will be initalized to -1(empty).
     */
    public void createNewSession(){
        MovieManager mm = new MovieManager(this.adminID);
        mm.printAllMovies();
        Scanner ii = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Movie Id: ");
        int movieID = ii.nextInt();
        System.out.println("Enter Session Date YYYYMMDD: ");
        String sessionDate = sc.nextLine();
        System.out.println("Enter Session Time HHMM: ");
        String sessionTime = sc.nextLine();
        ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
        seatingPlan.add(-1);
        Sessions sess = new Sessions(movieID, sessionDate, sessionTime, seatingPlan);
        ss.writeToCSV(sess);
        ;
        ;
    }
    /**
     * Modifies an existing Session in the database.
     * Session's movieID,Date and Time will be used to identify the Session to be modified.
     * movieID,Date and Time can be changed.
     */
    public void modifySession(){
        Scanner ii = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        int movieID = -1;
        String sessionDate="";
        String sessionTime = "";
        ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
        seatingPlan.add(-1);
        System.out.println("Enter the Session's Movie Id: ");
        movieID = ii.nextInt();
        System.out.println("Enter Session Date YYYYMMDD: ");
        sessionDate = sc.nextLine();
        System.out.println("Enter Session Time HHMM: ");
        sessionTime = sc.nextLine();
        String changeOptions[] = {
            "Movie ID",
            "Session Date",
            "Session Time",
            "Exit ChangeOptions"
        };
        for (Sessions s: ss.readFromCSV()){
            if (s.getMovieID()==movieID && s.getSessionDate().equals(sessionDate) && s.getSessionTime().equals(sessionTime)){
                seatingPlan = s.getSeatingPlan();
            }
        }
        int newMovieID=movieID;
        String newSessionDate=sessionDate;
        String newSessionTime=sessionTime;
        int choice = -1;
        do{
            System.out.println("What would you like to change? ");
            
            int i;
            for (i = 1; i <= changeOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, changeOptions[i-1]);
            }
            choice = ii.nextInt();
			
            
            switch (choice) {
                case 1:
                    System.out.println("Enter New movieID: ");
                    newMovieID = ii.nextInt();
                    break;
                case 2:
                    System.out.println("Enter New SessionDate: ");
                    newSessionDate = sc.nextLine();
                    break;                
				case 3:
                    System.out.println("Enter New SessionTime: ");
                    newSessionTime = sc.nextLine();
					break;
                case 4:
                    System.out.println("Exit ChangeOptions...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
        Sessions aa = new Sessions();
        aa.setSessionTime(sessionTime);
        aa.setSessionDate(sessionDate);
        aa.setMovieID(movieID);
        ss.deleteFromCSV(aa);
        Sessions sa = new Sessions(newMovieID,newSessionDate,newSessionTime,seatingPlan);
        ss.writeToCSV(sa);
        ;
        ;
    }
    /**
     * Deletes a Session from Database.
     * Session's movieID,Date and Time will be used to identify the Session to be deleted.
     * Session's seatingPlan must be empty to be deleted.
     */
    public void deleteSession(){
        Scanner ii = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        int flag=-1;
        int found=-1;
        System.out.println("--------- Delete session ---------");
        System.out.println("Enter Movie Id: ");
        int movieID = ii.nextInt();
        System.out.println("Enter Session Date YYYYMMDD: ");
        String sessionDate = sc.nextLine();
        System.out.println("Enter Session Time HHMM: ");
        String sessionTime = sc.nextLine();
        ArrayList<Integer> empty = new ArrayList<Integer>();
        empty.add(-1);
        for (Sessions s:ss.readFromCSV()){
            if(s.getMovieID()==movieID && s.getSessionDate().equals(sessionDate) && s.getSessionTime().equals(sessionTime)){
                found =1;
                if(s.getSeatingPlan().equals(empty)){
                    flag =1;
                    break;
                }
            }
        }
        if (found==1){
            if(flag==1){
                Sessions target = new Sessions(movieID,sessionDate,sessionTime,empty);
                ss.deleteFromCSV(target);
            }else System.out.println("Session has bookings!");
        } else System.out.println("Session not found!");
    }
}
