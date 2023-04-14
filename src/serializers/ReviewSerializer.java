package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Review;

public class ReviewSerializer implements InterfaceSerializer<Review>{
    private static final String CSV_SEPARATOR = ",";

    
    /** 
     * write review to csv
     * @param review
     */
    @Override
    public void writeToCSV(Review review)
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
            ;
        }
        catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
    }
    
    /** 
     * read review from csv
     * @return ArrayList<Review>
     */
    @Override
    public ArrayList<Review> readFromCSV()
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
            ;
            return reviewList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * overwrite review csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Review> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/reviewsData.csv",false)));
			for(Review review:aList) {
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
			}
			bw.flush();
			;
		}
		catch (UnsupportedEncodingException e) {System.out.printf("'%s' %n", "Unsupported Encoding");}
        catch (FileNotFoundException e){System.out.printf("'%s' %n", "File Not Found"); }
        catch (IOException e){e.printStackTrace();}
	
    }
    
    /** 
     * update review in csv
     * @param r
     */
    @Override
    public void updateFromCSV(Review r) {
        ReviewSerializer rs = new ReviewSerializer();
	    ArrayList<Review> aList = rs.readFromCSV();
	    int flag =0;
	    for (Review a:aList) {
		    if (a.getReviewsID()==r.getReviewsID()) {
		    	a.setMovieGoersID(r.getMovieID());
                a.setRating(r.getRating());
                a.setReviews(r.getReviews());
                a.setMovieID(r.getMovieID());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        rs.overwriteCSV(aList);
	        System.out.println("Review record, id = "+ r.getReviewsID()+" successfully updated!");
        }
        else System.out.println("Review record, id = "+ r.getReviewsID()+" update unsuccessful!");
    }
    
    /** 
     * delete review in csv
     * @param r
     */
    @Override
    public void deleteFromCSV(Review r) {
        ReviewSerializer rs = new ReviewSerializer();
        ArrayList<Review> aList = rs.readFromCSV();
        int index=0,flag=0;
        for (Review a:aList) {
            if (a.getReviewsID()==r.getReviewsID()) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            rs.overwriteCSV(aList);
            System.out.println("Review record, id = "+ r.getReviewsID()+" successfully deleted!");
        }
        else System.out.println("Review record, id = "+ r.getReviewsID()+" deletion unsuccessful!");

	
    }
}
