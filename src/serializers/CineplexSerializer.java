package serializers;

import java.io.*;
import java.util.ArrayList;
 
import models.Cineplexes;

public class CineplexSerializer implements InterfaceSerializer<Cineplexes>{
    private static final String CSV_SEPARATOR = ",";

    
    /** write cineplex to csv
     * @param cineplexes
     */
    @Override
    public void writeToCSV(Cineplexes cineplexes)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cineplexesData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(cineplexes.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(cineplexes.getName());
            oneLine.append(CSV_SEPARATOR);
            String cast = cineplexes.getCinemasCode().toString();
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
     * read cineplex from csv
     * @return ArrayList<Cineplexes>
     */
    @Override
    public ArrayList<Cineplexes> readFromCSV()
    {
        try {
            ArrayList<Cineplexes> cineplexesList = new ArrayList<Cineplexes>();
            File file = new File("database/cineplexesData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String cineplexCode = tempArr[0];
               String name = tempArr[1];
               ArrayList<String> cinemasCode = new ArrayList<String>();
               String[] tempCast;
               tempCast = tempArr[2].split("; ");
               for (String s: tempCast) {           
                cinemasCode.add(s);
               }
               Cineplexes m = new Cineplexes(cineplexCode,name,cinemasCode);
               cineplexesList.add(m);
               
            }
            ;
            return cineplexesList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }  
    
    /** 
     * overwrite the cineplex csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Cineplexes> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/cineplexesData.csv",false)));
			for(Cineplexes cineplexes:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(cineplexes.getCineplexCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cineplexes.getName());
                oneLine.append(CSV_SEPARATOR);
                String cast = cineplexes.getCinemasCode().toString();
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
     * update the cineplex in csv
     * @param m
     */
    @Override
    public void updateFromCSV(Cineplexes m) {
        CineplexSerializer cp = new CineplexSerializer();
	    ArrayList<Cineplexes> aList = cp.readFromCSV();
	    int flag =0;
	    for (Cineplexes a:aList) {
		    if (a.getCineplexCode().equals(m.getCineplexCode()) ) {
                a.setName(m.getName());
                a.setCinemasCode(m.getCinemasCode());;
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        cp.overwriteCSV(aList);
	        System.out.println("Cineplexes record, code = "+ m.getCineplexCode()+" successfully updated!");
        }
        else System.out.println("Cineplexes record, code = "+m.getCineplexCode()+" update unsuccessful!");
    }
    
    /** 
     * delete the cineplex in the csv
     * @param m
     */
    @Override
    public void deleteFromCSV(Cineplexes m) {
        CineplexSerializer cp = new CineplexSerializer();
        ArrayList<Cineplexes> aList = cp.readFromCSV();
        int index=0,flag=0;
        for (Cineplexes a:aList) {
            if (a.getCineplexCode().equals(m.getCineplexCode())) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            cp.overwriteCSV(aList);
            System.out.println("Cineplexes record, code = "+ m.getCineplexCode()+" successfully deleted!");
        }
        else System.out.println("Cineplexes record, code = "+ m.getCineplexCode()+" deletion unsuccessful!");

	
    }
}
