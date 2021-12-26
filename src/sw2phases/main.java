package sw2phases;

import java.util.*;

public class main {

	public static void main(String[] args) {
		
		TransportationSystem system=TransportationSystem.getInstance("uber");
		Administrator admin=system.getAdmin();
		Scanner scan=new Scanner(System.in);

		System.out.println("****************************************************");
		System.out.println("****    Welcome to our Transportation System    ****");
		System.out.println("****************************************************");
		
		while(true) {

			system.getUI().ShowMainMenu();
			System.out.println("Enter Choice");
			char input =scan.next().charAt(0);
			
			switch(input) {

				case ('A'):
					system.getUI().ShowUserTypeMenu();
					char input2 = scan.next().charAt(0);
					switch (input2) {

						case ('A'):
							Driver d = new Driver();
							d.setSystem(system);
							while (true) {

								String name="";
								while (!d.getUtility().verifyName(name)){
									System.out.println("Enter your Name");
									 name = scan.next();

									 if(!d.getUtility().verifyName(name)){
										 System.out.println("Invalid Format !!");
									 }

								}

								String phone="";
								while (!d.getUtility().verifyPhoneNumber(phone)) {
									System.out.println("Enter your Phone Number");
									phone = scan.next();
									if(!d.getUtility().verifyPhoneNumber(phone)){
										System.out.println("Invalid Phone Number");
									}
								}

								String email="";
								while(!d.getUtility().verifyEmail(email)) {
									System.out.println("Enter your Email");
									email = scan.next();

									if (!d.getUtility().verifyEmail(email)) {
										System.out.println("Wrong Email Format");
									}
								}

								System.out.println("Enter your Password");
								String pass = scan.next();

								String id="";
								while(!d.getUtility().verifyNationalID(id)) {
									System.out.println("Enter your National ID");
									 id = scan.next();
									 if(!d.getUtility().verifyNationalID(id)){
										 System.out.println("Invalid National ID !!");
									 }
								}

								System.out.println("Enter your Licence Number");
								String licence = scan.next();

								d.setName(name);
								d.setEmail(email);
								d.setPassword(pass);
								d.setPhoneNumber(phone);
								d.setNationalID(id);
								d.setDriverLicence(licence);

/*
								d.setName("ezz");
								d.setEmail("sd@x.com");
								d.setPassword("0123");
								d.setPhoneNumber("01234567896");
								d.setNationalID("01234569871230");
								d.setDriverLicence("licence");
*/

								//d = (Driver) d.getAccount().signUp(d.getType(),  "name", "01234567896", "sd@x.com", "0123", "01234569871230", "licence");
								d=(Driver) d.signUp(d);
							//	d = (Driver) d.getAccount().signUp(d.getType(),  name,phone, email, pass, id, licence);

								//System.out.print(d.getAccount());
								if (d.isValidInput()) {

									//admin.verifyDriver(d);
									//d.addFavouriteArea("milano");

									System.out.println("Account created successfully");
									break;
								}
							}
							break;

						case ('B'):
							Client c = new Client();
							c.setSystem(system);
							while (true) {

								String name="";
								while (!c.getUtility().verifyName(name)){
									System.out.println("Enter your Name");
									name = scan.next();

									if(!c.getUtility().verifyName(name)){
										System.out.println("Invalid Format !!");
									}

								}

								String phone="";
								while (!c.getUtility().verifyPhoneNumber(phone)) {
									System.out.println("Enter your Phone Number");
									phone = scan.next();
									if(!c.getUtility().verifyPhoneNumber(phone)){
										System.out.println("Invalid Phone Number");
									}
								}

								String email="";
								while(!c.getUtility().verifyEmail(email)) {
									System.out.println("Enter your Email");
									email = scan.next();

									if (!c.getUtility().verifyEmail(email)) {
										System.out.println("Wrong Email Format");
									}
								}

								System.out.println("Enter your Password");
								String pass = scan.next();

								c.setName(name);
								c.setEmail(email);
								c.setPassword(pass);
								c.setPhoneNumber(phone);

								/*
								c.setName("ali");
								c.setEmail("sd@x.com");
								c.setPassword("2020");
								c.setPhoneNumber("01234567896");
								*/

								c=(Client) c.signUp(c);
								//c = (Client) c.getAccount().signUp(c.getType(),  "ali", "01234567896", "sd@x.com", "2020", null, null);
								//c = (Client) c.getAccount().signUp(c.getType(), name, phone, email, pass, null, null);

								//System.out.print(d.getAccount());
								if (c.isValidInput()) {

									System.out.println("Account created successfully");
									break;
								}
							}

							break;
							//admin.approveDriver(d);
					}

					break;

				case ('B'):
					String name;
					String pass;

					while (true) {
						System.out.println("Enter your Name");
						 name = scan.next();
						System.out.println("Enter your Password");
						 pass = scan.next();
						//driver=(Driver) driver.getAccount().login(system.getSpecificUser(name,pass).getType(),name,pass);
						if (system.getSpecificUser(name,pass) != null||name.equalsIgnoreCase(admin.getName())&&pass.equals(admin.getPassword())) {
							System.out.println("Login Successfully !!");
							break;
						}
						System.out.println("Incorrect Name or Password \nTry Again :(");


					}

					if (name.equalsIgnoreCase(admin.getName())&&pass.equalsIgnoreCase(admin.getPassword())){
						//admin  info
						System.out.println("Welcome "+admin.getName());
						while (true) {
							system.getUI().ShowAdminAccountMethodsMenu();
							System.out.println("Enter your Choice");

							char adminChoice = scan.next().charAt(0);
							switch (adminChoice) {
								case ('A'):

									admin.DisplayData();
									break;

								case ('B'):

									system.showPendingDrivers();
									System.out.println("Select Number of Driver ");
									int num = scan.nextInt();
									admin.verifyDriver((Driver) system.getAllRequests().get(num-1));
									break;

								case ('C'):

									system.displayUsers();
									break;

								case ('D'):

									system.displayUsers();
									System.out.println("Select Number of User ");
									int num2 = scan.nextInt();
									ArrayList<Users> user=new ArrayList<Users>(system.getAllUsers());
									admin.suspendUser(user.get(num2-1));
									break;

								case ('E'):

									system.showSuspendedUsers();
									System.out.println("Select Number of User ");
									num2 = scan.nextInt();
									admin.returnFromSuspended(system.getAllSuspended().get(num2-1));
									break;

								case ('F'):

									system.displayUsers();
									System.out.println("Select Number of User ");
									num2 = scan.nextInt();
									admin.getSystem().removeUser(system.getAllSuspended().get(num2-1));
									break;
							}

							System.out.println("\nDo you need any other Services ?");
							System.out.println("Yes/No (y/n)");

							input =scan.next().charAt(0);


							if(input=='n'||input=='N') {

								break;

							}else if(input=='y'||input=='Y') {

								continue;
							}

						}
						break;

					}

					switch (system.getSpecificUser(name,pass).getType().toLowerCase()){
						case("driver"):
							Driver driver=new Driver();
							driver.setSystem(system);

							driver=(Driver) driver.login(system.getSpecificUser(name,pass));
							System.out.println("///////////////////////////////////////////");
							System.out.println("Welcome "+driver.getName());
							while (true) {
								system.getUI().ShowDriverAccountMethodsMenu();
								System.out.println("Enter your Choice");

								char driverChoice = scan.next().charAt(0);
								switch (driverChoice) {
									case ('A'):

										driver.DisplayAllData();
										break;

									case ('B'):

										driver.displayRatings();
										break;

									case ('C'):

										System.out.println("Enter Area Name");
										String area = scan.next();
										driver.addFavouriteArea(area);
										break;

									case ('D'):

										System.out.println("Enter Area Name");
										area = scan.next();
										driver.removeFavouriteArea(area);
										break;

									case ('E'):

										driver.displayFavouriteAreas();
										break;

									case ('F'):

										driver.displayRidesList();
										System.out.println("Select Number of Selected Ride ");
										int num2 = scan.nextInt();
										System.out.println("Enter offer price ");
										float offer = scan.nextInt();
										ArrayList<Ride> ride=new ArrayList<Ride>(driver.getAllRides());
										//ride.get(num2-1).setRidePrice(offer);
										//driver.setPriceForSpecificRide(offer,ride.get(num2-1).getRideNumber());
										driver.setPriceForSpecificRide(offer,ride.get(num2-1));
										break;
								}

								System.out.println("Do you need any other Services ?");
								System.out.println("Yes/No (y/n)");

								input =scan.next().charAt(0);


								if(input=='n'||input=='N') {

									break;

								}else if(input=='y'||input=='Y') {

									continue;
								}
							}
							break;

						case ("client"):
							Client client=new Client();
							client.setSystem(system);

							client=(Client) client.login(system.getSpecificUser(name,pass));
							System.out.println("///////////////////////////////////////////");
							System.out.println("Welcome "+client.getName());
							while (true) {
								system.getUI().ShowClientAccountMethodsMenu();
								System.out.println("Enter your Choice");

								char clientChoice = scan.next().charAt(0);
								switch (clientChoice) {
									case ('A'):

										client.DisplayAllData();
										break;

									case ('B'):

										System.out.println("Enter Source Location");
										String src = scan.next();
										System.out.println("Enter your Destination Location");
										String destination = scan.next();
										client.getRideController().requestRide(client,src, destination);
										break;

									case ('C'):

										System.out.println("Enter Rate Number [1 to 5]");
										float rate = scan.nextInt();
										client.getRateController().rateDriver(rate);
										break;

									case ('D'):

										client.getRide().getDriver().displayRatings();
										break;

									case ('E'):

										system.removeUser(client);
										admin.removeClient(client);
										break;

								}

								System.out.println("\nDo you need any other Services ?");
								System.out.println("Yes/No (y/n)");

								input =scan.next().charAt(0);


								if(input=='n'||input=='N') {

									break;

								}else if(input=='y'||input=='Y') {

									continue;
								}

							}
							break;

					}


			}

			System.out.println("\nDo you need any other Services in the System?");
			System.out.println("Yes/No (y/n)");

			input =scan.next().charAt(0);


			if(input=='n'||input=='N') {

				scan.close();
				System.out.println("It was a pleasure to serve U :)");
				break;

			}else if(input=='y'||input=='Y') {

				continue;
			}


		}
	}
		
}


