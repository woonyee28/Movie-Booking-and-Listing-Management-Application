package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Review;

public class ReviewSerializer {
    private static final String CSV_SEPARATOR = ",";

    public static void writeToReviewCSV(Review review)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/reviewsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(review.getReviewsID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(review.getMovieGoersID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(review.getRating());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(review.getReviews());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(review.getMovieID());

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }

    public static ArrayList<Review> readFromReviewCSV()
    {
        try {
            ArrayList<Review> reviewList = new ArrayList<Review>();
            File file = new File("database/reviewsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int reviewsID = Integer.valueOf(tempArr[0]);
               int movieGoersID = Integer.valueOf(tempArr[1]);
               double rating = Double.valueOf(tempArr[2]);
               String reviews = tempArr[3];
               int movieID = Integer.valueOf(tempArr[4]);
               Review m = new Review(reviewsID,movieGoersID,rating,reviews,movieID);
               reviewList.add(m);
            }
            br.close();
            return reviewList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
