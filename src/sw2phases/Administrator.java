package sw2phases;

public class Administrator extends Users{

	private SystemController systemController;
	
	Administrator(TransportationSystem system){
		this.system=system;
		this.systemController=new SystemController(getSystem());
		setName("admin");
		setPassword("admin");
		setEmail("admin@admin.com");
		setPhoneNumber("01234569877");
		setType();
	}


	@Override
	void setType() {
		Type="Administrator";
	}

	@Override
	Users signUp(Users user) {
		return null;
	}

	@Override
	void DisplayAllData() {
		System.out.println("Name : "+super.getName());
		System.out.println("Password : "+super.getPassword());
		System.out.println("Email : "+super.getEmail());
		System.out.println("Phone Number : "+super.getPhoneNumber());
		System.out.println("Account Type : "+super.getType());
	}

	SystemController getSystemController(){
		return this.systemController;
	}

}
