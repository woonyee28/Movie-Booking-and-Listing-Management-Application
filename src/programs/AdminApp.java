package programs;

import java.text.ParseException;
import java.util.Scanner;

import managers.AmendBookingManager;


public class AdminApp {

	private int adminID;
	
	private final String menuOptions[] = {
        "Amend Booking",
        "Show Movie Listing and Book Ticket",
        "Show Sales Report",
        "Create New Movie/Session Listing",
		"Configure System Settings",
		"Create New Admin",
		"Manage Admins",
		"Exit Admin App"
	};
	/**
	 * Creates an AdminApp with the given adminID.
	 * @param adminID This AdminApp's adminID.
	 */
	public AdminApp(int adminID){
		this.adminID=adminID;
	}

	
	/** 
	 * get admin id
	 * @return int
	 */
	public int getAdminID(){
		return this.adminID;
	}

	/**
	 * Runs the AdminApp.
	 * @throws ParseException
	 */
    public void run() throws ParseException{
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================AdminApp======================\n");
			for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
				case 1:
                    // Amend Booking, UIAmendBooking
					UIAmendBooking m = new UIAmendBooking(adminID,1);
					m.run();
					break;
				case 2:
					// Show movie listing + Book ticket, UIListingAndBooking
					UIListingAndBooking a = new UIListingAndBooking(adminID, 1);
					a.run();
					// Zheng Kai
					break;
				case 3:
					UISalesReporting s = new UISalesReporting(this.adminID,1);
					s.run();
					break;
                case 4:
					// Create new movie listing / new session / change movie details, UINewListingSession
					// Woon Yee
					UINewListingSession l = new UINewListingSession(this.adminID);
					l.run();
					break;
                case 5:
					// Config system setting, UIConfigSystem
					UIConfigSystem configSystem = new UIConfigSystem(this.adminID);
					configSystem.run();
					break;
                case 6:
					// sign up admin, UIAdminSignUp
					UIAdminSignUp adminSignUp = new UIAdminSignUp(this.adminID);
					adminSignUp.run();
					break;
                case 7:
					// manage admin, UIManageAdmin
					UIManageAdmin manageAdmin = new UIManageAdmin(this.adminID);
					manageAdmin.run();
					break;
				case 8:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 8);
		;
    }
	
}
