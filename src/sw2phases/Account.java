package sw2phases;


public class Account {
	
	private Users user;
	
	Account(Users user){
		
		this.user=user;
		
	}
	
	Users getUser() {
		return this.user;
	}
}
