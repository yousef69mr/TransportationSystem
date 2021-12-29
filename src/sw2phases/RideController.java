package sw2phases;

import java.util.ArrayList;

public class RideController {

    private Ride ride;
    private Users user;

    RideController(Ride ride,Users user){
        this.user=user;
        this.ride=ride;
    }

    Driver getDriverMatchesItsFavouriteArea() {

        if(!user.getSystem().getDatabase().getAllDrivers().isEmpty()) {
            for(int i=0;i<user.getSystem().getDatabase().getAllDrivers().size();i++) {


                for(int j=0;j<user.getSystem().getDatabase().getAllDrivers().get(i).getFavouriteAreas().size();j++) {

                    if(isFavourite(ride, user.getSystem().getDatabase().getAllDrivers().get(i))) {
                        return user.getSystem().getDatabase().getAllDrivers().get(i);
                    }
                }

            }
        }

        return null;
    }


    void requestRide(Client c,String src,String dest) {
        ride=new Ride(c,src,dest);

        ride.setRideSystem(c.getSystem());

        if(ride.getDriver()!=null){
            ((Client)user).setRide(ride);
            user.getSystem().addRide(ride);
            ride.getDriver().addRide(ride);

            ride.getDriver().setRideController(ride);
            System.out.print("The Request is Completed Successfully\n/******************************/\nDriver Info :\n");
            ride.getDriver().DisplaySpecificData();
            System.out.print("/******************************/");
        }else{
            System.out.print("No available drivers in your area\n");
        }

    }


    //check if the Entry area is favourite area for the driver
    boolean isFavourite(Ride r, Driver driver) {

        for(int i=0;i<driver.getFavouriteAreas().size();i++) {
            if(driver.getFavouriteAreas().get(i).equalsIgnoreCase(r.getSource())) {
                return true;
            }
        }

        return false;
    }


    //***************************** Driver Functions ********************************//

    // show source Ride that matches with favourite area of Driver
    void showRideSourceMatchesFavouriteAreaOfDriver() {
        ArrayList<Ride> selectedRides=user.getSystem().getRideSourceMatchesFavouriteAreaOfDriver((Driver) user,user.getSystem().getDatabase().getAllRides());
        for(int i=0;i<selectedRides.size();i++) {
            selectedRides.get(i).displayRideData();
        }
    }



    ////////////////////////
    void setPriceForSpecificRide(float price,Ride ride) {
        showRideSourceMatchesFavouriteAreaOfDriver();
        ride.setRidePrice(price);
    }
}
