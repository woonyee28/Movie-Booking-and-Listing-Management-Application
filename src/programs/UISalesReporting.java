package programs;

import java.util.Scanner;

import managers.SalesManager;

public class UISalesReporting {
    /**
     * The adminOrmember of this UISalesReporting.
     * Admin=1,Member=0,Guest=-1.
     */
    private int adminOrmember; // 1 == admin, 0 == member, -1 == Guest
    /**
     * The id of this UISalesReporting.
     */
    private int id; // -1 == Member
    /**
     * The displaySortByRating of this UISalesReporting.
     * If value ==1, DisplaySortByRating is not hidden.
     */
    public static int displaySortByRating = 1;
    /**
     * The displaySortBySales of this UISalesReporting.
     * If value ==1, DisplaySortBySales is not hidden.
     */
    public static int displaySortBySales = 1;
    /**
     * The displayReview of this UISalesReporting.
     * If value ==1, DisplayReview is not hidden.
     */
    public static int displayReview = 1;
    /**
     * Creates a new UISalesReporting with give id and adminOrmember.
     * @param id This UISalesReporting's id.
     * @param adminOrmember This UISalesReporting's adminOrmember.
     */
    public UISalesReporting(int id, int adminOrmember){
        this.id = id;
        this.adminOrmember = adminOrmember;
    }

    /**
     * The menuOptions of this UISalesReporting.
     */
    protected String menuOptions[] = {
        "Sort By Rating",
        "Sort By Sales",
        "Show Reviews",
        "Exit Sales Reporting",
        "Edit MovieGoers Permission"
    };
    /**
     * The adminOptions of this UISalesReporting.
     */
    protected String adminOptions[] = {
        "Hide SortByRating",
        "Hide SortBySales",
        "Hide Review",
        "Display SortByRating",
        "Display SortBySales",
        "Display Review",
        "Done and Exit"
    };
    /**
     * Runs UISalesReporting.
     */
    public void run(){
        int choice = -1;

        Scanner sc = new Scanner(System.in);
        if (adminOrmember==-1){
            adminOrmember=0; //same permission for member and guest
        }
        do{
            System.out.println("====================UI Sales Reporting======================");
            int i;
            if (adminOrmember==1 || (adminOrmember==0 && displaySortByRating==1)){
                System.out.printf("(%d) %s \n", 1, menuOptions[0]);
            }
            if (adminOrmember==1 || (adminOrmember==0 && displaySortBySales==1)){
                System.out.printf("(%d) %s \n", 2, menuOptions[1]);
            }
            if (adminOrmember==1 || (adminOrmember==0 && displayReview==1)){
                System.out.printf("(%d) %s \n", 3, menuOptions[2]);
            }
            System.out.printf("(%d) %s \n", 4, menuOptions[3]);
            if (adminOrmember==1){
                System.out.printf("(%d) %s \n", 5, menuOptions[4]);
            }
            choice = Integer.valueOf(sc.next());
			SalesManager sm = new SalesManager(this.id, this.adminOrmember);
            switch (choice) {
				case 1:
                    if (adminOrmember==0 && displaySortByRating==0){
                        System.out.println("Please input a valid option.");
                    }else{
                        System.out.println("sortByRating():");
                        sm.sortByRating();
                    }
					break;
				case 2:
                    if (adminOrmember==0 && displaySortBySales==0){
                        System.out.println("Please input a valid option.");
                    }else{
                        System.out.println("sortBySales():");
                        sm.sortBySales();
                    }
					break;
                case 3:
                    if (adminOrmember==0 && displayReview==0){
                        System.out.println("Please input a valid option.");
                    }else{
                        System.out.println("showReview()");
                        sm.showReview();
                    }
                    break;
				case 4:
					System.out.println("Program exiting...");
					break;
                case 5:
                    if (adminOrmember==1){
                        do{
                            System.out.println("Please input your preferred change: ");
                            for (i = 1; i <= adminOptions.length; i++) {
                                System.out.printf("(%d) %s \n", i, adminOptions[i-1]);
                            }
                            choice = Integer.valueOf(sc.next());
                            switch(choice){
                                case 1:
                                    displaySortByRating = 0;
                                    System.out.println("SortByRating has been hidden for MovieGoer and Guest! ");
                                    break;
                                case 2:
                                    displaySortBySales = 0;
                                    System.out.println("SortBySales has been hidden for MovieGoer and Guest! ");
                                    break;
                                case 3:
                                    displayReview = 0;
                                    System.out.println("Review has been hidden for MovieGoer and Guest! ");
                                    break;
                                case 4:
                                    displaySortByRating = 1;
                                    System.out.println("SortByRating has been shown for MovieGoer and Guest! ");
                                    break;
                                case 5:
                                    displaySortBySales = 1;
                                    System.out.println("SortBySales has been shown for MovieGoer and Guest! ");
                                    break;
                                case 6:
                                    displayReview = 1;
                                    System.out.println("Review has been shown for MovieGoer and Guest! ");
                                    break;
                                case 7:
                                    System.out.println("Program exiting...");
                                    break;
                                default:
                                    System.out.println("Please input a valid option.");
                                    break;
                            }
                        }while (choice!=7);
                    }else{
    					System.out.println("Please input a valid option.");
                    }
                    break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
        }while (choice != 4);
        ;
    }
}
