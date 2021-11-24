package sw2phases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TransportationSystem {
	private String systemName;
	private Administrator admin;
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
		
		users.add(a);
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
		r.setRideOwner(this);
	}
	public void removeRide(Ride r) {
		rides.remove(r);
		r.setRideOwner(null);
	}

	///////////////////////////
	
	
	//relation with Driver  class
		public void addDriver(Driver d) {
			drivers.add(d);
			users.add(d);
			d.setSystem(this);
		}
		public void removeDriver(Driver d) {
			drivers.remove(d);
			users.remove(d);
			d.setSystem(null);
		}
	
		//////////////////////////
		

		//relation with Client  class
			public void addClient(Client c) {
				clients.add(c);
				users.add(c);
				c.setSystem(this);
			}
			public void removeClient(Client c) {
				clients.remove(c);
				users.remove(c);
				c.setSystem(null);
			}
		
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
	
	ArrayList<Driver> getAllDrivers(){
		return drivers;
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
	
	
	Driver createDriverAccount(String name,String phone,String email,String pass,String id,String licence) {
		Driver d=new Driver(name,phone,email,pass,id,licence);
		if(d.isValidInput()) {
			if(!requests.contains(d)) {
				requests.add(d);
			}
			return d;
		}else {
			return null;
		}
	}
	
	Client createClientAccount(String name,String phone,String email,String pass) {
		Client c=new Client(name,phone,email,pass);
		if(c.isValidInput()) {
			if(!users.contains(c) ||!clients.contains(c)) {
				addUser(c);
				addClient(c);
			}
			return c;
		}
		else {
			return null;
		}
	}
		
	void suspendUser(Users u) {
		if(confirmed.contains(u)) {
			suspended.add(u);
			confirmed.remove(u);
			users.remove(u);
		}else {
			System.out.println("Account isn't existed");
		}
	}
	
	void returnFromSuspended(Users u) {
		if(suspended.contains(u)) {
			confirmed.add(u);
			suspended.remove(u);
			users.add(u);
		}else {
			System.out.println("Account isn't suspended");
		}
	}
	
	
	
	void displayDrivers() {
		for(int i=0;i<drivers.size();i++) {
			drivers.get(i).DisplayData();
		}
	}
	
	
	
	// login method 
	Driver getSpecificDriver(String name,String pass) {
		
		for(int i=0;i<drivers.size();i++){
			if(drivers.get(i).getName().equalsIgnoreCase(name)&&drivers.get(i).getPassword().equals(pass)) {
				return drivers.get(i);
			}
		}
		return null;
	}
	
	// login method 
		Client getSpecificClient(String name,String pass) {
			
			for(int i=0;i<clients.size();i++){
				if(clients.get(i).getName().equalsIgnoreCase(name)&&clients.get(i).getPassword().equals(pass)) {
					return clients.get(i);
				}
			}
			return null;
		}
		
		ArrayList<Ride> getRideSourceMatchesFavouriteAreaOfDriver(Driver drive,ArrayList<Ride> ride){
			ArrayList<Ride> selectedRides=new ArrayList<Ride>();
			
			for(int i=0;i<rides.size();i++) {
				if(drive.isFavourite(ride.get(i), drive.getFavouriteAreas())) {
					selectedRides.add(ride.get(i));
				}
			}
			return selectedRides;
		}
	
}
