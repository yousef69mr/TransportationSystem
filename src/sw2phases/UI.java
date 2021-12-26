package sw2phases;

public class UI {

    private TransportationSystem system;

    UI(TransportationSystem system){
        this.system=system;
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
    }

    void ShowAdminAccountMethodsMenu() {

        System.out.println("A:Show Admin Information ");
        System.out.println("B:Approve Driver");
        System.out.println("C:Show All Users");
        System.out.println("D:Suspend User");
        System.out.println("E: Reactivate a suspended User");
        System.out.println("F:Delete User ");

    }

    void ShowDriverAccountMethodsMenu() {

        System.out.println("A:Show Driver Information ");
        System.out.println("B:Show My Ratings ");
        System.out.println("C:Add Favourite Area ");
        System.out.println("D:Remove Favourite Area ");
        System.out.println("E:Show My Favourite Areas ");
        System.out.println("F:Make offer for My Rides ");
    }


}
