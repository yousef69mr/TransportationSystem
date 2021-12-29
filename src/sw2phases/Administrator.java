package sw2phases;

public class Administrator {
	private String userName;
	private String password;
	private TransportationSystem system;
	private SystemController systemController;
	
	Administrator(TransportationSystem system){
		this.system=system;
		this.systemController=new SystemController(getSystem());
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

	SystemController getSystemController(){
		return this.systemController;
	}

	void DisplayData(){
		System.out.println("Name : "+getName());
		System.out.println("Password : "+getPassword());
	}


}
