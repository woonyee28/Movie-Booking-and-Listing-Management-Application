package models;

public class Ticket{
   
        private String movieType;
        private String cineplexCode;
        private String ageCat;
        private String dayType;
        private boolean afterSix;
        private int price;

        public Ticket(){}

        public Ticket(String movieType,String cineplexCode,String ageCat,String dayType,boolean afterSix,int price){
            this.movieType=movieType;
            this.cineplexCode=cineplexCode;
            this.ageCat=ageCat;
            this.dayType=dayType;
            this.afterSix=afterSix;
            this.price=price;
        }

        
        /** 
         * get movie type
         * @return String
         */
        public String getMovieType() {
            return movieType;
        }
        
        /** 
         * set movie type
         * @param movieType
         */
        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }
        
        /** 
         * get cineplex code
         * @return String
         */
        public String getCineplexCode() {
            return cineplexCode;
        }
        
        /** 
         * set cineplex code
         * @param cineplexCode
         */
        public void setCineplexCode(String cineplexCode) {
            this.cineplexCode = cineplexCode;
        }
        
        /** 
         * get age category
         * @return String
         */
        public String getAgeCat() {
            return ageCat;
        }
        
        /** 
         * set age category
         * @param ageCat
         */
        public void setAgeCat(String ageCat) {
            this.ageCat = ageCat;
        }
        
        /** 
         * get day type
         * @return String
         */
        public String getDayType() {
            return dayType;
        }
        
        /** 
         * set time
         * @param dayType
         */
        public void setTime(String dayType) {
            this.dayType = dayType;
        }
        
        /** 
         * get price
         * @return int
         */
        public int getPrice() {
            return price;
        }
        
        /** 
         * set price
         * @param price
         */
        public void setPrice(int price) {
            this.price = price;
        }
        
        
        /** 
         * get if it is after six oclock
         * @return boolean
         */
        public boolean getAfterSix(){
            return afterSix;
        }

        
        /** 
         * set if it is after six oclock
         * @param afterSix
         */
        public void setAfterSix(boolean afterSix){
            this.afterSix=afterSix;
        }

        private static final String CSV_SEPARATOR =",";
        
        
        /** 
         * Generate String representation of the Object
         * @return String
         */
        public String toString() {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(this.getMovieType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getAgeCat());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getDayType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getAfterSix());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getPrice());
            return oneLine.toString();
            
        }
        
}
