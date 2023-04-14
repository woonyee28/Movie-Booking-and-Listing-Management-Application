package programs;

import java.util.Scanner;

import managers.SettingsManager;

public class UIConfigSystem {
    /**
     * The adminID of this UIConfigSystem.
     */
    private int adminID;
    /**
     * Creates a new UIConfigSystem with given adminID.
     * @param adminID This UIConfigSystem's adminID.
     */
    public UIConfigSystem(int adminID){
        this.adminID = adminID;
    }
    /**
     * THe menuOptions of this UIConfigSystem.
     */
    private final String menuOptions[] = {
        "Print Holiday List",
        "Add New Holiday",
        "Update Holiday",
        "Delete Holiday",
        "Print Price List",
        "Edit Prices",
        "Exit System Config"
    };
    /**
     * Runs UIConfigSystem.
     */
    public void run(){
        int i,choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("====================ConfigureSystemSettings======================\n");
            for (i = 1; i <= menuOptions.length; i++) {
                System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
            }
			choice = Integer.valueOf(sc.next());
			System.out.println();
            SettingsManager sm = new SettingsManager(this.adminID);
			
            switch (choice) {
				case 1:
                    sm.printHolidayList();
                    // Print Holiday List
                    break;
				case 2:
					// Create Holiday
                    sm.addHoliday();
					break;
                case 3:
                    sm.updateHoliday();
                    // Update Holiday  
                    break;
                case 4:
                    sm.deleteHoliday();
                    // Delete Holiday
                    break;
                case 5:
                    sm.printPriceList();
                    // Print Price List
                    break;
                case 6:
                    sm.editPrices();
                    // Edit Prices
                    break;
                case 7:
                    System.out.println("Exiting to the previous level...");
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 7);
    }
}
