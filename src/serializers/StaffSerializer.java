package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Staff;

public class StaffSerializer implements InterfaceSerializer<Staff>{

    private static final String CSV_SEPARATOR = ",";
    
    
    /** 
     * write to staff csv
     * @param admin
     */
    @Override
    public void writeToCSV(Staff admin)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/StaffsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(admin.getStaffID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(admin.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(admin.getEmail());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(admin.getPasswordHashed());
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
     * read staffs from csv
     * @return ArrayList<Staff>
     */
    @Override
    public ArrayList<Staff> readFromCSV()
    {
        try {
            ArrayList<Staff> adminList = new ArrayList<Staff>();
            File file = new File("database/staffsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               int staffID = Integer.valueOf(tempArr[0]);
               String name = tempArr[1];
               String email = tempArr[2];
               String hashed = tempArr[3];
               Staff admin = new Staff(staffID,name,email,hashed);
               adminList.add(admin);
               
            }
            ;
            return adminList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /** overwrite staff csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Staff> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/staffsData.csv",false)));
			for(Staff a:aList) {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(a.getStaffID());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(a.getName());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(a.getEmail());
				oneLine.append(CSV_SEPARATOR);
	            oneLine.append(a.getPasswordHashed());
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
     * update staff in csv
     * @param o
     */
    @Override
    public void updateFromCSV(Staff o) {
        StaffSerializer ss = new StaffSerializer();
	    ArrayList<Staff> aList = ss.readFromCSV();
	    int flag =0;
	    for (Staff a:aList) {
		    if (a.getName().equals(o.getName()) && a.getStaffID()==o.getStaffID() ) {
		    	a.setEmail(o.getEmail());
		    	a.setPasswordHashed(o.getPasswordHashed());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        ss.overwriteCSV(aList);
	        System.out.println("Admin account: name = " +o.getName()+" id = "+ o.getStaffID()+" successfully updated!");
	    } else System.out.println("Admin account: name = " +o.getName()+" id = "+ o.getStaffID()+" update unsuccessful!");
    }

    
    /** 
     * delete staff from csv
     * @param o
     */
    @Override
    public void deleteFromCSV(Staff o) {
        StaffSerializer ss = new StaffSerializer();
        ArrayList<Staff> aList = ss.readFromCSV();
        int index=0,flag=0;
        for (Staff a:aList) {
            if (a.getName().equals(o.getName()) && a.getStaffID()==o.getStaffID() ) {
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            ss.overwriteCSV(aList);
            System.out.println("Admin account: name = " +o.getName()+" id = "+ o.getStaffID()+" successfully deleted!");
        }
        else System.out.println("Staff record, name = " +o.getName()+" id = "+ o.getStaffID()+" deletion unsuccessful!");

	
    }
}
