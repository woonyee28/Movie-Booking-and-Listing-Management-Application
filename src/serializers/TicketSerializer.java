package serializers;

import java.io.*;
import java.util.ArrayList;
import models.Ticket;

public class TicketSerializer implements InterfaceSerializer<Ticket> {
    private static final String CSV_SEPARATOR = ",";

    
    
    /** 
     * write ticket to csv
     * @param t
     */
    @Override
    public void writeToCSV(Ticket t)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/ticketData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(t.getMovieType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getAgeCat());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getDayType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getAfterSix());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getPrice());
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
     * read tickets from csv
     * @return ArrayList<Ticket>
     */
    @Override
    public ArrayList<Ticket> readFromCSV()
    {
        try {
            ArrayList<Ticket> adminList = new ArrayList<Ticket>();
            File file = new File("database/ticketData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String movieType = tempArr[0];
               String cineplexCode = tempArr[1];
               String ageCat = tempArr[2];
               String dayType = tempArr[3];
               Boolean afterSix = Boolean.parseBoolean(tempArr[4]);
               int price =Integer.parseInt(tempArr[5]);
               Ticket newT = new Ticket(movieType,cineplexCode,ageCat,dayType,afterSix,price);
               adminList.add(newT);
               
            }
            ;
            return adminList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /** 
     * overwrite ticket csv
     * @param tList
     */
    @Override
    public void overwriteCSV(ArrayList<Ticket> tList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/ticketData.csv",false)));
			for(Ticket t:tList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(t.getMovieType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getCineplexCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getAgeCat());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getDayType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getAfterSix());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getPrice());
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
     * update ticket in csv
     * @param o
     */
    @Override
    public void updateFromCSV(Ticket o) {
        TicketSerializer tt = new TicketSerializer();
	    ArrayList<Ticket> tList = tt.readFromCSV();
	    int flag =0;
	    for (Ticket t:tList) {
		    if (t.getMovieType().equals(o.getMovieType()) 
            && t.getCineplexCode().equals(o.getCineplexCode()) 
            && t.getAgeCat().equals(o.getAgeCat()) 
            && t.getDayType().equals(o.getDayType())
            && t.getAfterSix()==o.getAfterSix()) {
		    	t.setPrice(o.getPrice());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        tt.overwriteCSV(tList);
	        System.out.println("Ticket: "+ o.getMovieType()+ " "+ o.getCineplexCode()+" " + o.getAgeCat()+ " "+ o.getDayType() + " "+ o.getAfterSix()+ o.getPrice() +" price successfully updated!");
	    } else System.out.println("Ticket: "+ o.getMovieType()+ " "+ o.getCineplexCode()+" " + o.getAgeCat()+ " "+ o.getDayType() + " "+ o.getAfterSix() + o.getPrice() +" price update unsuccesful!");
    }

    
    /** 
     * delete ticket from csv
     * @param o
     */
    @Override
    public void deleteFromCSV(Ticket o) {
        TicketSerializer tt = new TicketSerializer();
        ArrayList<Ticket> tList = tt.readFromCSV();
        int index=0,flag=0;
        for (Ticket t:tList) {
            if (t.getMovieType().equals(o.getMovieType()) 
            && t.getCineplexCode().equals(o.getCineplexCode()) 
            && t.getAgeCat().equals(o.getAgeCat()) 
            && t.getDayType().equals(o.getDayType())
            && t.getAfterSix()==o.getAfterSix()
                && t.getPrice()==o.getPrice())
                {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            tList.remove(index);
            tt.overwriteCSV(tList);
            System.out.println("Ticket: "+ o.getMovieType()+ " "+ o.getCineplexCode()+" " + o.getAgeCat()+ " "+ o.getDayType() + " "+ o.getAfterSix()+ " " + o.getPrice() + " successfully deleted!");
            } else System.out.println("Ticket: "+ o.getMovieType()+ " "+ o.getCineplexCode()+" " + o.getAgeCat()+ " "+ o.getDayType() + " "+ o.getAfterSix()+ " " + o.getPrice() + " deletion unsuccesful!");
        
        }
    
}
