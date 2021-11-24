package sw2phases;

public class Ride {
	
	private String source;
	private String destination;
	private float ridePrice;
	private Driver driver;
	private Client client;
	private static int rideNumber;
	private TransportationSystem system;
	
	Ride(Client c,String src,String dest){
		
		setSource(src);
		setDestination(dest);
		//setRidePrice(0f);
		setClient(c);
		setRideSystem(client.getSystem());

		LinkRideToDriver();
		rideNumber++;
	}
	/*
	Ride(Driver d){
		this.driver=d;
		setDriver(d);
	}
	*/
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
		this.driver=getDriverMatchesItsFavouriteArea();
		setDriver(driver);
	}
	
	
	Driver getDriverMatchesItsFavouriteArea() {
		
		if(!system.getAllDrivers().isEmpty()) {
			for(int i=0;i<system.getAllDrivers().size();i++) {
				
					
					for(int j=0;j<system.getAllDrivers().get(i).getFavouriteAreas().size();j++) {
						
						if(system.getAllDrivers().get(i).isFavourite(this, system.getAllDrivers().get(i).getFavouriteAreas())) {
							return system.getAllDrivers().get(i);
						}
					}
					
			}
		}
		
		return null;
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
	
	void setSource(String s) {
		
		this.source=s;
		
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
	
	int getRideNumber() {
		return this.rideNumber;
	}
	// Display all data about the Ride 
	void displayRideData() {

		System.out.println("Ride Number : #" +getRideNumber());
		System.out.println("Source Location : " +getSource());
		System.out.println("Destination Location : " +getDestination());
		System.out.println("Price : "+getRidePrice());
		System.out.println("//////////////////////");
		System.out.println("Client INfo :");
		client.DisplaySpecificData();
		System.out.println("//////////////////////");
		System.out.println("Driver INfo :");
		driver.DisplaySpecificData();
		System.out.println("//////////////////////");
	}
	
}
