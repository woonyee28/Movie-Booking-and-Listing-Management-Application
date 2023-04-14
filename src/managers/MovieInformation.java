package managers;
import java.util.*;

import models.Movie;
import serializers.MovieSerializer;

public class MovieInformation {

    private ArrayList<Movie> movList;
    static MovieSerializer ms = new MovieSerializer();
    
    public MovieInformation(){
        this.movList = ms.readFromCSV();
    }

    /**
     * Display the movie information of a selected movie
     * @param MovieID
     * @return
     */
    public void returnMovInfo(int MovieID){
        String movieName = null;
        String movieType = null;
        String movieSynopsis = null;
        Double movieRating = 0.0;
        String movieDirector = null;
        String movieStatus = null;
        ArrayList<String> movieCast = null;
        for (Movie m: this.movList){
            if (m.getMovieID()==MovieID){
                movieName = m.getTitle();
                movieType = m.getType();
                movieSynopsis = m.getSynopsis();
                movieRating = m.getRating();
                movieDirector = m.getDirector();
                movieStatus = m.getShowingStatus();
                movieCast = m.getCasts();
                break;
            }
        }

        System.out.println("========= Movie Information =========");
        System.out.println("Movie name: " + movieName + "(" + movieRating + " stars)");
        System.out.println("Directed by: " + movieDirector);
        System.out.println("Casts: " + movieCast);
        System.out.println();
        System.out.println("Category: "+ movieType);
        System.out.println("Synopsis: " + movieSynopsis);
        System.out.println("Status: " + movieStatus);
        System.out.println();
        System.out.println("Going Back to Booking Page");
        System.out.println();
        System.out.println();
    }
    
}
