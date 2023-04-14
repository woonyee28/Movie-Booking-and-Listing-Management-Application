package programs;

import java.util.Scanner;

import managers.AdminManager;


public class UIManageAdmin {
    /**
     * THe adminID of this UIManageAdmin,
     */
    private int adminID;
    /**
     * Creates a new UIManageAdmin with given adminID.
     * @param adminID
     */
    public UIManageAdmin(int adminID){
        this.adminID=adminID;
    }
    /**
     * THe menuOptions of this UIManageAdmin.
     */
    private final String menuOptions[] = {
        "Print Admin List",
        "Update Admin",
        "Delete Admin",
        "Exit ManageAdmin"
    };
    /**
     * Gets adminID of the UIManageAdmin.
     * @return Returns this UIManageAdmin's adminID.
     */
    public int getAdminID(){
        return this.adminID;
    }

    /**
     * Runs UIManageAdmin.
     */
    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================ManageAdmin======================\n");
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
            AdminManager adminmag = new AdminManager(this.adminID);
			
            switch (choice) {
				case 1:
                    // Prints admin list
                    adminmag.printAdminList();
                    break;
                case 2:
                    // Update admin(change pass/email)
                    adminmag.updateAdminPassword();
                    break;
                case 3:
                    // Delete admin
                    adminmag.deleteAdmin(this.adminID);
                    break;
				case 4:
					System.out.println("Exiting to the previous level...");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
        ;
    }
}
