package programs;

import java.util.Scanner;

import managers.MovieManager;
import managers.SessionManager;

public class UINewListingSession {
    /**
     * The adminID of this UINewListingSession.
     */
    private int adminID;
    /**
     * Creates a new UINewListingSession with given adminID. 
     * @param adminID This UINewListingSession's adminID.
     */
    public UINewListingSession(int adminID){
        this.adminID = adminID;
    }
    /**
     * THe menuOptions of this UIListingSession.
     */
    private final String menuOptions[] = {
        "Print All Movies",
        "Print All Sessions",
        "Create New Movie",
        "Create New Session",
        "Modify Movie Detail",
        "Modify Session Detail",
        "Delete Session",
        "Delete Movie",
        "Exit New Listing Session"
    };
    /**
     * Runs UINewListingSession.
     */
    public void run(){
        int choice = -1;

        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================UI Manage Movie / Session======================");
            int i;
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
            choice = Integer.valueOf(sc.next());
			SessionManager sm = new SessionManager(this.adminID);
            MovieManager mm = new MovieManager(adminID);
            switch (choice) {
                case 1:
                    System.out.println("printAllMovies():");
                    mm.printAllMovies();
                    break;
                case 2:
                    System.out.println("printAllSessions:");
                    sm.printAllSessions();
                    break;                
				case 3:
                    System.out.println("createNewMovie():");
                    mm.createNewMovie();
					break;
				case 4:
                    System.out.println("createNewSession():");
					sm.createNewSession();
					break;
                case 5:
                    System.out.println("modifyMovie()");
                    mm.modifyMovie();
                    break;
				case 6:
                    System.out.println("modifySession()");
                    sm.modifySession();
                    break;
                case 7:
                    System.out.println("deleteSession()");
                    sm.deleteSession();
                    break;
                case 8:
                    System.out.println("deleteMovie()");
                    mm.deleteMovie();
                    break;
                case 9:
                    System.out.println("Program exiting...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 9);
        ;
    }
}
