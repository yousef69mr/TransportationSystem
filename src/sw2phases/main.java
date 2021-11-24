package sw2phases;

import java.util.*;

public class main {

	public static void main(String[] args) {
		
		TransportationSystem system=TransportationSystem.getInstance("uber");
		Administrator admin=system.getAdmin();
		Scanner scan=new Scanner(System.in);
		
		while(true) {

			system.ShowMainMenu();
			System.out.println("Enter Choice");
			char input =scan.next().charAt(0);
			
			switch(input) {

				case ('A'):
					system.ShowUserTypeMenu();
					char input2 = scan.next().charAt(0);
					switch (input2) {

						case ('A'):
							Driver d = new Driver();
							d.setSystem(system);
							while (true) {

								System.out.println("Enter your Name");
								String name = scan.next();
								System.out.println("Enter your Phone Number");
								String phone = scan.next();
								System.out.println("Enter your Email");
								String email = scan.next();
								System.out.println("Enter your Password");
								String pass = scan.next();
								System.out.println("Enter your National ID");
								String id = scan.next();
								System.out.println("Enter your Licence Number");
								String licence = scan.next();
								d = (Driver) d.getAccount().signUp(d.getType(), name, phone, email, pass, id, licence);
								//System.out.print(d.getAccount());
								if (d.isValidInput()) {
									admin.verifyDriver(d);
									//Driver c=system.getSpecificDriver(name, pass);
									//c.DisplayData();
									System.out.println("Account created successfully");
									break;
								}
							}
						case ('B'):
							Client c = new Client();
							c.setSystem(system);
							while (true) {

								System.out.println("Enter your Name");
								String name = scan.next();
								System.out.println("Enter your Phone Number");
								String phone = scan.next();
								System.out.println("Enter your Email");
								String email = scan.next();
								System.out.println("Enter your Password");
								String pass = scan.next();

								c = (Client) c.getAccount().signUp(c.getType(), name, phone, email, pass, null, null);
								//System.out.print(d.getAccount());
								if (c.isValidInput()) {

									System.out.println("Account created successfully");
									break;
								}
							}


							//admin.approveDriver(d);
					}/*
					String area =scan.next();
					d.addFavouriteArea(area);
					area =scan.next();
					d.addFavouriteArea(area);
					area =scan.next();
					d.addFavouriteArea(area);
					system.displayDrivers();
					*/
				case ('B'):
					String name;
					String pass;

					while (true) {
						 name = scan.next();
						 pass = scan.next();
						//driver=(Driver) driver.getAccount().login(system.getSpecificUser(name,pass).getType(),name,pass);
						if (system.getSpecificUser(name,pass) != null) {
							System.out.println("Login Successfully !!");
							break;
						}
					}
					switch (system.getSpecificUser(name,pass).getType().toLowerCase()){
						case("driver"):
							Driver driver=new Driver();
							driver=(Driver) driver.getAccount().login(system.getSpecificUser(name,pass).getType(),name,pass);
							System.out.println("Welcome "+driver.getName());
							system.ShowDriverAccountMethodsMenu();
							System.out.println("Enter your Choice");

							char driverChioce = scan.next().charAt(0);
							switch (driverChioce){
								case('A'):

									driver.DisplayData();

								case('B'):

									driver.displayRatings();

								case('C'):

									System.out.println("Enter Area");
									String area =scan.next();
									driver.addFavouriteArea(area);

								case('D'):

									driver.displayFavouriteAreas();

								case('E'):

							}

						case ("client"):
							Client client=new Client();
							client=(Client) client.getAccount().login(system.getSpecificUser(name,pass).getType(),name,pass);
							System.out.println("Welcome "+client.getName());
							system.ShowDriverAccountMethodsMenu();
							System.out.println("Enter your Choice");

							char clientChoice= scan.next().charAt(0);
							switch (clientChoice){
								case('A'):

									client.DisplayData();

								case('B'):

								case('C'):


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


