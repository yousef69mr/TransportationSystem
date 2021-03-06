package sw2phases;


public class SystemController {
    private TransportationSystem system;

    SystemController(TransportationSystem system){
        this.system=system;
    }



    boolean isOnSystem(Ride ride){

        if(system.getDatabase().getAllRides().contains(ride)){
            return true;
        }

        return false;
    }
    

    /*********** Admin Functions **********/
    // function to to add the driver in the system
    void verifyDriver(Driver d) {
        if(system.getDatabase().getAllRequests().contains(d)) {

            addDriver(d);

            system.getDatabase().getAllRequests().remove(d);
            System.out.println("This Driver has already been approved Successfully");

        }else if(system.getDatabase().getAllDrivers().contains(d)) {

            System.out.println("This Driver has already been approved before !!x");
        }else {
            System.out.println("This Driver didn't requested to join the System");
        }
    }

    //function to delete driver from system
    void deleteSpecificDriver(Driver d) {
        if(system.getDatabase().getAllDrivers().contains(d)) {
            removeDriver(d);
        }else {
            System.out.println("Driver isn't existed");
        }
    }

    public void addDriver(Driver d) {
        system.getDatabase().getAllDrivers().add(d);
        system.getDatabase().getAllUsers().add(d);
        d.setSystem(getSystem());
    }

    public void removeDriver(Driver d) {
        system.getDatabase().getAllDrivers().remove(d);
        system.getDatabase().getAllUsers().remove(d);
        d.setSystem(null);
    }

    public void addClient(Client c) {
        system.getDatabase().getAllClients().add(c);
        system.getDatabase().getAllUsers().add(c);
        c.setSystem(getSystem());
    }

    public void removeClient(Client c) {
        system.getDatabase().getAllClients().remove(c);
        system.getDatabase().getAllUsers().remove(c);
        c.setSystem(null);
    }

    // function to suspend user account

    void suspendUser(Users u) {
        if (system.getDatabase().getAllConfirmed().contains(u)) {
            system.getDatabase().getAllSuspended().add(u);
            system.getDatabase().getAllConfirmed().remove(u);
            system.getDatabase().getAllUsers().remove(u);
            System.out.println("Account is suspended");
        } else {
            System.out.println("Account isn't existed");
        }
    }
    //function to return from suspend for user account
    void returnFromSuspended(Users u) {
        if (system.getDatabase().getAllSuspended().contains(u)) {
            system.getDatabase().getAllConfirmed().add(u);
            system.getDatabase().getAllSuspended().remove(u);
            system.getDatabase().getAllUsers().add(u);
            System.out.println("Account returned back online");
        } else {
            System.out.println("Account isn't suspended");
        }
    }

    void deleteAccount(Users u) {
        system.removeUser(u);
    }

    void displayRides(){
        System.out.println("System Rides :");

        for(int i=0;i<system.getDatabase().getAllRides().size();i++){
            system.getDatabase().getAllRides().get(i).displayRideData();
            System.out.println("/************************/");
        }
    }


    public void addDiscountForArea(String area) {

        system.getDatabase().getAreasDiscount().addArea(area.toLowerCase());
    }

    public void removeDiscountForArea(String area) {
        if(system.getDatabase().getAreasDiscount().getAreas().contains(area.toLowerCase())) {
            system.getDatabase().getAreasDiscount().removeArea(area);
        }
    }

    Ride getSpecificRide(Ride ride){
        for(int i=0;i<system.getDatabase().getAllRides().size();i++){
            if(system.getDatabase().getAllRides().get(i).equals(ride)){
                return system.getDatabase().getAllRides().get(i);
            }
        }
        return null;
    }

    TransportationSystem getSystem(){
        return this.system;
    }
}
