package sw2phases;

public class Client extends Users{

	private Ride ride;
	private Ratings rate;
	

	Client(){
		setName("");
		setPassword("");
		setType();

		setAccount();
	}

	Client(String name,String phone,String email,String pass){
		setName(name);
		setEmail(email);
		setPhoneNumber(phone);
		setPassword(pass);
		setType();
		setAccount();
	}
	
	void requestRide(Client c,String src,String dest) {
		ride=new Ride(c,src,dest);
		ride.setRideSystem(c.getSystem());
		super.getSystem().addRide(ride);
		ride.getDriver().addRide(ride);
		if(ride.getDriver()!=null){
			System.out.print("The Request is Completed Successfully\n");
			ride.getDriver().DisplaySpecificData();
		}else{
			System.out.print("No available drivers in your area\n");
		}

	}
	
	void rateDriver(float rateRange) {
		
		if(ride!=null) {
			rate =new Ratings(rateRange,this,ride.getDriver());
			ride.getDriver().addRating(rate);
			ride.getDriver().setAverageRatings();

			System.out.println("Driver is rated Successfully");
		}else {
			System.out.println("you have to make a ride first to rate its driver");
		}
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
	
	Ride getRide() {
		return this.ride;
	}
	
	Ratings getRating() {
		return this.rate;
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
