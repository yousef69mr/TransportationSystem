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



	Users login(String type,String name,String pass){
		if(type.equalsIgnoreCase("driver")) {
			return user.getSystem().getSpecificDriver(name,pass);
		}else if(type.equalsIgnoreCase("client")){
			return user.getSystem().getSpecificClient(name,pass);
		}else{
			return null;
		}
	}

	Users signUp(String type,String name,String phone,String email,String pass,String id,String licence){
		if(type.equalsIgnoreCase("driver")) {
			return user.getSystem().createDriverAccount(name, phone, email, pass, id, licence);
		}else if(type.equalsIgnoreCase("client")){
			return user.getSystem().createClientAccount(name,phone,email,pass);
		}else{
			return null;
		}

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
