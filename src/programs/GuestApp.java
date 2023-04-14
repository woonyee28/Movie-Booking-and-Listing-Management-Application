package programs;

import java.text.ParseException;
import java.util.Scanner;


public class GuestApp {
	/**
	 * Creates a new GuestApp.
	 */

    public GuestApp(){};
	/**
	 * Runs the Guest App
	 * @throws ParseException
	 */
    public void run() throws ParseException{
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================GuestApp======================\n");
			System.out.println("What would you like to do?");
			System.out.println("\t[1] View Movies and Book Tickets\n\t[2] View Sales\n\t[3] Go back to previous menu.");
			choice = Integer.valueOf(sc.next());
			System.out.println();
			
            switch (choice) {
				case 1:
					// Show movie listing + Book ticket, UIListingAndBooking
					UIListingAndBooking a = new UIListingAndBooking(-1, -1);
					a.run();
					// Zheng Kai
					break;
				case 2:
					// Woon Yee
					UISalesReporting s = new UISalesReporting(-1,-1);
					s.run();
					break;
				case 3:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 3);
		;
    }
}
