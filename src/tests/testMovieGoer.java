package tests;

import models.MovieGoer;
import serializers.MovieGoerSerializer;

public class testMovieGoer {
    public static void main(String[] args) {
        MovieGoer n = new MovieGoer(2,"TestSnorlex","snorlex@gmail.com.org",21,"-2056038121",86521122,"BBB202210232031");
        MovieGoerSerializer.writeToMovieGoerCSV(n);
        for (MovieGoer m: MovieGoerSerializer.readFromMovieGoerCSV()) {           
            m.toString();
            System.out.println(m); 
        }
    }
}
 