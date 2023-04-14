package managers;

import java.text.DecimalFormat;
import java.util.ArrayList;

import models.*;
import serializers.*;
public class PriceManager {
    /**
     * The PriceSerializer of this PriceManager.
     */
    static PriceSerializer ps = new PriceSerializer();

    /**
     * Create a new PriceManager.
     */
    public PriceManager(){
    }

    /**
     * Prints out the list of price of the respective cineplexes.
     */
    public void viewPricing(){
        
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
}
