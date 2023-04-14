package managers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Movie;
import models.MovieGoer;
import models.Review;
import models.Transaction;
import serializers.MovieGoerSerializer;
import serializers.MovieSerializer;
import serializers.ReviewSerializer;
import serializers.TransactionSerializer;


public class BookingHistManager {
    private int movieGoerID;

    static MovieGoerSerializer mgs = new MovieGoerSerializer();
    static TransactionSerializer ts = new TransactionSerializer();
    static MovieSerializer ms = new MovieSerializer();
    static ReviewSerializer rr = new ReviewSerializer();

    public BookingHistManager(int movieGoerID){
        this.movieGoerID = movieGoerID;
    }

    
    /** 
     * show booking history
     * @param movieGoerID
     */
    public void showBookingHistory(int movieGoerID){
        String tid = "";
        for (MovieGoer mg: mgs.readFromCSV()) {
            if (mg.getMovieGoersID()==movieGoerID){
                tid = mg.getTID_List();
            }
        }
        String[] tempArr;
        tempArr = tid.split("; ");
        for (String s: tempArr){
            for (Transaction t: ts.readFromCSV()){
                if (t.getTID().equals(s)){
                    System.out.println("\n"); 
                    System.out.println("Your Booking TID: "); 
                    System.out.println(t.getTID());   
                    System.out.println("Movie Name: ");   
                    int movieid = t.getMovieID();
                    for (Movie m: ms.readFromCSV()) {           
                        if (m.getMovieID() == movieid){
                            System.out.println(m.getTitle());  
                        }
                    }
                    System.out.println("Booking Date: ");
                    System.out.println(t.getBookingDate());   
                    System.out.println("Booking Time: ");
                    System.out.println(t.getBookingTime());   
                    System.out.println("Cinema Code: ");
                    System.out.println(t.getCinemaCode());   
                    System.out.println("Seat Number: ");
                    System.out.println(t.getSeatingNum());   
                    System.out.println("Ticket Price: ");
                    System.out.println(t.getPrice()); 
                    
                }
            }
        }
    }


    
    /** 
     * input review and rating
     * @param movieGoerID
     */
    public void inputReviewAndRating(int movieGoerID){
        int maxx = 0;
        for (Review r: rr.readFromCSV()) {
            maxx = Math.max(r.getReviewsID(),maxx);
        }
        System.out.println("Input the movie name: ");
        Scanner sc = new Scanner(System.in);
        int movieid = -1;
        String movieName = sc.nextLine();
        for (Movie m: ms.readFromCSV()) {           
            if (movieName.equals(m.getTitle())){
                movieid = m.getMovieID();
                break;
            }
        }
        if (movieid==-1){
            System.out.println("Movie doesn't exist!");   
            ;
            return;
        }
        System.out.println("Input the review without comma: ");
        String review = sc.nextLine();
        System.out.println("Input the rating in 1 decimal: ");
        Double rating = sc.nextDouble();
        Review rrr= new Review(maxx+1, movieGoerID, rating, review, movieid);
        rr.writeToCSV(rrr);
        int count=0;
        Double score=0.0;
        for (Review r: rr.readFromCSV()){
            if (r.getMovieID()==movieid){
                count+=1;
                score+=r.getRating();
            }
        }
        score/=count;
        for (Movie m: ms.readFromCSV()) {    
            if (movieid == m.getMovieID()){
                ArrayList<Integer> reviewsID;
                reviewsID = m.getReviewsID();
                if (reviewsID.contains(-1)){
                    reviewsID.remove(-1);
                }
                reviewsID.add(maxx+1);
                m.setReviewID(reviewsID);
                ms.updateFromCSV(m);
                break;
            }
        }
        System.out.println("Update successful!");   
        ;     
    }
    
}
