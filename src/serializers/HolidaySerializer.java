package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Holiday;

public class HolidaySerializer implements InterfaceSerializer<Holiday>{
    private static final String CSV_SEPARATOR = ",";

    
    /** 
     * write holiday to csv
     * @param holiday
     */
    @Override
    public void writeToCSV(Holiday holiday)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/holidayData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(holiday.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(holiday.getDate());

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
     * read holidays from csv
     * @return ArrayList<Holiday>
     */
    @Override
    public ArrayList<Holiday> readFromCSV()
    {
        try {
            ArrayList<Holiday> holidayList = new ArrayList<Holiday>();
            File file = new File("database/holidayData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String name = tempArr[0];
               String date= tempArr[1];
               Holiday m = new Holiday(name,date);
               holidayList.add(m);
            }
            ;
            return holidayList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * overwrite the holidat csv
     * @param hList
     */
    @Override
    public void overwriteCSV(ArrayList<Holiday> hList) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/holidayData.csv",false)));
            for(Holiday h:hList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(h.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(h.getDate());
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
     * update holiday in the csv
     * @param m
     */
    @Override
    public void updateFromCSV(Holiday m) {
        HolidaySerializer hs = new HolidaySerializer();
        ArrayList<Holiday> hList = hs.readFromCSV();
        int flag =0;
        for (Holiday h:hList) {
            if (h.getName().equals(m.getName())) {
                h.setDate(m.getDate());
                flag=1;
            }
        }
        if (flag==1){
        hs.overwriteCSV(hList);
        System.out.println(m.getName() + " Holiday succesfully updated!");
        } else System.out.println("update of "+ m.getName() + " holiday unsuccessful!");
    }

    
    /** 
     * delete the holiday in the csv
     * @param m
     */
    @Override
    public void deleteFromCSV(Holiday m) {
        HolidaySerializer hs = new HolidaySerializer();
        ArrayList<Holiday> hList = hs.readFromCSV();
        int index=0,flag=0;
        for (Holiday h:hList) {
            if (h.getName().equals(m.getName())) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            hList.remove(index);
            hs.overwriteCSV(hList);
            System.out.println(m.getName() + " Holiday succesfully deleted");
        }
        else System.out.println("Deletion of" +m.getName()+" holiday unsuccesful!");

        
    }
}
