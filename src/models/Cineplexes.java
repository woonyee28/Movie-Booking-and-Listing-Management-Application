package models;

import java.util.ArrayList;

public class Cineplexes {
    String cineplexCode;
    String name;
    ArrayList<String> cinemasCode;

    public Cineplexes() {
    }

    /**
     * Initialize variables of an instance of the Cineplex Class
     * @param cineplexCode
     * @param name
     * @param cinemasCode
     */
    public Cineplexes(String cineplexCode, String name, ArrayList<String> cinemasCode) {
        this.cineplexCode = cineplexCode;
        this.name = name;
        this.cinemasCode = cinemasCode;
    }

    /**
     * returns Cineplex code of a Cineplex Object
     * @return
     */
    public String getCineplexCode() {
        return this.cineplexCode;
    }

    /**
     * sets Cineplex code of a Cineplex object
     * @param cineplexCode
     */
    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    /**
     * returns name of Cineplex object
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * set name of Cineplex Object
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * return CinemasCode of a Cineplex Object
     * @return
     */
    public ArrayList<String> getCinemasCode() {
        return this.cinemasCode;
    }

    /**
     * Set cinemasCode of a Cineplex Object
     * @param cinemasCode
     */
    public void setCinemasCode(ArrayList<String> cinemasCode) {
        this.cinemasCode = cinemasCode;
    }

    
    /** 
     * convert to string
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " cineplexCode='" + getCineplexCode() + "'" +
            ", name='" + getName() + "'" +
            ", cinemasCode='" + getCinemasCode() + "'" +
            "}";
    }

}
