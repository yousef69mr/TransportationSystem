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
			
			system.addDriver(d);
			system.getAllRequests().remove(d);
			
		}else if(system.getAllDrivers().contains(d)) {
			
			System.out.println("This Driver has already been approved");
		}else {
			System.out.println("This Driver didn't requested to join the System");
		}
	}
	
	void deleteSpecificDriver(Driver d) {
		if(system.getAllDrivers().contains(d)) {
			system.removeDriver(d);
		}else {
			System.out.println("Driver isn't existed");
		}
	}
	
	void suspendAccount(Users u) {
		system.suspendUser(u);
	}
	
	void deleteAccount(Users u) {
		system.removeUser(u);
	}
	
}
