package managers;

import serializers.*;

import java.util.ArrayList;
import java.util.Scanner;

import models.*;

public class MemberManager implements logIn {
   /**
    * The MovieGoerSerializer of this MemberManager.
    */
    static MovieGoerSerializer mgs = new MovieGoerSerializer(); 
    
     /**
     * Checks if hash of the entered password matches that of the database.
     * @param email Target email is used to identify the Member.
     * @param hashedPassword Hashed password to be matched.
     * @return true if hashed password matches.
     */
    @Override
    public boolean checkPassword(String email, String hashedPassword){
        boolean correct = false;
        if (checkExistenceEmail(email)){
                for (MovieGoer m: mgs.readFromCSV()){
                    if (m.getEmail().equals(email) && m.getPasswordHashed().equals(hashedPassword)){
                        correct = true;
                    }
                }
        }


        return correct;
    }
     /**
     * Check if email of Member exists in the database
     * @param name Target email of Member to be checked.
     * @return true if name of Member exists.
     */
    @Override
    public boolean checkExistenceEmail(String email){
        boolean exists = false;
        for (MovieGoer m: mgs.readFromCSV()){
            if (m.getEmail().equals(email)){
                exists = true;
                break;
            }
        }

        return exists;
    }

    /**
     * Gets name of Member using email.
     * @param email Email of target Member.
     * @return Name of Member with target email.
     */
    public String checkName(String email){
        String name = email;
        for (MovieGoer m: mgs.readFromCSV()){
            if (m.getEmail().equals(email)){
                name = m.getName();
                break;
            }
        }
        return name;
    }
    
    
    
    /** 
     * @return int
     */
    /*
     * Log in function for Member.
     * Prompts for email and password.
     * @return 1 if successfully logged in. 0 otherwise.
     */
    public static int logIn(){
        MemberManager login = new MemberManager();
        Scanner input = new Scanner(System.in);
        System.out.println("Please key in your email:");
        String email = input.nextLine();
        System.out.println("Please key in your password:");
        char[] password1 = System.console().readPassword("%s", "Password:");
        String password = new String(password1);
        ;
        if(!login.checkExistenceEmail(email)){
			System.out.println("Email does not exist!");
			return -1;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				String name = login.checkName(email);
                System.out.println("Logging in...");
				System.out.println("Welcome "+ name);
				return MemberManager.checkMovieGoerID(email);
			}
			else {
				System.out.println("Wrong password!");
				return -1;
			}
		}
    
    }
     /**
     * Generates unique memberID that is greater than the greatest memberID in database by 1.
     * @return Int of unique memberID.
     */
    public int checkExistenceID(){
        int largest = 0;
		for (MovieGoer m:mgs.readFromCSV()) {
			if (largest < m.getMovieGoersID()) {
				largest = m.getMovieGoersID();
			}
		}
        return largest;
    }
     /**
     * Creates new Member
     * Generates new MemberID and checks if email is already in use.
     * If email is not in used, new Member will be written to CSV using MovieGoerSerializer.
     * @return 1 if succesfully created, 0 if unsuccessful.
     */
    public static int createMember(){
        MemberManager create = new MemberManager();
        int movieGoerID = 0;
        String name=null;
        String email=null;
        int age = -1;
        String password = null;
        String passwordHashed = null;
        int mobile = -1;
		
        System.out.println("--------- Create an account ---------");
        Scanner input1 = new Scanner(System.in);
        email = "";
        System.out.println("Please enter your email:");
        email = input1.nextLine();
        while (email.contains("@")==false){
            System.out.println("Please enter a valid email:");
            email = input1.nextLine();
        }
        

        if (!create.checkExistenceEmail(email)){
            movieGoerID = create.checkExistenceID() + 1;
            System.out.println("Please enter your name:");
            name = input1.nextLine();
            age = -1;
            while (age==-1){
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
            System.out.println("Email already in use!");
        }
        ;
        return 0;
		
    }
    
    /**
     * signUp function.
     * Uses createMember.
     * if creation is succesful, Member are directed to logIn.
     */
    public static void signUp(){
        // Create Account then go back to memberLogIn
        int check1=0;
        check1 = createMember();
    }
    /**
     * Get MovieGoer's ID from given email.
     * @param email Email of MovieGoer to have name returned.
     * @return name of MovieGoer.
     */
    public static int checkMovieGoerID(String email){
        for (MovieGoer m: mgs.readFromCSV()){
            if (m.getEmail().equals(email)){
                return m.getMovieGoersID();
            }
        }
        return -1;
    }

    
    
}
