package programs;

import java.text.ParseException;
import java.util.Scanner;

import managers.AdminManager;
import managers.MemberManager;

// import com.apple.laf.resources.aqua;

public class MoblimaMainApp{
    
    /** 
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
		MoblimaMainApp app = new MoblimaMainApp();
        System.out.println("Welcome to MOBLIMA Booking System!");
		app.run();
	}
   
    /**
     * Runs the mainpage of the Moblima app.
     * @throws ParseException
     */
    public void run() throws ParseException{
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        int adminID=0;
        

        do{
            System.out.println("====================MoblimaMainApp======================\n");
            System.out.println("Are you a:\n\t[1] Moblima Member\n\t[2] New User Sign Up\n\t[3] Guest Visit\n\t[4] Moblima Staff\n\t[5] Exit");
            choice = Integer.valueOf(sc.next());
            System.out.println();
            switch (choice) {
				case 1:
                    int movieGoerID = MemberManager.logIn();
                    if(movieGoerID!=-1){
                        MemberApp m = new MemberApp(movieGoerID);
                        m.run();
                    }
					break;
				case 2:
					MemberManager.signUp();
					break;
                case 3:
                    GuestApp gm = new GuestApp();
                    gm.run();
                    break;
				case 4:
                    AdminManager adminmag = new AdminManager(adminID);
                    adminID = adminmag.logIn();
                    if(adminID!=-1){
                        AdminApp a = new AdminApp(adminID);
                        a.run();
                    }
					break;
				case 5:
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 5);
        
        ;
    }
       
}