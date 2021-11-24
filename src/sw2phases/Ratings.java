package sw2phases;

public class Ratings {
	
	private float rate;
	private Client client;
	private Driver driver;
	
	
	Ratings(float r,Client c,Driver d){
		
		setRate(r);
		setClient(c);
		setDriver(d);
	}
	
	//relation with Ratings class
	public void setDriver(Driver newDriver) {
		
		if (driver != newDriver) {
			Driver old = driver;
			driver = newDriver;
			if (newDriver != null)
				newDriver.addRating(this);
			if (old != null)
				old.removeRating(this);
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
	
	void setRate(float r) {
		if(r>=1&&r<=5) {
			this.rate =r;
		}else {
			System.out.println("Out of Range [1 to 5]");
		}
	}
	
	float getRate() {
		return this.rate;
	}
	
	void displayRatingData() {
		System.out.println(client.getName()+"Rate  : " +getRate());
	}
}
