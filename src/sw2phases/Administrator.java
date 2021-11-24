package sw2phases;

public class Administrator {
	private String userName;
	private String password;
	private TransportationSystem system;
	
	Administrator(TransportationSystem system){
		this.system=system;
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
	
	void verifyDriver(Driver d) {
		if(system.getAllRequests().contains(d)) {
			
			addDriver(d);
			system.getAllRequests().remove(d);
			
		}else if(system.getAllDrivers().contains(d)) {
			
			System.out.println("This Driver has already been approved");
		}else {
			System.out.println("This Driver didn't requested to join the System");
		}
	}
	
	void deleteSpecificDriver(Driver d) {
		if(system.getAllDrivers().contains(d)) {
			removeDriver(d);
		}else {
			System.out.println("Driver isn't existed");
		}
	}

	void suspendAccount(Users u) {
		suspendUser(u);
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

	void suspendUser(Users u) {
		if (system.getAllConfirmed().contains(u)) {
			system.getAllSuspended().add(u);
			system.getAllConfirmed().remove(u);
			system.getAllUsers().remove(u);
		} else {
			System.out.println("Account isn't existed");
		}
	}

	void returnFromSuspended(Users u) {
		if (system.getAllSuspended().contains(u)) {
			system.getAllConfirmed().add(u);
			system.getAllSuspended().remove(u);
			system.getAllUsers().add(u);
		} else {
			System.out.println("Account isn't suspended");
		}
	}

	void deleteAccount(Users u) {
		system.removeUser(u);
	}
	
}
