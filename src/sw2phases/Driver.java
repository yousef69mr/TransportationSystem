package sw2phases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Driver extends Users {

	private String driverLicence;
	private String nationalID;
	private ArrayList<String> favouriteAreas;
	private double averageRatings;
	private Set<Ride> rides;
	private ArrayList<Ride> confirmedRides;
	private Set<Ratings> rates;
	
	
	Driver(){
		setName("");
		setPassword("");
		this.nationalID="";
		setDriverLicence("");

		setType();
		this.utility=new Utility(this);
		//setAccount();
	}
	
	public Driver(String name,String phone,String email,String pass,String id,String licence,TransportationSystem system) {
		setName(name);
		setEmail(email);
		setPhoneNumber(phone);
		setPassword(pass);
		setNationalID(id);
		setDriverLicence(licence);
		
		setType();

		setRideData();

		setSystem(system);
		//setAccount();
		this.systemController=new SystemController(getSystem());
		this.utility=new Utility(this);
		favouriteAreas =new ArrayList<String>();
		rides = new HashSet<Ride>();
		confirmedRides= new ArrayList<Ride>();
		rates = new HashSet<Ratings>();
	}
	
	//relation with Ride class
	public void addRide(Ride a) {
		rides.add(a);
		a.setDriver(this);
	}
	public void removeRide(Ride a) {
		rides.remove(a);
		a.setDriver(null);
	}
	///////////////////////////
	
	//relation with Ratings class
	public void addRating(Ratings a) {
		rates.add(a);
		a.setDriver(this);
	}
	public void removeRating(Ratings a) {
		rates.remove(a);
		a.setDriver(null);
	}

	/////////////////

	void setRideController(Ride ride){
		this.rideController=new RideController(ride,this);
	}

	void setDriverLicence(String licence) {
		
		this.driverLicence=licence;
		
	}
	
	String getDriverLicence() {
		return this.driverLicence;
	}
	
	void setNationalID(String id) {
		
		if(id.length()==14) {
			this.nationalID=id;
		}else {
			System.out.println("Invalid National ID !!");
		}
		
	}
	
	String getNationalID() {
		return this.nationalID;
	}
	/*
	void setOffer(float o) {
		
		this.offer=o;
		
	}
	
	float getOffer() {
		return this.offer;
	}
	*/

	void setConfirmedRides(Ride ride){
		confirmedRides.add(ride);
	}
	
	void displayRideHistory(){

		System.out.println("/**********************************/");
		System.out.println("My Rides History :");
		System.out.println("/**********************************/");
		for(int i=0;i<confirmedRides.size();i++){
			System.out.println(i+1+")"+" Ride No.: "+confirmedRides.get(i).getRideNumber()+"\n"+confirmedRides.get(i).getSource()+"-->"+confirmedRides.get(i).getDestination()+" Client : "+confirmedRides.get(i).getClient().getName());
			System.out.println("Confirmation Time : "+confirmedRides.get(i).getRequestedTime());
			System.out.println("/**********************************/");

		}
	}


	@Override
	//Display the Driver Data
	void DisplayAllData() {
		System.out.println("Name : "+super.getName());
		System.out.println("Email : "+super.getEmail());
		System.out.println("Password : "+super.getPassword());
		System.out.println("Phone Number : "+super.getPhoneNumber());
		System.out.println("Licence Number : "+ getDriverLicence());
		System.out.println("National ID : "+getNationalID());
		System.out.println("Account Type : "+super.getType());
		System.out.println("Average Ratings : "+ getAverageRatings());
		
		displayFavouriteAreas();
	}

	void DisplaySpecificData() {
		System.out.println("Driver's Name : "+super.getName());
		System.out.println("Phone Number : "+super.getPhoneNumber());
		System.out.println("Licence Number : "+ getDriverLicence());
		//System.out.println("Account Type : "+super.getType());
		System.out.println("Average Ratings : "+ getAverageRatings());

		displayFavouriteAreas();
	}


	@Override
	void setType() {
		Type="Driver";
	}
	
	Set<Ride> getAllRides(){
		return rides;
	}

	ArrayList<Ride> getAllConfirmedRides(){
		return confirmedRides;
	}

	Set<Ratings> getAllRatings(){
		return rates;
	}
	
	
	ArrayList<String> getFavouriteAreas() {
		return this.favouriteAreas;
	}
	
	
	void addFavouriteArea(String area) {
		if(!favouriteAreas.contains(area.toLowerCase())) {
			
			favouriteAreas.add(area.toLowerCase());
			System.out.println("Area is added successfully");
		}
	}
	
	void removeFavouriteArea(String area) {
		if(favouriteAreas.contains(area.toLowerCase())) {

			favouriteAreas.remove(area.toLowerCase());
			System.out.println("Area is removed successfully");
		}
	}
	
	
	
	
	void displayFavouriteAreas() {
		System.out.print("Favourite Areas : ");

		for(int i=0;i<favouriteAreas.size();i++) {
			if(i==favouriteAreas.size()-1) {
				System.out.println(favouriteAreas.get(i));
			}else {
				System.out.print(favouriteAreas.get(i)+" -- ");
			}
		}
	}
	
	
	boolean isValidInput() {
		
		if(super.isValidInput()) {
			if(getDriverLicence()==null||getNationalID()==null) {
				return false;
			}
		}
		return true;
	}


	@Override
	Users signUp(Users user) {
		if(user.getClass().equals(Driver.class)){
			Driver d=new Driver(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), ((Driver) user).getNationalID(), ((Driver) user).getDriverLicence(),user.getSystem());
			d.setSystem(user.getSystem());
			if(d.isValidInput()) {
				//	users.add(d);
				if(!d.getSystem().getDatabase().getAllRequests().contains(d)) {
					d.getSystem().getDatabase().getAllRequests().add(d);
				}
				return d;
			}else {
				return null;
			}
		}
		return null;
	}
	void displayRatings() {
		ArrayList<Ratings> r=new ArrayList<Ratings>(rates);
		
		for(int i=0;i<r.size();i++) {
			r.get(i).displayRatingData();
		}
	}

	void fixPendingRides(){
		int i;
		ArrayList<Ride> r=new ArrayList<>(rides);

		for(i=0;i<r.size();i++) {
			if(r.get(i).getRequestedTime()!=null){
				rides.remove(r.get(i));
			}
		}
	}

	void displayRidesList() {
		int i;
		ArrayList<Ride> r=new ArrayList<>(rides);

		System.out.println("/**********************************/");
		System.out.println("Rides List:");
		System.out.println("/**********************************/");
		for(i=0;i<r.size();i++) {
			System.out.println(i+1+")"+" Ride No.: "+r.get(i).getRideNumber()+"\n"+r.get(i).getSource()+"-->"+r.get(i).getDestination()+" Client : "+r.get(i).getClient().getName() );
			System.out.println("Number Of Passengers : "+r.get(i).getNumOfPassengers());
			System.out.println("/**********************************/");
		}
	}

	public double getAverageRatings() {
		return this.averageRatings;
	}

	public void setAverageRatings() {
		ArrayList<Ratings> r=new ArrayList<>(rates);
		int sum=0;
		
		for(int i=0;i<r.size();i++) {
			sum+=r.get(i).getRate();
		}
		double average=sum/r.size();
		
		this.averageRatings = average;
	}



	Ride getSpecificRide(int choice){
		ArrayList<Ride> rides=new ArrayList<>(getAllRides());

		for(int i=0;i<rides.size();i++){
			if(i==choice){
				return rides.get(i);
			}
		}

		return null;
	}

	boolean isActive(){
		for(int i=0;i<getAllConfirmedRides().size();i++){
			if(getAllConfirmedRides().get(i).getReachedDestinationTime()==null){
				return true;
			}
		}
		return false;
	}


	@Override
	public void update(Ride ride,float price) {

		if(!system.getDatabase().getAllRides().contains(ride)){

			if(price!=0) {
				ArrayList<Ride> rides = new ArrayList<>(ride.getDriver().getAllRides());

				for (int i = 0; i < rides.size(); i++) {
					if (getUtility().match(ride, rides)) {

						rides.get(i).setRidePrice(price);

						//System.out.println(ride.getDriver().getType() + " : " + ride.getDriver().getName() + " offers " + price);
						ride.getDriver().getRideData().getOffers().add(ride.getDriver().getType() + " : " + ride.getDriver().getName() + " offers " + price);

						ride.getDriver().getRideData().attachRide(rides.get(i));
						rides.get(i).displayRideData();

						ride.getDriver().getRideData().setOfferWithoutNotify(0f);
					}
				}
			}

		}

	}
}
