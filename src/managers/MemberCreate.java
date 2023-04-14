package managers;
import java.util.*;

import models.MovieGoer;
import serializers.MovieGoerSerializer;

public class MemberCreate implements logIn {
    private ArrayList<MovieGoer> mList;
    static MovieGoerSerializer mgs = new MovieGoerSerializer();

    public MemberCreate(){
        this.mList= mgs.readFromCSV();
    }

    
    /** 
     * check if passwords matched
     * @param email
     * @param hashedPassword
     * @return boolean
     */
    @Override
    public boolean checkPassword(String email, String hashedPassword) {
        boolean correct = false;
		if (checkExistenceEmail(email))
			for (MovieGoer m :mList) {
				if (m.getEmail().equals(email) && m.getPasswordHashed().equals(hashedPassword)) {
						correct = true;
				}
			}
		
		return correct;
    }

    
    /** 
     * check if email exists
     * @param email
     * @return boolean
     */
    @Override
    public boolean checkExistenceEmail(String email) {
        boolean exists =false;
		for (MovieGoer m:this.mList ) {
			if (m.getEmail().equals(email)) {
				exists = true;
				break;
			}
		}
        return exists;
    }


    
    /** 
     * check if id exists
     * @return int
     */
    public int checkExistenceID(){
        int largest = 0;
		for (MovieGoer m:this.mList) {
			if (largest < m.getMovieGoersID()) {
				largest = m.getMovieGoersID();
			}
		}
        return largest;

    }

    
    /** 
     * run member create
     * @return int
     */
    public int run(){
        MemberCreate create = new MemberCreate();
        int movieGoerID = 0;
        String name=null;
        String email=null;
        int age = -1;
        String password = null;
        String passwordHashed = null;
        int mobile = -1;
		
        System.out.println("--------- Create an account ---------");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter your email ID:");
        email = input1.nextLine();

        if (!create.checkExistenceEmail(email)){
            movieGoerID = checkExistenceID() + 1;
            System.out.println("Please enter your name:");
            name = input1.nextLine();
            age = -1;
            while (age!=-1){
                System.out.println("Please enter your age:");
                int test = input1.nextInt();
                if (test<=12||test>=99){
                    System.out.println("Please enter a valid age. (12-99)");
                }else{
                    age = test;
                }
            }
            System.out.println("Please enter your mobile number:");
            mobile = input1.nextInt();

            Scanner input2 = new Scanner(System.in); 
            System.out.println("Please enter your password:");
            password = input2.nextLine();
            ;
            
            
            passwordHashed = String.valueOf(password.hashCode());

            MovieGoer newMem = new MovieGoer(movieGoerID, name, email, age, passwordHashed, mobile, null);
            mgs.writeToCSV(newMem);
            System.out.println("Account successfully created. Please log in again");
            return 1;
        }
        else{
            System.out.println("Account already exists!");
        }
        ;
    
        return 0;
		
    }
    
}