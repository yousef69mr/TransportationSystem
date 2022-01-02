package sw2phases;

public class UI {

    private TransportationSystem system;

    UI(TransportationSystem system){
        this.system=system;
    }

    void ShowWelcomeMenu(){

        System.out.println("*****************************************************");
        System.out.println("****    Welcome to "+system.getName()+" Transportation System    ****");
        System.out.println("*****************************************************");

    }


    void ShowMainMenu() {

        System.out.print("A:Create a new account              ");
        System.out.println("B: Existing Account");
    }

    void ShowUserTypeMenu() {

        System.out.print("A:Driver              ");
        System.out.println("B:Client");

    }


    void ShowClientAccountMethodsMenu() {

        System.out.println("A:Show Client Information ");
        System.out.println("B:Request Ride ");
        System.out.println("C:Rate a Driver ");
        System.out.println("D:Show All Ratings of a Driver ");
        System.out.println("E:Delete Account ");
        System.out.println("F:Show Driver Offers ");
    }

    void ShowAdminAccountMethodsMenu() {

        System.out.println("A:Show Admin Information ");
        System.out.println("B:Approve Driver");
        System.out.println("C:Show All Users");
        System.out.println("D:Suspend User");
        System.out.println("E: Reactivate a suspended User");
        System.out.println("F:Delete User ");
        System.out.println("G:Show All Drivers ");
        System.out.println("H:Show All Rides ");
        System.out.println("I:Add Discount For Specific Area ");

    }

    void ShowDriverAccountMethodsMenu() {

        System.out.println("A:Show Driver Information ");
        System.out.println("B:Show My Ratings ");
        System.out.println("C:Add Favourite Area ");
        System.out.println("D:Remove Favourite Area ");
        System.out.println("E:Show My Favourite Areas ");
        System.out.println("F:Make offer for My Rides ");
        System.out.println("G:Show My Rides' History ");
        System.out.println("H:Show My Active Ride ");
    }

    void ShowDriverActiveRideMethodsMenu(){
        System.out.println("A:Reached Pick Up Location  ");
        System.out.println("B:Reached Destination Location ");
        System.out.println("C:Exit ");
    }


    void loginFaluireMenu(){
        System.out.println("Incorrect Name or Password ");
        System.out.println("A:Try Again ");
        System.out.println("B:Exit ");
    }
/*
    void ShowRequestRideMenu(){
        System.out.println("A:Show Driver Information ");
        System.out.println("B:Show My Ratings ");
    }

 */
}
