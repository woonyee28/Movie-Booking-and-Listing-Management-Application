package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Movie;

public class MovieSerializer implements InterfaceSerializer<Movie>{

    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * write movie into csv
     * @param movie
     */
    @Override
    public void writeToCSV(Movie movie)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/moviesData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(movie.getMovieID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getTitle());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getSynopsis());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getRating());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getShowingStatus());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movie.getDirector());
            oneLine.append(CSV_SEPARATOR);
            String cast = movie.getCasts().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);
            oneLine.append(CSV_SEPARATOR);
            String reviewsID = movie.getReviewsID().toString();
            reviewsID = reviewsID.replace(',', ';').substring(1, reviewsID.length() - 1);
            oneLine.append(reviewsID);

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            ;
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }
    
    /** 
     * read movie from csv
     * @return ArrayList<Movie>
     */
    @Override
    public ArrayList<Movie> readFromCSV()
    {
        try {
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            File file = new File("database/moviesData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieID = Integer.valueOf(tempArr[0]);
               String title = tempArr[1];
               String type = tempArr[2];
               String synopsis = tempArr[3];
               Double rating = Double.valueOf(tempArr[4]);
               String showingStatus = tempArr[5];
               String director = tempArr[6];
               ArrayList<String> cast = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[7].split("; ");
               for (String s: tempCast) {           
                cast.add(s);
               }
               ArrayList<Integer> reviewsID = new ArrayList<Integer>();
               String[] tempReviewID;
               tempReviewID = tempArr[8].split("; ");
               for (String s: tempReviewID) {           
                reviewsID.add(Integer.valueOf(s));
               }
               Movie m = new Movie(movieID,title,type,synopsis,rating,showingStatus,director,cast,reviewsID);
               movieList.add(m);
               
            }
            ;
            return movieList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * overwrite movie csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Movie> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/moviesData.csv",false)));
			for(Movie movie:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(movie.getMovieID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movie.getTitle());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movie.getType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movie.getSynopsis());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movie.getRating());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movie.getShowingStatus());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movie.getDirector());
                oneLine.append(CSV_SEPARATOR);
                String cast = movie.getCasts().toString();
                cast = cast.replace(',', ';').substring(1, cast.length() - 1);
                oneLine.append(cast);
                oneLine.append(CSV_SEPARATOR);
                String reviewsID = movie.getReviewsID().toString();
                reviewsID = reviewsID.replace(',', ';').substring(1, reviewsID.length() - 1);
                oneLine.append(reviewsID);
    
                bw.write(oneLine.toString());
                bw.newLine();
			}
			bw.flush();
			;
		}
		catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
	
    }
    
    /** 
     * update movie in csv
     * @param m
     */
    @Override
    public void updateFromCSV(Movie m) {
	    MovieSerializer ms = new MovieSerializer();
        ArrayList<Movie> aList = ms.readFromCSV();
	    int flag =0;
	    for (Movie a:aList) {
		    if (a.getMovieID()==m.getMovieID()) {
		    	a.setTitle(m.getTitle());
                a.setType(m.getType());
                a.setSynopsis(m.getSynopsis());
                a.setRating(m.getRating());
                a.setShowingStatus(m.getShowingStatus());
                a.setDirector(m.getDirector());
                a.setCast(m.getCasts());
                a.setReviewID(m.getReviewsID());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        ms.overwriteCSV(aList);
	        System.out.println("Movie record, title = " +m.getTitle()+" successfully updated!");
	    } else System.out.println("Movie record, title = " +m.getTitle()+" update unsuccessful!");
    }
    
    /** 
     * delete movie in csv
     * @param m
     */
    @Override
    public void deleteFromCSV(Movie m) {
	    MovieSerializer ms = new MovieSerializer();
        ArrayList<Movie> aList = ms.readFromCSV();
        int index=0,flag=0;
        for (Movie a:aList) {
            if (a.getTitle().equals(m.getTitle())) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            ms.overwriteCSV(aList);
            System.out.println("Movie record, title = " +m.getTitle()+" successfully deleted!");
	    } else System.out.println("Movie record, title = " + m.getTitle() +" delete unsuccessful!");
    }
}