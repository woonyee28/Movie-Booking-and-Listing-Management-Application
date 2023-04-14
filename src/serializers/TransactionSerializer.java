package serializers;

import java.io.*;
import java.util.ArrayList;

import models.Transaction;

public class TransactionSerializer implements InterfaceSerializer<Transaction>{
    private static final String CSV_SEPARATOR = ",";

    
    /** 
     * write transaction to csv
     * @param transaction
     */
    @Override
    public void writeToCSV(Transaction transaction)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/transactionsData.csv", true)));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(transaction.getTID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getMovieGoersID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getBookingDate());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getBookingTime());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getCinemaCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getSeatingNum());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getPrice());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(transaction.getMovieID());

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
     * read transaction from csv
     * @return ArrayList<Transaction>
     */
    @Override
    public ArrayList<Transaction> readFromCSV()
    {
        try {
            ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
            File file = new File("database/transactionsData.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
               tempArr = line.split(CSV_SEPARATOR);
               String TID = tempArr[0];
               int movieGoersID = Integer.valueOf(tempArr[1]);
               String bookingDate = tempArr[2];
               String bookingTime = tempArr[3];
               String cinemaCode = tempArr[4];
               int seatingNum = Integer.valueOf(tempArr[5]);
               double price = Double.valueOf(tempArr[6]);
               int movieID = Integer.valueOf(tempArr[7]);
               Transaction m = new Transaction(TID,movieGoersID,bookingDate,bookingTime,cinemaCode,seatingNum, price, movieID);
               transactionList.add(m);
            }
            ;
            return transactionList;
        } 
        catch(IOException e) {
            e.printStackTrace();
        } 
        return null;
    }

    
    /**
     * overwrite transaction csv
     * @param aList
     */
    @Override
    public void overwriteCSV(ArrayList<Transaction> aList) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database/transactionsData.csv",false)));
			for(Transaction transaction:aList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(transaction.getTID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getMovieGoersID());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getBookingDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getBookingTime());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getCinemaCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getSeatingNum());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getPrice());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transaction.getMovieID());
    
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
     * update transaction in csv
     * @param t
     */
    @Override
    public void updateFromCSV(Transaction t ) {
	    TransactionSerializer ts = new TransactionSerializer();
        ArrayList<Transaction> aList = ts.readFromCSV();
	    int flag =0;
	    for (Transaction a:aList) {
		    if (a.getTID().equals(t.getTID()) ) {
		    	a.setMovieGoersID(t.getMovieGoersID());
                a.setBookingDate(t.getBookingDate());
                a.setBookingTime(t.getBookingTime());
                a.setCinemaCode(t.getCinemaCode());
                a.setSeatingNum(t.getSeatingNum());
                a.setPrice(t.getPrice());
                a.setMovieID(t.getMovieID());
		    	flag=1;
		    	break;
	    	}
	    }
	    if (flag==1){
	        ts.overwriteCSV(aList);
	        System.out.println("Transaction record, id = "+ t.getTID()+" successfully updated!");
        }
        else System.out.println("Transaction record, id = "+ t.getTID()+" update unsuccessful!");
    }
    
    /** 
     * delete transaction in csv
     * @param t
     */
    @Override
    public void deleteFromCSV(Transaction t) {
	    TransactionSerializer ts = new TransactionSerializer();
        ArrayList<Transaction> aList = ts.readFromCSV();
        int index=0,flag=0;
        for (Transaction a:aList) {
            if (a.getTID().equals(t.getTID()) ){
                flag=1;
                break;
            }
            index++;
        }
        if (flag==1) {
            aList.remove(index);
            ts.overwriteCSV(aList);
            System.out.println("Transaction record, id = "+ t.getTID()+" successfully deleted!");
        }
        else System.out.println("Transaction record, id = "+ t.getTID()+" deletion unsuccessful!");
    }
}
