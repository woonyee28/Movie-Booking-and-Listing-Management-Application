package models;

public class Holiday {
    private String name;
    private String date;

    public Holiday(){}
    
    /**
     * Initialise variables of an instance of Holiday class
     * @param name
     * @param date
     */
    public Holiday(String name, String date){
        this.name = name;
        this.date = date;
    }

    /**
     * returns name of Holiday
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * returns date of the Holiday
     * @return
     */
    public String getDate(){
        return this.date;
    }

    // set methods
    /**
     * set name of Holiday 
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * set date of Holiday
     * @param date
     */
    public void setDate(String date){
        this.date = date;
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
        oneLine.append(this.getName());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(this.getDate());

        return oneLine.toString();
    }
}
