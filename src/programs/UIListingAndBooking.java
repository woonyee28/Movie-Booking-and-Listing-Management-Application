package programs;

import java.text.ParseException;
import java.util.Scanner;

import managers.MovieBookingManager;
import models.MovieGoer;
import serializers.MovieGoerSerializer;

public class UIListingAndBooking {
    private int adminOrmember; // 1 == admin, 0 == member, -1 == Guest
    private int id; // -1 == Member

    public UIListingAndBooking(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }

    public void run() throws ParseException{
        int selection_choice;
        String book_choice;
        MovieGoerSerializer mgs = new MovieGoerSerializer();
        Scanner sc = new Scanner(System.in);

        MovieBookingManager mbm = new MovieBookingManager(this.id, this.adminOrmember);
        do{
            System.out.println("Welcome to booking page! ");
            System.out.println("1: Show movie listings ");
            System.out.println("2: Book Tickets ");
            System.out.println("3: Print Seating Plan ");
            System.out.println("4: Exit to Previous Level ");
            selection_choice =  sc.nextInt();
            switch(selection_choice)
            { 
                case 1: 
                    // System.out.println("Now Showing:");
                    mbm.showMovieListing();
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
                                mbm.bookings(n);
                            }else{
                                mbm.bookings(this.id);
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
                            mbm.bookings(n);
                        }else{
                            mbm.bookings(this.id);
                        }
                        break;
                    }

                case 3:
                    System.out.println("Please select a cineplex: ");
                    mbm.showSeatPlan();
                    break;
                case 4:
                    System.out.println("Exiting to the previous level...");
                    break;
                default:
                    System.out.println("Please input a valid option.");
                    //function to add to main page
                    break;
            }
           
        }while(selection_choice!=4);
    }
}
