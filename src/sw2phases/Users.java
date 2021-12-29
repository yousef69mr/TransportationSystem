package sw2phases;

import java.util.ArrayList;

public abstract class  Users {
		
	private String userName;
	private String mobileNumber;
	private String email;
	private String password;
	protected String Type;
	protected Utility utility;
	protected RideController rideController;
	//private ArrayList<String> favouriteAreas;
	//private Account account;
	protected TransportationSystem system;
	
	//System.out.println("s");



	public void setSystem(TransportationSystem newSystem) {
		if (system != newSystem) {
			TransportationSystem old = system;
			system = newSystem;
			if (newSystem != null) {
				newSystem.addUser(this);
			}
			if (old != null) {
				old.removeUser(this);
			}
		}
	}
	
	void setName(String n) {
		
		this.userName=n;
		
	}
	
	String getName() {
		return this.userName;
	}
	
	void setPhoneNumber(String p) {
		if(p.length()==11) {
			this.mobileNumber=p;
		}else {
			System.out.println("Invalid Phone Number");
		}
	}
	
	String getPhoneNumber() {
		return this.mobileNumber;
	}
	
	void setEmail(String e) {
		if(e.contains("@")&&e.contains(".com")) {
			this.email=e;
		}else {
			System.out.println("Wrong Email Format");
		}
	}
	
	String getEmail() {
		return this.email;
	}
	
	void setPassword(String pass) {
		
		this.password=pass;
		
	}
	
	String getPassword() {
		return this.password;
	}
	/*
	void setAccount(){
		this.account=new Account(this);
	}
	
	Account getAccount() {
		return this.account;
	}
	*/
	abstract void setType();
	
	String getType() {
		return this.Type;
	}
	
	TransportationSystem getSystem() {
		return this.system;
	}

	boolean isValidInput() {
		if(getName()==null||getPhoneNumber()==null||getEmail()==null||getPassword()== null) {
			return false;
		}
		
		return true;
	}
	
	Utility getUtility(){
		return this.utility;
	}

	RideController getRideController(){
		return this.rideController;
	}
	// abstract Users login(String name,String pass);
	
	/*
	ArrayList<String> getFavouriteAreas() {
		return this.favouriteAreas;
	}
	
	
	void addFavouriteArea(String area) {
		if(!favouriteAreas.contains(area.toLowerCase())) {
			
			favouriteAreas.add(area.toLowerCase());
		}
	}
	
	void removeFavouriteArea(String area) {
		if(favouriteAreas.contains(area.toLowerCase())) {
			favouriteAreas.remove(area.toLowerCase());
		}
	}
	*/

	// login method for all users
	Users login(Users user) {
		if(user!=null){
			return user;
		}
		return null;
	}

	// sign up method for all users
	abstract Users signUp(Users user);
	abstract void DisplayAllData();
	
}
