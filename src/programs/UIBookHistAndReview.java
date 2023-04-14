package programs;

import java.util.Scanner;

import managers.BookingHistManager;


public class UIBookHistAndReview {
    /**
     * The movieGoerID of this UIBookHistAndReview.
     */
    private int movieGoerID;
    /**
     * Creates a new UIBookHistAndReview with the given movieGoerID.
     * @param movieGoerID This UIBookHistAndReview's movieGoerID.
     */
    public UIBookHistAndReview(int movieGoerID){
        this.movieGoerID = movieGoerID;
    }
    /**
     * THe menuOptions of this UIBookHistAndReview.
     */
    private final String menuOptions[] = {
        "Show Booking History",
        "Input Review And Rating",
        "Exit to previous level"
    };
    /**
     * Runs UIBookHistAndReview.
     */
    public void run(){
        int choice = -1; 
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================UI Sales Reporting======================");
            int i;
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
            choice = Integer.valueOf(sc.next());
            BookingHistManager bh = new BookingHistManager(this.movieGoerID);
            switch (choice) {
				case 1:
                    System.out.println("Booking History: ");
                    bh.showBookingHistory(this.movieGoerID);
					break;
				case 2:
                    System.out.println("Input Reviews and Ratings: ");
					bh.inputReviewAndRating(this.movieGoerID);
					break;
				case 3:
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 3);
        ;
    }
}
