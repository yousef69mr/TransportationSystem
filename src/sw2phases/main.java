package sw2phases;

import java.util.*;

public class main {

	public static void main(String[] args) {
		
		TransportationSystem system=TransportationSystem.getInstance("uber");
		Administrator admin=system.getAdmin();
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			System.out.println("Enter choose");
			char input =scan.next().charAt(0);
			
			switch(input) {
			
				case('A'):
					Driver d;
					while(true) {
						System.out.println("Enter your Name");
						String name =scan.next();
						System.out.println("Enter your Phone Number");
						String phone =scan.next();
						System.out.println("Enter your Email");
						String email =scan.next();
						System.out.println("Enter your Password");
						String pass =scan.next();
						System.out.println("Enter your National ID");
						String id =scan.next();
						System.out.println("Enter your Licence Number");
						String licence =scan.next();
						d=system.createDriverAccount(name, phone, email, pass,id,licence);
						System.out.print(d.getAccount());
						if(system.createDriverAccount(name, phone, email, pass,id,licence)!=null) {
							admin.verifyDriver(d);
							d=system.getSpecificDriver(name, pass);
							System.out.println("Account created successfully");
							break;
						}
					}
				
					//admin.approveDriver(d);
					
					String area =scan.next();
					d.addFavouriteArea(area);
					area =scan.next();
					d.addFavouriteArea(area);
					area =scan.next();
					d.addFavouriteArea(area);
					system.displayDrivers();
					
				case('B'):
					Driver d2;
					while(true) {
						String name =scan.next();
						String pass =scan.next();
						if(system.getAllDrivers().get(0).login(name, pass)!=null) {
							d2=system.getSpecificDriver(name, pass);
							break;
						}
					}
					
					
					
					
					
					
						
					
				case('C'):
					String in =scan.nextLine();
					if(in.equals("exit")) {
						scan.close();
						break;
					}
			}
		}
	}
		
}


