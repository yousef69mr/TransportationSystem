package sw2phases;


import java.util.ArrayList;

public class Account {

	private String userName;
	private String password;

	private Users user;
	
	Account(Users user){
		
		this.user=user;
		setName(user.getName());
		setPassword(user.getPassword());
		
	}
	
	Users getUser() {
		return this.user;
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
}
