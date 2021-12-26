package sw2phases;

public class Client extends Users{

	private Ride ride;
	private Ratings rate;

	private RateController rateController;

	Client(){
		setName("");
		setPassword("");
		setType();
		this.utility=new Utility(this);
		this.rideController=new RideController(ride,this);
		this.rateController=new RateController(this);
		//setAccount();
	}

	Client(String name,String phone,String email,String pass){
		setName(name);
		setEmail(email);
		setPhoneNumber(phone);
		setPassword(pass);
		setType();
		//setAccount();
		this.utility=new Utility(this);
		this.rideController=new RideController(ride,this);
		this.rateController=new RateController(this);
	}
	

	

	
	@Override
	void DisplayAllData() {

		System.out.println("Name : "+super.getName());
		System.out.println("Email : "+super.getEmail());
		System.out.println("Password : "+super.getPassword());
		System.out.println("Phone Number : "+super.getPhoneNumber());
		System.out.println("Account Type : "+super.getType());
	}


	void DisplaySpecificData(){
		System.out.println("Name : "+super.getName());
		System.out.println("Email : "+super.getEmail());
		System.out.println("Phone Number : "+super.getPhoneNumber());
		System.out.println("Account Type : "+super.getType());
	}

	@Override
	void setType() {
		
		Type="Client";
	}

	@Override
	Users signUp(Users user) {
		Client c=new Client(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword());
		c.setSystem(user.getSystem());
		if(c.isValidInput()) {
			if(!user.getSystem().getAllUsers().contains(c) ||!user.getSystem().getAllClients().contains(c)) {
				//addUser(c);
				user.getSystem().getAdmin().addClient(c);
			}
			return c;
		}
		else {
			return null;
		}

	}

	Ride getRide() {
		return this.ride;
	}
	
	Ratings getRating() {
		return this.rate;
	}

	RideController getRideController(){
		return this.rideController;
	}

	RateController getRateController(){
		return this.rateController;
	}
	void setRate(Ratings rate){
		this.rate=rate;
	}

	void setRide(Ride ride){
		this.ride=ride;
	}
	/*
	Client login(String name, String pass) {
		if(!super.getSystem().getAllSuspended().contains(super.getSystem().getSpecificClient(name, pass))) {
			if(super.getSystem().getSpecificClient(name,pass)!=null) {
				return super.getSystem().getSpecificClient(name, pass);
			}else {
				System.out.println("UserName or Password are incorrect");
				return null;
			}
		}else {
			System.out.println("Your Account is Suspended !!");
			return null;
		}

	}*/
}
