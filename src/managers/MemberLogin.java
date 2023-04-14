package managers;

import serializers.MovieGoerSerializer;
import java.util.*;

import models.MovieGoer;;
public class MemberLogin implements logIn{
    private ArrayList<MovieGoer> mList;
    static MovieGoerSerializer mgs = new MovieGoerSerializer();
    

    public MemberLogin(){
        this.mList = mgs.readFromCSV();
    }

    
    /** 
     * check if passwords matched
     * @param email
     * @param hashedPassword
     * @return boolean
     */
    @Override
    public boolean checkPassword(String email, String hashedPassword){
        boolean correct = false;
        if (checkExistenceEmail(email)){
                for (MovieGoer m: mList){
                    if (m.getEmail().equals(email) && m.getPasswordHashed().equals(hashedPassword)){
                        correct = true;
                    }
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
    public boolean checkExistenceEmail(String email){
        boolean exists = false;
        for (MovieGoer m: this.mList){
            if (m.getEmail().equals(email)){
                exists = true;
                break;
            }
        }

        return exists;
    }

    
    /** 
     * check if name exists
     * @param email
     * @return String
     */
    public String checkName(String email){
        String name = email;
        for (MovieGoer m: this.mList){
            if (m.getEmail().equals(email)){
                name = m.getName();
                break;
            }
        }
        return name;
    }

    
    /** 
     * run member login
     * @param email
     * @param password
     * @return int
     */
    public static int run(String email, String password){
        MemberLogin login = new MemberLogin();
        if(!login.checkExistenceEmail(email)){
			System.out.println("Email does not exist!");
			return 0;
		}
		else {
			
			if(login.checkPassword(email,String.valueOf(password.hashCode()))){
				String name = login.checkName(email);
                System.out.println("Logging in...");
				System.out.println("Welcome "+ name);
				return 1;
			}
			else {
				System.out.println("Wrong password!");
				return 0;
			}
		}

    }
}
