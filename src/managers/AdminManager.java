package managers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Staff;
import serializers.StaffSerializer;

public class AdminManager implements logIn{
    /**
     * The adminID of this AdminManager.
     */
    private int adminID;
    /**
     * The AdminSerializer of this AdminManager.
     */
    static StaffSerializer ss = new StaffSerializer();

    /**
     * Creates a new AdminManager with the given adminID.
     * @param adminID This AdminManger's adminID.
     */
    public AdminManager(int adminID){
        this.adminID = adminID;
    }



    /**
     * Checks if hash of the entered password matches that of the database.
     * @param email Target email is used to identify the admin.
     * @param hashedPassword Hashed password to be matched.
     * @return true if hashed password matches.
     */

    public boolean checkPassword(String email, String hashedPassword){
        boolean correct = false;
        if (checkExistenceEmail(email)){
                for (Staff s: ss.readFromCSV()){
                    if (s.getEmail().equals(email) && s.getPasswordHashed().equals(hashedPassword)){
                        correct = true;
                    }
                }
        }


        return correct;
    }

    /**
     * Check if email exists in the database.
     * @param email Target email.
     * @return true if email exists.
     */
    public boolean checkExistenceEmail(String email){
        boolean exists = false;
        for (Staff s: ss.readFromCSV()){
            if (s.getEmail().equals(email)){
                exists = true;
                break;
            }
        }

        return exists;
    }

    /**
     * Check if name of Admin exists in the database
     * @param name Target name of Admin to be checked.
     * @return true if name of Admin exists.
     */
    public boolean checkExistenceName(String name){
        boolean exists =false;
        for (Staff s:ss.readFromCSV()){
            if(s.getName().equals(name)){
                exists =true;
                break;
            }
        }
        return exists;
    }

    /**
     * Gets name of Admin using email.
     * @param email Email of target Admin.
     * @return Name of Admin with target email and null if target is not found.
     */
    public String checkName(String email){
        String name = null;
        for (Staff s: ss.readFromCSV()){
            if (s.getEmail().equals(email)){
                name = s.getName();
                break;
            }
        }
        return name;
    }

    /**
     * Generates unique adminID that is greater than the greatest adminID in database by 1.
     * @return Int of unique adminID.
     */
    public int checkExistenceID(){
        int largest = 0;
		for (Staff s:ss.readFromCSV()) {
			if (largest < s.getStaffID()) {
				largest = s.getStaffID();
			}
		}
        return largest;

    }

    /**
     * Gets adminID by email.
     * @param email Target email
     * @return adminID of target email and -1 if target is not found.
     */
    public int getStaffID(String email){
        int staffID =-1;
        for (Staff s : ss.readFromCSV()){
            if(s.getEmail().equals(email)){
                staffID= s.getStaffID();
                break;
            }
        }
        return staffID;

    }
    
    /**
     * Checks if staffID already exists in the database.
     * @param staffID staffID to be checked
     * @return true if staffID exists.
     */
    public boolean checkStaffID(int staffID){
        boolean exists =false;
        for(Staff s:ss.readFromCSV()){
            if(s.getStaffID()==staffID){
                exists =true;
                break;            
            }
        }
        return exists;
    }

    /**
     * Log in function for Admins.
     * Prompts for email and password.
     * @return adminID if succesfully logged in,-1 if unsuccesful.
     */
    public int logIn(){
        AdminManager login = new AdminManager(this.adminID);
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
				return login.getStaffID(email);
			}
			else {
				System.out.println("Wrong password!");
				return -1;
			}
		}

    }

    

    /**
     * Creates new Admin
     * Generates new adminID and checks if email is already in use.
     * If email is not in used, new Admin will be written to CSV using AdminSerializer.
     * @return 1 if succesfully created, 0 if unsuccessful.
     */
    public int createAdmin(){
        AdminManager create = new AdminManager(this.adminID);
        int staffID = 0;
        String name=null;
        String email=null;
        String password = null;
        String passwordHashed = null;
     
		
        System.out.println("--------- Create an admin account ---------");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter your email:");
        email = input1.nextLine();

        if (!create.checkExistenceEmail(email)){
            staffID = create.checkExistenceID() + 1;
            System.out.println("Please enter your name:");
            name = input1.nextLine();

            Scanner input2 = new Scanner(System.in); 
            System.out.println("Please enter your password:");
            password = input2.nextLine();
            
            passwordHashed = String.valueOf(password.hashCode());
            ;
            Staff newStaff = new Staff(staffID, name, email, passwordHashed);
            ss.writeToCSV(newStaff);
            System.out.println("Admin account successfully created.");
            return 1;
        }
        else{
            System.out.println("Email already in use!");
        }
        ;
        return 0;
		
    }

    /**
     * Prints list of adminID,name and email for all Admins.
     * Uses AdminSerializer
     */
    public void printAdminList(){
        System.out.println("--------- Admin list ---------");
        for (Staff s:ss.readFromCSV()){
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(s.getStaffID());
            oneLine.append(",");
            oneLine.append(s.getName());
            oneLine.append(",");
            oneLine.append(s.getEmail());
            System.out.println(oneLine);
        }
    }

    /**
     * Changes hashed password of existing Admin in database.
     * Uses AdminSerializer.
     */
    public void updateAdminPassword(){
        String email=null,password,passwordHashed=null,name;
        int staffID;
        AdminManager update = new AdminManager(this.adminID);
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("--------- Update admin password ---------");
        System.out.println("Please enter your staffID:");
        staffID=input1.nextInt();

        if(update.checkStaffID(staffID)){
            System.out.println("Please enter admin name:");
            name=input2.nextLine();

            if(update.checkExistenceName(name)){
                System.out.println("Please enter email:");
                email=input2.nextLine();

                if (update.checkExistenceEmail(email)){
                    System.out.println("Please enter new password:");
                    password=input2.nextLine();

                    passwordHashed = String.valueOf(password.hashCode());
                    Staff upd = new Staff();
                    upd.setEmail(email);
                    upd.setName(name);
                    upd.setPasswordHashed(passwordHashed);
                    upd.setStaffID(staffID);
                    ss.updateFromCSV(upd);

                }else System.out.println("Admin email does not found!");
            }else System.out.println("Admin name does not found!");
        }else System.out.println("Admin ID does not found!");
        ;
        ;
    }

    /**
     * Deletes Admin with target adminID and name.
     * Uses AdminSerializer.
     * Currently logged in Admin cannot delete own account.
     * @param accountHolderID This AdminManager's adminID.
     */
    public void deleteAdmin(int accountHolderID){
        int staffID;
        String name,confirm;
        AdminManager delete = new AdminManager(this.adminID);
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("--------- Delete admin account ---------");
        System.out.println("Please enter your staffID:");
        staffID=input1.nextInt();

        if (delete.checkStaffID(staffID)){
            if(accountHolderID!=staffID){
                System.out.println("Please enter admin name:");
                name=input2.nextLine();
                if (delete.checkExistenceName(name)){
                    System.out.println("Press y/n to confirm deletion ");
                    confirm =input2.next();
                    if(confirm.equals("y")){
                        Staff del = new Staff();
                        del.setName(name);
                        del.setStaffID(staffID);
                        ss.deleteFromCSV(del);
                    } else System.out.println("Deletion aborted.");
                }else System.out.println("Admin name not found.");
            }else System.out.println("Error! Cannot delete currently logged in admin!");
        }else System.out.println("Admin ID not found.");
        ;
        ;
    }
}
