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
	
	public Driver(String name,String phone,String email,String pass,String id,String licence) {
		setName(name);
		setEmail(email);
		setPhoneNumber(phone);
		setPassword(pass);
		setNationalID(id);
		setDriverLicence(licence);
		
		setType();
		
		//setAccount();
		this.utility=new Utility(this);
		favouriteAreas =new ArrayList<String>();
		rides = new HashSet<Ride>();
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
			Driver d=new Driver(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), ((Driver) user).getNationalID(), ((Driver) user).getDriverLicence());
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

	/*
       
	Driver login(String name, String pass) {
		if(!super.getSystem().getAllSuspended().contains(super.getSystem().getSpecificClient(name, pass))) {
			
			if(super.getSystem().getSpecificDriver(name,pass)!=null) {
				return super.getSystem().getSpecificDriver(name, pass);
			}else {
				System.out.println("UserName or Password are incorrect");
				return null;
			}
		}else {
			System.out.println("Your Account is Suspended !!");
			return null;
		}
	}
	*/

	////////////////
	 /*
	void setPriceForSpecificRide(float price,int rideNum) {
		rideController.showRideSourceMatchesFavouriteAreaOfDriver();
		ArrayList<Ride> selectedRides=super.getSystem().getRideSourceMatchesFavouriteAreaOfDriver(this,super.getSystem().getDatabase().getAllRides());
		for(int i=0;i<selectedRides.size();i++) {
			if(selectedRides.get(i).getRideNumber()==rideNum) {
				selectedRides.get(i).setRidePrice(price);
			}
		}
		
	}
	*/
	void displayRatings() {
		ArrayList<Ratings> r=new ArrayList<Ratings>(rates);
		
		for(int i=0;i<r.size();i++) {
			r.get(i).displayRatingData();
		}
	}

	void displayRidesList() {
		ArrayList<Ride> r=new ArrayList<Ride>(rides);
		System.out.println("\nRides List:");

		for(int i=0;i<r.size();i++) {
			System.out.println(i+1+")"+" Ride No.: "+r.get(i).getRideNumber()+"\n"+r.get(i).getSource()+"-->"+r.get(i).getDestination()+" Client : "+r.get(i).getClient().getName() );
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

}
