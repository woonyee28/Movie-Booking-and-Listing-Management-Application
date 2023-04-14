package managers;

import serializers.*;
import models.*;
import java.util.*;

public class SettingsManager {  
    /**
     * The adminID of this SettingsManager.
     */
    private int adminID;
    /**
     * The HolidaySerializer of this SettingsManager.
     */
    static HolidaySerializer hs = new HolidaySerializer();
    /**
     * The PriceSerializer of this SettingsManager.
     */
    static PriceSerializer ps = new PriceSerializer();
    /**
     * Create a new SettingsManager with given adminID.
     * @param adminID This SettingsManager's adminID.
     */
    public SettingsManager(int adminID){
        this.adminID = adminID;
    }

    
    /**
     * Check if Holiday of given name exists.
     * @param name Name of Holiday to be checked.
     * @return true if Holiday exists.
     */
    public boolean checkExistenceHol(String name){
        boolean exists =false;
        for (Holiday h :hs.readFromCSV()){
            if (h.getName().equals(name)){
                
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Check if Price of given movieType and category exists.
     * @param movieType Movie Type to be checked.
     * @param cat Category to be checked.
     * @return true if Price with given movieType and category exists.
     */
    public boolean checkExistencePrice(String movieType,String cat){
        boolean exists =false;
        for (Price p:ps.readFromCSV()){
            if(p.getCat().equals(cat) && p.getMovieType().equals(movieType)){
                exists =true;
                break;
            }
        }
        return exists;
    }
    /**
     * Prints all Holiday.
     */
    public void printHolidayList(){
        System.out.println("--------- Holiday list ---------");
        for (Holiday h:hs.readFromCSV()){
            h.toString();
            System.out.println(h);
        }
    }

    /**
     * Adds a new Holiday to the database.
     * Name of Holiday needs to be unique.
     */
    public void addHoliday(){
        String name,date;
        SettingsManager add = new SettingsManager(this.adminID);
        Scanner input1 = new Scanner(System.in);
        System.out.println("--------- Add holiday ---------");
        System.out.println("Please enter name of holiday to be added:");
        name=input1.nextLine();
        if(!add.checkExistenceHol(name)){
            System.out.println("Please enter date (dd-mm-yyyy):");
            date=input1.nextLine();
            Holiday newH = new Holiday(name, date);
            hs.writeToCSV(newH);
            printHolidayList();
        }else System.out.println(name +" holiday not found!");
        ;

    }

   
    /**
     * Update an existing Holiday.
     * Name of Holiday will be used to identify the Holiday to be changed..
     */
    public void updateHoliday(){
            String name,newDate;
            SettingsManager update = new SettingsManager(this.adminID);
            Scanner input1 = new Scanner(System.in);
    
            System.out.println("--------- Update holiday ---------");
            System.out.println("Please enter name of holiday to be updated:");
            name=input1.nextLine();
            if(update.checkExistenceHol(name)){
                System.out.println("Please enter new date (yyyymmdd):");
                newDate=input1.nextLine();
                Holiday up = new Holiday();
                up.setDate(newDate);
                up.setName(name);
                hs.updateFromCSV(up);
                printHolidayList();
            }else System.out.println(name +" holiday not found!");
            ;
    }
    /**
     * Deletes a Holiday.
     * Name of Holiday and Date will be used to identify Holiday to be deleted.
     */
    public void deleteHoliday(){
        String name;
        SettingsManager delete = new SettingsManager(this.adminID);
        Scanner input1 = new Scanner(System.in);

        System.out.println("--------- Delete holiday ---------");
        System.out.println("Please enter name of holiday to be deleted:");
        name=input1.nextLine();
        if(delete.checkExistenceHol(name)){
            Holiday up = new Holiday();
            up.setName(name);
            hs.deleteFromCSV(up);
            printHolidayList();
        }else System.out.println(name +" holiday not found!");
        ;
    }
    /**
     * Prints all Prices.
     */
    public void printPriceList(){
        
        System.out.println("---------- View Pricing ----------");
        String Class[]={"Regular","Gold","Platinum"};  
        String bold ="\033[0;1m"; 
        String normal="\033[0m";
        String italics ="\033[3m";
        String pp;
        for (String s:Class){
            System.out.println("----------------------------------");
            int numChar =34-1-s.length()+normal.length();
            System.out.print("|"+bold+italics+s);
            System.out.printf("%"+numChar+"s",normal+"|");
            System.out.println();
            System.out.println("----------------------------------");
            System.out.println(bold+"|Movie Type |Category     |Price |"+normal);
            System.out.println("----------------------------------");
            for (Price p: ps.readFromCSV()){
                StringBuffer oneLine = new StringBuffer();
                oneLine.append("|");
                oneLine.append(String.format("%-11s",p.getMovieType()));
                oneLine.append("|");
                oneLine.append(String.format("%-13s", p.getCat()));
                oneLine.append("|");
                Double price =p.getPrice();
                if (s.equals("Gold")){
                    price *=2;
                }
                else if(s.equals("Platinum")){
                    price*=3;
                }
                oneLine.append("$");
                pp =String.format("%.2f",price);
                oneLine.append(String.format("%5s",pp));
                oneLine.append("|");
                System.out.println(oneLine);
                System.out.println("----------------------------------");
            }
            System.out.println();
        }
        System.out.println("Prices are inclusive of GST.");
        System.out.println("Weekends and Public Holiday falls under \"Special\" category." );
        System.out.println();
    }   

    /**
     * Change price of existing Price.
     * Movie Type and Category will be used to identify the Price to be changed.
     */
    public void editPrices(){
        String cat,movieType;
        double newPrice;
        SettingsManager edit = new SettingsManager(this.adminID);
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("--------- Edit prices ---------");
        System.out.println("Please enter Movie Type to be edited:");
        movieType=input1.nextLine();
        System.out.println("Please enter Category to be edited:");
        cat=input1.nextLine();
        if(edit.checkExistencePrice(movieType,cat)){
            System.out.println("Please enter new price:");
            newPrice=input2.nextDouble();
            Price p =new Price(movieType,cat, newPrice);
            ps.updateFromCSV(p);
            printPriceList();
        }else System.out.println("Movie type: "+ movieType+" Category: "+cat +" not found!");
        ;
        ;
    }

}

