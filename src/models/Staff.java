package models;

public class Staff {
    private int staffID;
    private String name;
    private String email;
    private String passwordHashed;
    
    public Staff(){}
    
    public Staff(int staffID, String name, String email, String passwordHashed){
        this.staffID = staffID;
        this.name = name;
        this.email = email;
        this.passwordHashed = passwordHashed;
    }

    
    /** 
     * get staff id
     * @return int
     */
    // get methods
    public int getStaffID(){
        return this.staffID;
    }

    
    /** 
     * get staff name
     * @return String
     */
    public String getName(){
        return this.name;
    }

    
    /** 
     * get staff email
     * @return String
     */
    public String getEmail(){
        return this.email;
    }

    
    /** 
     * get staff hashed password
     * @return String
     */
    public String getPasswordHashed(){
        return this.passwordHashed;
    }

    
    /** 
     * set staff id
     * @param staffID
     */
    // set methods
    public void setStaffID(int staffID){
        this.staffID = staffID;
    }

    
    /** 
     * set staff name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    
    /** 
     * set staff email
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    
    /** 
     * set hashed password
     * @param passwordHashed
     */
    public void setPasswordHashed(String passwordHashed){
        this.passwordHashed = passwordHashed;
    }

    private static final String CSV_SEPARATOR = ",";
    
    /** 
     * Generate String representation of the Object
     * @return String
     */
    public String toString()
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(this.getStaffID());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getName());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getEmail());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getPasswordHashed());

        return oneLine.toString();
    }

   
}
