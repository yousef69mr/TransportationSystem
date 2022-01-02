package sw2phases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RideController {

    private Ride ride;
    private Users user;
    private RideData rideData;

    RideController(Ride ride,Users user){
        this.user=user;
        this.ride=ride;
        this.rideData=new RideData(ride);
    }



    ArrayList<Driver> getAllDriversMatchesItsFavouriteArea(Client c,String src) {

        ArrayList<Driver> matchedDrivers =new ArrayList<>();

        ride = new Ride(c, src);

        if(!user.getSystem().getDatabase().getAllDrivers().isEmpty()) {

            for(int i=0;i<user.getSystem().getDatabase().getAllDrivers().size();i++) {


                for(int j=0;j<user.getSystem().getDatabase().getAllDrivers().get(i).getFavouriteAreas().size();j++) {

                    if(isFavourite(ride, user.getSystem().getDatabase().getAllDrivers().get(i))) {


                        if (!user.getSystem().getDatabase().getAllDrivers().get(i).isActive()) {
                            matchedDrivers.add(user.getSystem().getDatabase().getAllDrivers().get(i));
                        }

                    }
                }

            }

            return matchedDrivers;
        }

        return null;
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

    Ride getActiveRide(Driver driver){
        for(int i=0;i<driver.getAllConfirmedRides().size();i++){
            if(driver.getAllConfirmedRides().get(i).getReachedDestinationTime()==null){
                return driver.getAllConfirmedRides().get(i);
            }
        }
        return null;
    }

    void applyDiscounts(Ride ride){


        /********************************** Area Discount *******************************************/

        if(user.getSystem().getDatabase().getAreasDiscount().matchSource(ride)){
            ride.getDiscounts().add(user.getSystem().getDatabase().getAreasDiscount());
        }

        if(user.getSystem().getDatabase().getAreasDiscount().matchDestination(ride)){
            ride.getDiscounts().add(user.getSystem().getDatabase().getAreasDiscount());
        }

        if(ride.getClient().isFirstRide()){

            ride.getDiscounts().add(new FirstRideDiscount(new BasicDiscount()));
        }
        /********************************** BirthDay Discount *******************************************/
        //
        String day=ride.getClient().getBirthDay().getDay();
        String month=ride.getClient().getBirthDay().getMonth();

        //Date birthday=new Date(Integer.parseInt(ride.getClient().getBirthDay().getYear()),Integer.parseInt(ride.getClient().getBirthDay().getMonth()),Integer.parseInt(ride.getClient().getBirthDay().getDay()));
        Date today=new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

       // int todayMonth=calendar.get(Calendar.MONTH);
       // int todayDay=calendar.get(Calendar.DAY_OF_MONTH);

        if(calendar.get(Calendar.DAY_OF_MONTH)==Integer.parseInt(day) && calendar.get(Calendar.MONTH)+1==Integer.parseInt(month)){
            ride.getDiscounts().add(new BirthDayDiscount(new BasicDiscount()));
            System.out.println("Happy BirthDay  :)");
        }

        /********************************** Holiday Discount *******************************************/

        if(calendar.get(Calendar.DAY_OF_WEEK)==5||calendar.get(Calendar.DAY_OF_WEEK)==6){
            ride.getDiscounts().add(new HolidayDiscount(new BasicDiscount()));
        }

        /********************************** Passenger Discount *******************************************/

        if(ride.getNumOfPassengers()>=2){
            ride.getDiscounts().add(new ExtraPassengersDiscount(new BasicDiscount()));
        }


    }

/*
    void requestRide(Client c,String src,String dest) {
        ride=new Ride(c,src,dest);

        ride.setRideSystem(c.getSystem());

        if(ride.getDriver()!=null){
            ((Client)user).setRide(ride);
            user.getSystem().addRide(ride);
            ride.getDriver().addRide(ride);

            ride.getDriver().setRideController(ride);
       //     System.out.print("The Request is Completed Successfully\n//");
            ride.getDriver().DisplaySpecificData();
            System.out.print("//");
        }else{
            System.out.print("No available drivers in your area\n");
        }

    }
    */


    void createPotintialRides(Client c, String src, String dest, ArrayList<Driver> matched,int numOfPassengers){
        int i;

        RideData rideData =new RideData();

        if(!matched.isEmpty()) {
            ArrayList<Ride> rides = new ArrayList<>();
            for (i = 0; i < matched.size(); i++) {
                rides.add(new Ride(c, matched.get(i), src, dest,numOfPassengers));
            }


            //ride.setRideSystem(c.getSystem());
            for (i = 0; i < matched.size(); i++) {
                if (rides.get(i).getDriver() != null) {
                    //last ride for client
                    //((Client) user).setRide(rides.get(i));
                    //user.getSystem().addRide(ride);
                    rides.get(i).getDriver().addRide(rides.get(i));

                    rides.get(i).getDriver().setRideController(rides.get(i));
                    rides.get(i).getDriver().setRideData(rideData);
                    rides.get(i).getDriver().getRideData().setRide(rides.get(i));

                    if(!rides.get(i).getClient().getRideData().equals(rideData)) {
                        rides.get(i).getClient().setRideData(rideData);
                    }

                    System.out.println("Potintial Driver Info :");
                    System.out.println("/******************************/");
                    System.out.println(rides.get(i).getDriver().getName());
                    System.out.println("/******************************/");

                } else {
                    System.out.print("No available drivers in your area\n");
                }
            }
        }else{
            System.out.print("No available drivers in your area\n");
        }


    }
/*
    void requestRide(Client c,String src,String dest) {

        ride = new Ride(c, src, dest);

        ride.setRideSystem(c.getSystem());

        if(ride.getDriver()!=null){
            ((Client)user).setRide(ride);
            user.getSystem().addRide(ride);
            ride.getDriver().addRide(ride);

            ride.getDriver().setRideController(ride);
            System.out.print("The Request is Completed Successfully\n/////////////////\nDriver Info :\n");
            ride.getDriver().DisplaySpecificData();
            System.out.print("///////////////");
        }else{
            System.out.print("No available drivers in your area\n");
        }

    }
*/

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
/*
    void setPriceForSpecificRide(float price,Ride ride) {
        ride.setRidePrice(price);
    }
*/
/*
    // show source Ride that matches with favourite area of Driver
    void showRideSourceMatchesFavouriteAreaOfDriver(Client c,String src) {

        ArrayList<Driver> drivers=getAllDriversMatchesItsFavouriteArea(c,src);

        ArrayList<Ride> selectedRides=user.getSystemController().getRidesSourceMatchesFavouriteAreaOfDrivers(drivers,ride);
        for(int i=0;i<selectedRides.size();i++) {
            selectedRides.get(i).displayRideData();
        }
    }



    ////////////////////////
    void setPriceForSpecificRide(float price,Ride ride) {
        showRideSourceMatchesFavouriteAreaOfDriver(ride.getClient(),ride.getSource());
        ride.setRidePrice(price);
    }

 */
}
