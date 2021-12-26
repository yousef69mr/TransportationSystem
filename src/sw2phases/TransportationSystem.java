package sw2phases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TransportationSystem {
	private String systemName;
	private Administrator admin;
	private UI ui;
	private static TransportationSystem uniqueSystem;
	private Set<Users> users;
	
	private ArrayList<Driver> drivers;
	private ArrayList<Client> clients;
	private ArrayList<Ride> rides;
	
	private ArrayList<Users> suspended;
	private ArrayList<Users> requests;
	private ArrayList<Users> confirmed;
	private ArrayList<Users> deleted;
	
	private TransportationSystem(String n){
		
		setName(n);
		this.ui=new UI(this);
		admin =new Administrator(this);
		users = new HashSet<Users>();
		drivers = new ArrayList<Driver>();
		clients = new ArrayList<Client>();
		rides = new ArrayList<Ride>();
		suspended=new ArrayList<Users>();
		requests=new ArrayList<Users>();
		confirmed=new ArrayList<Users>();
		deleted=new ArrayList<Users>();
		
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
			users.add(a);
		}
		confirmed.add(a);
		
		a.setSystem(this);
	}
	public void removeUser(Users a) {
		
		users.remove(a);
		deleted.add(a);
		
		a.setSystem(null);
	}
	////////////	
	
	//relation with Ride  class
	public void addRide(Ride r) {
		rides.add(r);
		r.setRideSystem(this);
	}
	public void removeRide(Ride r) {
		rides.remove(r);
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

	ArrayList<Driver> getAllDrivers(){
		return drivers;
	}

	ArrayList<Client> getAllClients(){
		return clients;
	}

	Set<Users> getAllUsers(){
		return users;
	}

	ArrayList<Users> getAllRequests(){
		return requests;
	}
	
	ArrayList<Users> getAllConfirmed(){
		return confirmed;
	}
	
	ArrayList<Users> getAllSuspended(){
		return suspended;
	}
	
	ArrayList<Ride> getAllRides(){
		return rides;
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
	void displayDrivers() {
		for(int i=0;i<drivers.size();i++) {
			drivers.get(i).DisplayAllData();
		}
	}

	void displayUsers() {
		ArrayList<Users> user=new ArrayList<Users>(users);
/*
		for(int i=0;i<user.size();i++) {

			if(user.get(i).getName().equalsIgnoreCase("")){
				user.remove(user.get(i));
			}

		}
*/
		System.out.println("\nActive Users:");

		for(int i=0;i<user.size();i++) {
			
			System.out.println(i+1+")"+user.get(i).getName());

		}
	}

	void showPendingDrivers() {

		System.out.println("\nPending Drivers:");

		for(int i=0;i<requests.size();i++) {
			System.out.println(i+1+")"+requests.get(i).getName());
		}
	}

	void showSuspendedUsers() {
		System.out.println("\nSuspended Drivers:");

		for(int i=0;i<suspended.size();i++) {
			System.out.println(i+1+")"+suspended.get(i).getName());
		}
	}

	// login method
	Users getSpecificUser(String name,String pass) {
		ArrayList<Users> u =new ArrayList<Users>(users);
		for(int i=0;i<u.size();i++){
			if(u.get(i).getName().equalsIgnoreCase(name)&&u.get(i).getPassword().equals(pass)) {
				return u.get(i);
			}
		}
		return null;
	}


	ArrayList<Ride> getRideSourceMatchesFavouriteAreaOfDriver(Driver drive,ArrayList<Ride> ride){
		ArrayList<Ride> selectedRides=new ArrayList<Ride>();

		for(int i=0;i<rides.size();i++) {
			if(drive.getRideController().isFavourite(ride.get(i), drive)) {
				selectedRides.add(ride.get(i));
			}
		}
		return selectedRides;
	}


}
