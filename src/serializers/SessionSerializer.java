package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Sessions;

public class SessionSerializer implements InterfaceSerializer<Sessions>{
    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * write session to csv
     * @param session
     */
    @Override
    public void writeToCSV(Sessions session)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/sessionsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(session.getMovieID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(session.getSessionDate());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(session.getSessionTime());
            oneLine.append(CSV_SEPARATOR);
            String cast = session.getSeatingPlan().toString();
            cast = cast.replace(',', ';').substring(1, cast.length() - 1);
            oneLine.append(cast);

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
     * read sessions from csv
     * @return ArrayList<Sessions>
     */
    @Override
    public ArrayList<Sessions> readFromCSV()
    {
        try {
            ArrayList<Sessions> sessionList = new ArrayList<Sessions>();
            File file = new File("database/sessionsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int movieID = Integer.valueOf(tempArr[0]);
               String sessionDate = tempArr[1];
               String sessionTime = tempArr[2];
               ArrayList<Integer> seatingPlan = new ArrayList<Integer>();
               String[] tempSessionID;
               tempSessionID = tempArr[3].split("; ");
               for (String s: tempSessionID) {           
                seatingPlan.add(Integer.valueOf(s));
               }
               Sessions m = new Sessions(movieID, sessionDate, sessionTime, seatingPlan);
               sessionList.add(m);
               
            }
            ;
            return sessionList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * overwrite session csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Sessions> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/sessionsData.csv",false)));
			for(Sessions session:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(session.getMovieID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(session.getSessionDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(session.getSessionTime());
                oneLine.append(CSV_SEPARATOR);
                String cast = session.getSeatingPlan().toString();
                cast = cast.replace(',', ';').substring(1, cast.length() - 1);
                oneLine.append(cast);
        
    
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
     * update session in csv
     * @param s
     */
    @Override
    public  void updateFromCSV(Sessions s) {
        SessionSerializer ss = new SessionSerializer();
	    ArrayList<Sessions> aList = ss.readFromCSV();
	    int flag =0;
	    for (Sessions a:aList) {
		    if (a.getMovieID()==s.getMovieID() && a.getSessionDate().equals(s.getSessionDate()) && a.getSessionTime().equals(s.getSessionTime())) {
                a.setSeatingPlan(s.getSeatingPlan());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        ss.overwriteCSV(aList);
	        System.out.println("Sessions record, date = "+s.getSessionDate()+" time = " + s.getSessionTime()+" successfully updated!");
        }
        else System.out.println("Sessions record, date = "+s.getSessionDate()+" time = " + s.getSessionTime()+" update unsuccessful!");
    }

    
    /** 
     * delete session from csv
     * @param s
     */
    @Override
    public void deleteFromCSV(Sessions s) {
        SessionSerializer ss = new SessionSerializer();
        ArrayList<Sessions> aList = ss.readFromCSV();
        int index=0,flag=0;
        for (Sessions a:aList) {
            if (a.getMovieID()==s.getMovieID() && a.getSessionDate().equals(s.getSessionDate()) && a.getSessionTime().equals(s.getSessionTime())){
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            ss.overwriteCSV(aList);
            System.out.println("Sessions record, date = "+s.getSessionDate()+" time = " + s.getSessionTime()+" successfully deleted!");
        }
        else System.out.println("Sessions record, date = "+s.getSessionDate()+" time = " + s.getSessionTime()+" deletion unsuccessful!");

	
    }
}
