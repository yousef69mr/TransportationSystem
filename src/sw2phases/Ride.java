package sw2phases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Ride {
	
	private String source;
	private String destination;
	private float ridePrice;
	private float priceAfterDiscount;
	private Driver driver;
	private Client client;
	private Date requestedTime;
	private Date pickUpTime;
	private Date rideDuration;
	private Date reachedDestinationTime;
	private int numOfPassengers;
	private ArrayList<Discount> discounts;
	private static int rideNumber;
	private RideController rideController;
	private TransportationSystem system;

	Ride(Client c,String src){

		setRequestedTime(null);
		setPickUpTime(null);
		setReachedDestinationTime(null);
		setSource(src);
		setRidePrice(0f);
		setClient(c);
		rideController= new RideController(this,getClient());
		discounts=new ArrayList<>();
		//setPriceAfterDiscount();
		LinkRideToDriver();
		rideNumber++;
	}

	Ride(Client c,Driver d,String src,String dest,int numOfPassengers){

		setRequestedTime(null);
		setPickUpTime(null);
		setReachedDestinationTime(null);
		setSource(src);
		setDestination(dest);
		setRidePrice(0f);
		setClient(c);
		setNumOfPassengers(numOfPassengers);
		//setRideSystem(client.getSystem());
		rideController= new RideController(this,getClient());
		discounts=new ArrayList<>();
		rideDuration=null;
		//setPriceAfterDiscount();

		this.driver=d;
		setDriver(driver);

		rideNumber++;
	}

	RideController getRideController()
	{
		return rideController;
	}

	void setNumOfPassengers(int num){
		this.numOfPassengers=num;
	}

	int getNumOfPassengers(){
		return this.numOfPassengers;
	}

	// set the new ride in the the ArrayList in TransportationSystem class.
	public void setRideSystem(TransportationSystem newSystem) {
		if (system != newSystem) {
			TransportationSystem old = system;
			system = newSystem;
			if (newSystem != null) {
				newSystem.addRide(this);
			}
			if (old != null) {
				old.removeRide(this);
			}
		}
	}
	
	void LinkRideToDriver() {
		this.driver=rideController.getDriverMatchesItsFavouriteArea();
		setDriver(driver);
	}


	
	public void setDriver(Driver newDriver) {
		
		if (driver != newDriver) {
			Driver old = driver;
			driver = newDriver;
			if (newDriver != null) {
				newDriver.addRide(this);	
			}
			if (old != null) {
				old.removeRide(this);
			}
		}
	}
	
	
	Driver getDriver() {
		return this.driver;
	}
	
	void setClient(Client c) {
		
		this.client=c;
		
	}
	
	
	Client getClient() {
		return this.client;
	}
	
	void setRidePrice(float price) {
		
		this.ridePrice= price;
		
	}
	
	float getRidePrice() {
		return this.ridePrice;
	}


	void setRequestedTime(Date requestedTime){
		this.requestedTime=requestedTime;
	}

	Date getRequestedTime(){
		return this.requestedTime;
	}

	void setPickUpTime(Date pickUpTime){
		this.pickUpTime=pickUpTime;
	}

	Date getPickUpTime(){
		return this.pickUpTime;
	}

	void setRideDurationTime(){
		//Calendar calendar = Calendar.getInstance();
		this.rideDuration = new Date(getReachedDestinationTime().getTime()-getPickUpTime().getTime());
		//calendar.setTime(rideDuration);
	}

	Date getRideDurationime(){
		return this.rideDuration;
	}

	void setReachedDestinationTime(Date reachedDestinationTime){
		this.reachedDestinationTime=reachedDestinationTime;
		setRideDurationTime();
	}

	Date getReachedDestinationTime(){
		return this.reachedDestinationTime;
	}

	void setSource(String s) {
		
		this.source=s;
		
	}

	ArrayList<Discount> getDiscounts(){
		return this.discounts;
	}
	
	String getSource() {
		return this.source;
	}
	
	void setDestination(String dest) {
		
		this.destination=dest;
		
	}
	
	String getDestination() {
		return this.destination;
	}

	void setPriceAfterDiscount(){
		if(getRidePrice()!=0) {
			this.priceAfterDiscount = getRidePrice() - calculateDiscount();
		}
	}

	float getPriceAfterDiscount(){
		setPriceAfterDiscount();
		return this.priceAfterDiscount;
	}
/*
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getDiscount() {
		return this.discount;
	}
*/
	float calculateDiscountPercentage(){
		float sum=0;

		for (int i=0;i<discounts.size();i++){
			sum+=discounts.get(i).getDiscountPercentage();
		}
		return sum;
	}

	float calculateDiscount(){
		return (calculateDiscountPercentage()/100)*getRidePrice();
	}

	int getRideNumber() {
		return this.rideNumber;
	}
	// Display all data about the Ride 
	void displayRideData() {

		System.out.println("Ride Number : #" +getRideNumber());
		System.out.println("Source Location : " +getSource());
		System.out.println("Destination Location : " +getDestination());
		System.out.println("Price : "+getRidePrice());


		if(calculateDiscountPercentage()!=0) {

			System.out.println("Discount : " + calculateDiscount());
			System.out.println("Price After Discount : " + getPriceAfterDiscount());
		}

		if(getRequestedTime()!=null) {
			System.out.println("Time Stamp : " + getRequestedTime());
		}

		if(getPickUpTime()!=null){
			System.out.println("Pick Up Time Stamp : " + getPickUpTime());
		}

		if(getReachedDestinationTime()!=null){
			System.out.println("Arrived Time Stamp : " + getReachedDestinationTime());
			Calendar calendar = Calendar.getInstance();
			//rideDuration = new Date(getReachedDestinationTime().getTime()-getPickUpTime().getTime());
			calendar.setTime(getRideDurationime());
			System.out.println("Ride Duration  : " + calendar.get(Calendar.HOUR_OF_DAY) + ":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));
		}



		System.out.println("Number Of Passengers : " + getNumOfPassengers());
		System.out.println("//////////////////////");
		System.out.println("Client Info :");
		client.DisplaySpecificData();
		System.out.println("//////////////////////");
		System.out.println("Driver Info :");
		driver.DisplaySpecificData();
		System.out.println("//////////////////////");
	}

	void displayMainRideData(){
		System.out.println("Ride Number : #" +getRideNumber());
		System.out.println("Source Location : " +getSource());
		System.out.println("Destination Location : " +getDestination());
		System.out.println("Original Price : "+getRidePrice());


		if(calculateDiscountPercentage()!=0) {

			System.out.println("Discount : " + calculateDiscount());
			System.out.println("Price After Discount : " + getPriceAfterDiscount());
		}

		if(getRequestedTime()!=null) {
			System.out.println("Requested Time Stamp : " + getRequestedTime());
		}

		if(getPickUpTime()!=null){
			System.out.println("Pick Up Time Stamp : " + getPickUpTime());
		}

		if(getReachedDestinationTime()!=null){
			System.out.println("Arrived Time Stamp : " + getReachedDestinationTime());

			Calendar calendar = Calendar.getInstance();
			rideDuration = new Date(getReachedDestinationTime().getTime()-getPickUpTime().getTime());
			calendar.setTime(rideDuration);

			System.out.println("Ride Duration  : " + calendar.get(Calendar.HOUR_OF_DAY) + ":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));
		}



		System.out.println("Number Of Passengers : " + getNumOfPassengers());
	}

	TransportationSystem getSystem() {
		return this.system;
	}
	
}
