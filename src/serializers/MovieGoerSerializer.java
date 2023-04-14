package serializers;

import java.io.*;
import java.util.ArrayList;

import models.MovieGoer;

public class MovieGoerSerializer implements InterfaceSerializer<MovieGoer>{
    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * write moviegoer into csv
     * @param movieGoer
     */
    @Override
    public void writeToCSV(MovieGoer movieGoer)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/movieGoersData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(movieGoer.getMovieGoersID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getEmail());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getAge());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getPasswordHashed());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getMobileNumber());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(movieGoer.getTID_List());

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
     * read moviegoer from csv
     * @return ArrayList<MovieGoer>
     */
    @Override
    public ArrayList<MovieGoer> readFromCSV()
    {
        try {
            ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();
            File file = new File("database/movieGoersData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieGoerID = Integer.valueOf(tempArr[0].replaceAll("\\uFEFF", ""));
               String name = tempArr[1];
               String email = tempArr[2];
               int age = Integer.valueOf(tempArr[3]);
               String passwordHashed = tempArr[4];
               int mobileNumber = Integer.valueOf(tempArr[5]);
               String TID_List = tempArr[6];
               
               MovieGoer m = new MovieGoer(movieGoerID,name,email,age,passwordHashed,mobileNumber,TID_List);
               movieGoerList.add(m);
               
            }
            ;
            return movieGoerList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * overwrite moviegoer csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<MovieGoer> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/movieGoersData.csv",false)));
			for(MovieGoer movieGoer:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(movieGoer.getMovieGoersID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getEmail());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getAge());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getPasswordHashed());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getMobileNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(movieGoer.getTID_List());
    
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
     * upadte moviegoer in the csv
     * @param m
     */
    @Override
    public void updateFromCSV(MovieGoer m) {
	    MovieGoerSerializer mg = new MovieGoerSerializer();
        ArrayList<MovieGoer> aList = mg.readFromCSV();
	    int flag =0;
	    for (MovieGoer a:aList) {
		    if (a.getName().equals(m.getName()) && a.getEmail().equals(m.getEmail())) {
		    	a.setMovieGoersID(m.getMovieGoersID());
                a.setAge(m.getAge());
                a.setpasswordHashed(m.getPasswordHashed());
                a.setMobileNumber(m.getMobileNumber());
                a.setTID_List(m.getTID_List());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        mg.overwriteCSV(aList);
	        System.out.println("MovieGoer record, name = " + m.getName() +" successfully updated!");
	    } else System.out.println("MovieGoer record, name " + m.getName()+" update unsuccessful!");
    }
    
    /** 
     * delete moviegoer from the csv
     * @param m
     */
    @Override
    public void deleteFromCSV(MovieGoer m) {
        MovieGoerSerializer mg = new MovieGoerSerializer();
        ArrayList<MovieGoer> aList = mg.readFromCSV();
        int index=0,flag=0;
        for (MovieGoer a:aList) {
            if (a.getName().equals(m.getName()) && a.getEmail().equals(m.getEmail())) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            mg.overwriteCSV(aList);
            System.out.println("MovieGoer record, name = " + m.getName() +" successfully deleted!");
	    } else System.out.println("MovieGoer record, name " + m.getName()+" delete unsuccessful!");
    }
}
 