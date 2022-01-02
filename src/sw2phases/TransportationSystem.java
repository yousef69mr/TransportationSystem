package sw2phases;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TransportationSystem {
	private String systemName;
	private Administrator admin;
	private UI ui;
	private static TransportationSystem uniqueSystem;
	private DataBase database;
	private SystemController systemController;


	
	private TransportationSystem(String n){
		
		setName(n);
		this.ui=new UI(this);
		admin =new Administrator(this);
		database=new DataBase(this);
		systemController=new SystemController(this);


	}
	
	public static TransportationSystem getInstance(String name) {
		if (uniqueSystem == null) {
			uniqueSystem = new TransportationSystem(name);
		}
		return uniqueSystem;
	}

	
	//relation with Users class
	public void addUser(Users a) {
		if(!a.getName().equals("")) {
			database.getAllUsers().add(a);
		}
		database.getAllConfirmed().add(a);
		
		a.setSystem(this);
	}
	public void removeUser(Users a) {

		database.getAllUsers().remove(a);
		database.getAllDeleted().add(a);
		
		a.setSystem(null);
	}
	////////////	
	
	//relation with Ride  class
	public void addRide(Ride r) {
		database.getAllRides().add(r);
		r.setRideSystem(this);
	}
	public void removeRide(Ride r) {
		database.getAllRides().remove(r);
		r.setRideSystem(null);
	}

	///////////////////////////
	
			//////////////////////////
	
	Administrator getAdmin() {
		return this.admin;
	}
	
	void setName(String n) {
		
		this.systemName=n;
		
	}
	
	
	String getName() {
		return this.systemName;
	}

	UI getUI(){
		return this.ui;
	}


	/*
	//function to create account for the driver
	Driver createDriverAccount(String name,String phone,String email,String pass,String id,String licence) {
		Driver d=new Driver(name,phone,email,pass,id,licence);
		d.setSystem(this);
		if(d.isValidInput()) {
		//	users.add(d);
			if(!requests.contains(d)) {
				requests.add(d);
			}
			return d;
		}else {
			return null;
		}
	}
	*/
	/*
	//function to create account for the client
	Client createClientAccount(String name,String phone,String email,String pass) {
		Client c=new Client(name,phone,email,pass);
		c.setSystem(this);
		if(c.isValidInput()) {
			if(!users.contains(c) ||!clients.contains(c)) {
				//addUser(c);
				admin.addClient(c);
			}
			return c;
		}
		else {
			return null;
		}
	}
		*/

	// login method
	Users getSpecificUser(String name,String pass) {
		ArrayList<Users> u =new ArrayList<>(database.getAllUsers());
		for(int i=0;i<u.size();i++){
			if(u.get(i).getName().equalsIgnoreCase(name)&&u.get(i).getPassword().equals(pass)) {
				return u.get(i);
			}
		}
		return null;
	}


//drivers then rides

	/*
	ArrayList<Ride> getRideSourceMatchesFavouriteAreaOfDriver(Driver drive,ArrayList<Ride> ride){
		ArrayList<Ride> selectedRides=new ArrayList<>();


		for(int i=0;i<database.getAllRides().size();i++) {
			if(drive.getRideController().isFavourite(ride.get(i), drive)) {
				selectedRides.add(ride.get(i));
			}
		}
		return selectedRides;
	}
*/
	DataBase getDatabase(){
		return this.database;
	}

	SystemController getSystemController(){
		return this.systemController;
	}
}
