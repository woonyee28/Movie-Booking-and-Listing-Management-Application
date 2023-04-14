package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Cinemas;
import models.GoldCinema;
import models.PlatinumCinema;
import models.RegularCinema;

public class CinemaSerializer implements InterfaceSerializer<Cinemas>{
    private static final String CSV_SEPARATOR = ",";

    
    /** 
     * write cinema class to csv
     * @param cinema
     */
    @Override
    public void writeToCSV(Cinemas cinema)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cinemasData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(cinema.getCinemaCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(cinema.getCinemaClass());
            oneLine.append(CSV_SEPARATOR);
            String cast = cinema.getSeatingPlan().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);
            oneLine.append(CSV_SEPARATOR);
            String reviewsID = cinema.getSessionsID().toString();
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
     * read cinema from csv
     * @return ArrayList<Cinemas>
     */
    @Override
    public ArrayList<Cinemas> readFromCSV()
    {
        try {
            ArrayList<Cinemas> cinemaList = new ArrayList<Cinemas>();
            File file = new File("database/cinemasData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String cinemaCode = tempArr[0];
               String cinemaClass = tempArr[1];
               ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
               String[] tempReviewID;
               tempReviewID = tempArr[2].split("; ");
               for (String s: tempReviewID) {           
                seatingPlan.add(Integer.valueOf(s));
               }
               ArrayList<String> sessionsID = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[3].split("; ");
               for (String s: tempCast) {           
                sessionsID.add(s);
               }
               Cinemas m = new RegularCinema(cinemaCode,seatingPlan,sessionsID,cinemaClass);
               switch (cinemaClass){
                    case "Regular":
                        m = new RegularCinema(cinemaCode,seatingPlan,sessionsID,cinemaClass);
                        break;
                    case "Gold":
                        m = new GoldCinema(cinemaCode,seatingPlan,sessionsID,cinemaClass);
                        break;
                    case "Platinum":
                        m = new PlatinumCinema(cinemaCode,seatingPlan,sessionsID,cinemaClass);
                        break;
                    default:
                        System.out.println("CinemaSerializer Line 83 Error!");
                        break;
               }
               cinemaList.add(m);
               
            }
            ;
            return cinemaList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * overwrite the csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Cinemas> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cinemasData.csv",false)));
			for(Cinemas cinema:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(cinema.getCinemaCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cinema.getCinemaClass());
                oneLine.append(CSV_SEPARATOR);
                String cast = cinema.getSeatingPlan().toString();
                cast = cast.replace(',', ';').substring(1, cast.length() - 1);
                oneLine.append(cast);
                oneLine.append(CSV_SEPARATOR);
                String reviewsID = cinema.getSessionsID().toString();
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
     * update the cinema in csv
     * @param m
     */
    @Override
    public void updateFromCSV(Cinemas m) {
	    CinemaSerializer cs = new CinemaSerializer();
        ArrayList<Cinemas> aList = cs.readFromCSV();
	    int flag =0;
	    for (Cinemas a:aList) {
		    if (a.getCinemaCode().equals(m.getCinemaCode())) {
		    	a.setCinemaClass(m.getCinemaClass());
                a.setSeatingPlan(m.getSeatingPlan());
                a.setSessionsID(m.getSessionsID());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        cs.overwriteCSV(aList);
	        System.out.println("Cinema record, cinemaCode = " +m.getCinemaCode()+" successfully updated!");
	    } else System.out.println("Cinema record, cinemaCode = " +m.getCinemaCode()+" update unsuccessful!");
    }
    
    /** 
     * delete the cinema in csv
     * @param m
     */
    @Override
    public void deleteFromCSV(Cinemas m) {
        CinemaSerializer cs = new CinemaSerializer();
        ArrayList<Cinemas> aList = cs.readFromCSV();
        int index=0,flag=0;
        for (Cinemas a:aList) {
            if (a.getCinemaCode().equals(m.getCinemaCode())) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            cs.overwriteCSV(aList);
            System.out.println("Cinema record, cinemaCode = " +m.getCinemaCode()+" successfully deleted!");
	    } else System.out.println("Cinema record, cinemaCode = " +m.getCinemaCode()+" delete unsuccessful!");
    }
}
