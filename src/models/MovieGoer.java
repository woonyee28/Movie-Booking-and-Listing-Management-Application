package models;

public class MovieGoer {
    private int movieGoersID;
    private String name;
    private String email;
    private int age;
    private String passwordHashed;
    private int mobileNumber; 
    private String TID_List;

    public MovieGoer(){}
    
    /**
     * Initialising the variables of an instance of a MovieGoer class
     * @param movieGoersID
     * @param name
     * @param email
     * @param age
     * @param passwordHashed
     * @param mobileNumber
     * @param TID_List
     */
    public MovieGoer(int movieGoersID, String name, String email, int age, String passwordHashed, int mobileNumber, String TID_List){
        this.movieGoersID = movieGoersID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.passwordHashed = passwordHashed;
        this.mobileNumber = mobileNumber;
        this.TID_List = TID_List;
    }

    // get methods
    /**
     * return movieGoerID of member in MovieGoer object
     * @return
     */
    public int getMovieGoersID(){
        return this.movieGoersID;
    }

    /**
     * return name of member in MovieGoer object
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * return email address of member in MovieGoer object
     * @return
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * return age of member in MovieGoer object
     * @return
     */
    public int getAge(){
        return this.age;
    }

    /**
     * return hashed password of member's account in MovieGoer object
     * @return
     */
    public String getPasswordHashed(){
        return this.passwordHashed;
    }

    /**
     * return mobile number of member in MovieGoer object
     * @return
     */
    public int getMobileNumber(){
        return this.mobileNumber;
    }

    /**
     * returns list of transactions of member in MovieGoer object
     * @return
     */
    public String getTID_List(){
        return this.TID_List;
    }

    // set methods
    /**
     * set movieGoerID for member in MovieGoer object
     * @param movieGoersID
     */
    public void setMovieGoersID(int movieGoersID){
        this.movieGoersID = movieGoersID;
    }

    /**
     * set name for member in MovieGoer object
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * set email address for member in MovieGoer object
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * set age for member in MovieGoer object
     * @param age
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * set hashed password for member in MovieGoer object
     * @param passwordHashed
     */
    public void setpasswordHashed(String passwordHashed){
        this.passwordHashed = passwordHashed;
    }

    /**
     * set mobile number for member in MovieGoer object
     * @param mobileNumber
     */
    public void setMobileNumber(int mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    /**
     * set transactionID list for member in MovieGoer object
     * @param TID_List
     */
    public void setTID_List(String TID_List){
        this.TID_List = TID_List;
    }

    // Generate String representation of the Object
    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * convert to string
     * @return String
     */
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getMovieGoersID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getName());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getEmail());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getAge());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getPasswordHashed());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getMobileNumber());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getTID_List());

        return oneLine.toString();
    }
}
