package sw2phases;

public class Administrator {
	private String userName;
	private String password;
	private TransportationSystem system;
	
	Administrator(TransportationSystem system){
		this.system=system;
		setName("admin");
		setPassword("admin");
	}
	
	void setName(String n) {
		
		this.userName=n;
		
	}
	
	String getName() {
		return this.userName;
	}
	
	void setPassword(String pass) {
		
		this.password=pass;
		
	}
	
	String getPassword() {
		return this.password;
	}
	
	TransportationSystem getSystem() {
		return this.system;
	}
	
	// function to to add the drver in the system
	void verifyDriver(Driver d) {
		if(system.getAllRequests().contains(d)) {
			
			addDriver(d);

			system.getAllRequests().remove(d);
			System.out.println("This Driver has already been approved Successfully");

		}else if(system.getAllDrivers().contains(d)) {
			
			System.out.println("This Driver has already been approved before !!x");
		}else {
			System.out.println("This Driver didn't requested to join the System");
		}
	}
	
	//function to delete driver from system 
	void deleteSpecificDriver(Driver d) {
		if(system.getAllDrivers().contains(d)) {
			removeDriver(d);
		}else {
			System.out.println("Driver isn't existed");
		}
	}

	public void addDriver(Driver d) {
		system.getAllDrivers().add(d);
		system.getAllUsers().add(d);
		d.setSystem(getSystem());
	}

	public void removeDriver(Driver d) {
		system.getAllDrivers().remove(d);
		system.getAllUsers().remove(d);
		d.setSystem(null);
	}

	public void addClient(Client c) {
		system.getAllClients().add(c);
		system.getAllUsers().add(c);
		c.setSystem(getSystem());
	}

	public void removeClient(Client c) {
		system.getAllClients().remove(c);
		system.getAllUsers().remove(c);
		c.setSystem(null);
	}
	
	// function to suspend user account

	void suspendUser(Users u) {
		if (system.getAllConfirmed().contains(u)) {
			system.getAllSuspended().add(u);
			system.getAllConfirmed().remove(u);
			system.getAllUsers().remove(u);
			System.out.println("Account is suspended");
		} else {
			System.out.println("Account isn't existed");
		}
	}
           //function to return from suspend for user account
	void returnFromSuspended(Users u) {
		if (system.getAllSuspended().contains(u)) {
			system.getAllConfirmed().add(u);
			system.getAllSuspended().remove(u);
			system.getAllUsers().add(u);
			System.out.println("Account returned back online");
		} else {
			System.out.println("Account isn't suspended");
		}
	}

	void deleteAccount(Users u) {
		system.removeUser(u);
	}

	void DisplayData(){
		System.out.println("Name : "+getName());
		System.out.println("Password : "+getPassword());
	}
	
}
