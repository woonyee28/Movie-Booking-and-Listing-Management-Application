package managers;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import models.Movie;
import models.Review;
import models.Transaction;
import serializers.MovieSerializer;
import serializers.ReviewSerializer;
import serializers.TransactionSerializer;

public class SalesManager {
    /**
     * The adminOrmember of this SalesManager.
     */
    private int adminOrmember; 
    /**
     * The id of this SalesManager.
     */
    private int id;
    /**
     * The TransactionSerializer of this SalesManager.
     */
    static TransactionSerializer ts = new TransactionSerializer();
    /**
     *  The MovieSerializer of this SalesManager.
     */
    static MovieSerializer ms = new MovieSerializer();
    /*
     *  The ReviewSerializer of this SalesManager.
     */
    static ReviewSerializer rs = new ReviewSerializer();
    /**
     * Creates a SalesManager with the given id and adminOrmember.
     * @param id This SalesManager's id.
     * @param adminOrmember This SaleManager's adminOrmember.
     */
    public SalesManager(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }
    /*
     * Prints top 5 movies by rating.
     */
    public void sortByRating(){
        Map<Double,String> d = new TreeMap<Double,String>(Collections.reverseOrder());
        for (Movie m: ms.readFromCSV()) {           
            d.put(m.getRating(),m.getTitle());
        }
        Set<Entry<Double, String>> set = d.entrySet();
        Iterator<Entry<Double, String>> i = set.iterator();
        int c=0;
        
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getValue() + ": ");
            System.out.println(me.getKey());
            c+=1;
            if (c==5){
                break;
            }
        }
        
    }
    /**
     * Prints top 5 movies by sales.
     */
    public void sortBySales(){
        final Map<Double, Integer> d = new TreeMap<Double,Integer>(Collections.reverseOrder());
        for (Transaction m: ts.readFromCSV()) {   
            int id = m.getMovieID();  
            Set<Double> prices = d.keySet();      
            if (d.containsValue(id)){
                Double ans=0.0;
                for( Double p : prices ){
                    if( d.get(p) == id){
                        ans = p;
                    }
                }
                Double price = ans+m.getPrice();
                d.remove(ans);
                d.put(price,id);
            }else{
                d.put(m.getPrice(),id);
            }
        }
        Set set = d.entrySet();
        Iterator i = set.iterator();
        int c=0;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            for (Movie m: ms.readFromCSV()) {           
                if (me.getValue().equals(m.getMovieID())){
                    System.out.print(m.getTitle() + ": ");
                    break;
                }
            }
            System.out.println(me.getKey());
            c+=1;
            if (c==5){
                break;
            }
        }
    }
    /**
     * Shows review of a movie.
     * Movie name will be used to get the review.
     */
    public void showReview(){
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
        int check = 0;
        for (Review r: rs.readFromCSV()){
            if (movieid == r.getMovieID()){
                System.out.println(r.getReviews());
                check=1;
            }
        }
        if (check==0){
            System.out.println("The movie has no review yet.");
        }
        ;
    }
}
