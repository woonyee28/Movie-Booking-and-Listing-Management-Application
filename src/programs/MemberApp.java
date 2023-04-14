package programs;

import java.text.ParseException;
import java.util.Scanner;

import managers.AmendBookingManager;
import managers.PriceManager;


public class MemberApp{
	/**
	 * The movieGoerID of this MemberApp.
	 */
	private int movieGoerID;
	/**
	 * Creates a new MemberApp with given movieGoerID.
	 * @param movieGoerID
	 */
	public MemberApp(int movieGoerID){
		this.movieGoerID = movieGoerID;
	}
	/**
	 * @return This MemberApp's movieGoerID.
	 */
	public int getMemberID(){
		return this.movieGoerID;
	}
	/**
	 * Runs the MemberApp.
	 * @throws ParseException
	 */
    public void run() throws ParseException{
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================MemberApp======================\n");
			System.out.println("What would you like to do?");
			System.out.println("\t[1] View your booking history and review ratings\n\t[2] Amend Bookings\n\t[3] View Movies and Book Tickets\n\t[4] View Sales\n\t[5] View Pricing\n\t[6] Go back to previous menu.");
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
                case 1:
                    // Booking History + review rating, UIBookHistAndReview
					// Woon Yee
					UIBookHistAndReview h = new UIBookHistAndReview(movieGoerID);
					h.run();
                    break;
				case 2:
                    // Amend Booking, UIAmendBooking
					UIAmendBooking m = new UIAmendBooking(movieGoerID,0);
					m.run();
					break;
				case 3:
					// Show movie listing + Book ticket, UIListingAndBooking
					UIListingAndBooking n = new UIListingAndBooking(movieGoerID, 0);
					n.run();
					// Zheng Kai
					break;
				case 4:
					// Woon Yee
					UISalesReporting s = new UISalesReporting(this.movieGoerID,0);
					s.run();
					break;
				case 5:
					PriceManager p = new PriceManager();
					p.viewPricing();
				case 6:
					System.out.println("Exiting to the previous level...");
					break;
					
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 6);
		;
    }
}
