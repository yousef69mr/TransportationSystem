package sw2phases;

import java.util.ArrayList;

public class RideData implements Subject{
    private ArrayList<Observer> observers;
    private Ride ride; //object that will be observed
    private ArrayList<Ride> rides;
    private ArrayList<String> offers;
    private int role;
    private float offer;

    RideData(){
        observers = new ArrayList<>();
        offers =new ArrayList<>();
        rides =new ArrayList<>();
    }

    RideData(Ride ride){
        observers = new ArrayList<>();
        this.ride=ride;
    }

    public Ride getRide(){
        return this.ride;
    }

    public void setRide(Ride ride){
        this.ride=ride;

       // notifyAllObservers();
    }

    public void setRole(int role) {
        this.role = role;
    }

    float getOffer(){
        return this.offer;
    }

    public void setOffer(float offer){
        this.offer=offer;

        notifyAllObservers();
    }


    public void setOfferWithoutNotify(float offer){
        this.offer=offer;

    }

    void displayOffers(){

        System.out.println("/**********************************/");
        System.out.println("Drivers Offers :");
        System.out.println("/**********************************/");
        for(int i=0;i<offers.size();i++){
            System.out.println(offers.get(i));
            System.out.println("/**********************************/");
        }
    }

    ArrayList<String> getOffers(){
        return this.offers;
    }

    void attachRide(Ride ride){
        rides.add(ride);
    }

    ArrayList<Ride> getRides(){
        return this.rides;
    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    public void unAttach(Observer observer){
        observers.remove(observer);
    }

    Ride search(float price){
        for(int i=0;i<rides.size();i++){
            if(rides.get(i).getRidePrice()==price){
                return rides.get(i);
            }
        }
        return null;
    }

    public void notifyAllObservers(){
        for (int i=0;i<observers.size();i++){
            //System.out.print(i+1+")");
            if(((Users)observers.get(i)).getType().equals("Client") && role==1) {

                observers.get(i).update(ride, offer);

            }else if(((Users)observers.get(i)).getType().equals("Driver")&&role==2){
                observers.get(i).update(ride, offer);
            }
        }
    }


}
