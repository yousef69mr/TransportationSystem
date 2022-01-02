package sw2phases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Client extends Users {

	private Ride ride;
	private boolean firstRide;
	private BirthDay birthDay;
	private Ratings rate;

	private RateController rateController;

	Client() {
		setName("");
		setPassword("");
		setType();
		this.utility = new Utility(this);
		this.rideController = new RideController(ride, this);
		this.rateController = new RateController(this);
		firstRide=true;
		this.birthDay=new BirthDay();
		//setAccount();
	}

	Client(String name, String phone, String email, String pass,BirthDay birthDay) {
		setName(name);
		setEmail(email);
		setPhoneNumber(phone);
		setPassword(pass);
		setType();

		setRideData();

		//setAccount();
		this.utility = new Utility(this);
		this.rideController = new RideController(ride, this);
		this.rateController = new RateController(this);
		firstRide=true;
		this.birthDay=birthDay;
	}


	@Override
	void DisplayAllData() {

		System.out.println("Name : " + super.getName());
		System.out.println("Email : " + super.getEmail());
		System.out.println("Password : " + super.getPassword());
		System.out.println("Phone Number : " + super.getPhoneNumber());
		System.out.println("BirthDay : " + getBirthDay().getDay()+"/"+getBirthDay().getMonth()+"/"+getBirthDay().getYear());
		System.out.println("Account Type : " + super.getType());
	}


	void DisplaySpecificData() {
		System.out.println("Name : " + super.getName());
		System.out.println("Email : " + super.getEmail());
		System.out.println("Phone Number : " + super.getPhoneNumber());
		System.out.println("Account Type : " + super.getType());
	}

	@Override
	void setType() {

		Type = "Client";
	}

	void setBirthDay(BirthDay birthDay){
		this.birthDay=birthDay;
	}

	BirthDay getBirthDay(){
		return this.birthDay;
	}

	@Override
	Users signUp(Users user) {
		Client c = new Client(user.getName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(),((Client)user).getBirthDay());
		c.setSystem(user.getSystem());
		if (c.isValidInput()) {
			if (!user.getSystem().getDatabase().getAllUsers().contains(c) || !user.getSystem().getDatabase().getAllClients().contains(c)) {
				//addUser(c);
				user.getSystem().getAdmin().getSystemController().addClient(c);
			}
			return c;
		} else {
			return null;
		}

	}

	public boolean isFirstRide() {
		return firstRide;
	}

	public void setFirstRide(boolean firstRide) {
		this.firstRide = firstRide;
	}

	Ride getRide() {
		return this.ride;
	}

	Ratings getRating() {
		return this.rate;
	}

	RideController getRideController() {
		return this.rideController;
	}

	RateController getRateController() {
		return this.rateController;
	}

	void setRate(Ratings rate) {
		this.rate = rate;
	}

	void setRide(Ride ride) {
		this.ride = ride;
	}

	@Override
	public void update(Ride ride, float price) {


		if (ride != null) {

			if (price != 0) {


				ride = ride.getDriver().getRideData().search(price);


				ride.getClient().getRideController().applyDiscounts(ride);

				//after discounts
				ride.setPriceAfterDiscount();

				ride.setRequestedTime(new Date());
				ride.setRideSystem(ride.getDriver().getSystem());
				ride.setDriver(ride.getDriver());
				ride.getDriver().setConfirmedRides(ride);
				ride.getClient().setRide(ride);
				ride.getDriver().getRideData().getRides().remove(ride);




				for(int i=0;i<ride.getDriver().getRideData().getRides().size();i++){
					ride.getDriver().getRideData().getRides().get(i).getDriver().removeRide(ride.getDriver().getRideData().getRides().get(i));
				}



				if (ride.getClient().getRide() != null) {
					System.out.print("The Request is Completed Successfully\n/******************************/\nDriver Info :\n");
					ride.getDriver().DisplaySpecificData();
					System.out.print("/******************************/");
				}

				ride.getClient().setFirstRide(false);
				//ride.getDriver().removeRide(ride);

			} else {
				System.out.println("There is no offers yet");
			}
		}

	}
}
