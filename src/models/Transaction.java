package models;

public class Transaction {
    private String TID;
    private int movieGoersID;
    private String bookingDate;
    private String bookingTime;
    private String cinemaCode;
    private int seatingNum;
    private double price;
    private int movieID;

    public Transaction(){}
    
    public Transaction(String TID, int movieGoersID, String bookingDate, String bookingTime, String cinemaCode, int seatingNum, double price, int movieID ){
        this.TID = TID;
        this.movieGoersID = movieGoersID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.cinemaCode = cinemaCode;
        this.seatingNum = seatingNum;
        this.price = price;
        this.movieID = movieID;
    }

    
    /** 
     * get tid
     * @return String
     */
    // get methods
    public String getTID(){
        return this.TID;
    }

    
    /** 
     * get movie goer id
     * @return int
     */
    public int getMovieGoersID(){
        return this.movieGoersID;
    }

    
    /** 
     * get booking date
     * @return String
     */
    public String getBookingDate(){
        return this.bookingDate;
    }

    
    /** 
     * get booking time
     * @return String
     */
    public String getBookingTime(){
        return this.bookingTime;
    }

    
    /** 
     * get cinema code
     * @return String
     */
    public String getCinemaCode(){
        return this.cinemaCode;
    }

    
    /** 
     * get seating number
     * @return int
     */
    public int getSeatingNum(){
        return this.seatingNum;
    }

    
    /** 
     * get price
     * @return double
     */
    public double getPrice(){
        return this.price;
    }

    
    /** 
     * get movie id
     * @return int
     */
    public int getMovieID(){
        return this.movieID;
    }

    
    /** 
     * set tid
     * @param TID
     */
    // set methods

    public void setTID(String TID){
        this.TID = TID;
    }

    
    /** 
     * set movie goers id
     * @param movieGoersID
     */
    public void setMovieGoersID(int movieGoersID){
        this.movieGoersID = movieGoersID;
    }

    
    /** 
     * set booking date
     * @param bookingDate
     */
    public void setBookingDate(String bookingDate){
        this.bookingDate = bookingDate;
    }

    
    /** 
     * set booking time
     * @param bookingTime
     */
    public void setBookingTime(String bookingTime){
        this.bookingTime = bookingTime;
    }

    
    /** 
     * set cinema code
     * @param cinemaCode
     */
    public void setCinemaCode(String cinemaCode){
        this.cinemaCode = cinemaCode;
    }

    
    /** 
     * set seating number
     * @param seatingNum
     */
    public void setSeatingNum(int seatingNum){
        this.seatingNum = seatingNum;
    }

    
    /** 
     * set price
     * @param price
     */
    public void setPrice(double price){
        this.price = price;
    }

    
    /** set movie id
     * @param movieID
     */
    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * Generate String representation of the Object
     * @return String
     */
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getTID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieGoersID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getBookingDate());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getBookingTime());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getCinemaCode());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getSeatingNum());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getPrice());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMovieID());

        return oneLine.toString();
    }
}
