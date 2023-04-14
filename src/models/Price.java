package models;

public class Price {
    private String movieType;
    private String cat;
    private double price;
   

    public Price(){}
    
    /**
     * Initialise variables of an instance of Price Object
     * @param movieType
     * @param cat
     * @param price
     */
    public Price(String movieType,String cat, double price){
        this.cat=cat;
        this.price=price;
        this.movieType=movieType;
    }

    /**
     * returns movie type of a Price object
     * @return
     */
    public String getMovieType(){
        return this.movieType;
    }

    /**
     * returns category of theatre (Regular,Gold, Platinum) of a Price Object
     * @return
     */
    public String getCat() {
        return cat;
    }
    
    /**
     * Returns price of a ticket in Price Object
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the category type of a ticket in Price object
     * @param cat
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * Sets the price of a ticket in Price object
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the movie type of a ticket in Price object
     * @param movieType
     */
    public void setMovieType(String movieType){
        this.movieType = movieType;
    }

    private static final String CSV_SEPARATOR =",";
    
    /**
     * Prints out all variables of a Price object
     */
    public String toString() {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getMovieType());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getCat());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(String.format("%.2f",this.getPrice()));
        return oneLine.toString();     
    }
    
}
