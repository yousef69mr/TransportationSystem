package sw2phases;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		TransportationSystem system=TransportationSystem.getInstance("FCAI");
		Administrator admin=system.getAdmin();
		Scanner scan=new Scanner(System.in);

		system.getUI().ShowWelcomeMenu();

		boolean isRunning =true;
		boolean isRunningClient =true;
		boolean isRunningDriver =true;
		boolean isRunningAdmin =true;
		boolean WantToLogin =true;

		while(isRunning) {

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
/*
							Driver d2 = new Driver();
							d2.setSystem(system);
*/
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

								//d = (Driver) d.getAccount().signUp(d.getType(),  "name", "01234567896", "sd@x.com", "0123", "01234569871230", "licence");
								d=(Driver) d.signUp(d);
							//	d = (Driver) d.getAccount().signUp(d.getType(),  name,phone, email, pass, id, licence);

								//System.out.print(d.getAccount());
								if (d.isValidInput()) {

									System.out.println("Account created successfully");

									//admin.getSystemController().verifyDriver(d);
									//d.addFavouriteArea("milano");

									break;
								}

							}
							break;

						case ('B'):
							Client c = new Client();
							c.setSystem(system);
							/*
							Client c1 = new Client();
							c1.setSystem(system);
							*/
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

							String day="";
							String month="";
							String year="";

							while (!c.getUtility().verifyDateFormat(day,month,year)){
								System.out.println("Enter your BirthDay Details :");
								System.out.print("Enter Day : ");
								day = scan.next();
								System.out.print("Enter Month : ");
								month = scan.next();
								System.out.print("Enter Year : ");
								year = scan.next();

								if(!c.getUtility().verifyDateFormat(day,month,year)){
									System.out.println("Invalid Date Format !!");
								}

							}

							c.setName(name);
							c.setEmail(email);
							c.setPassword(pass);
							c.setPhoneNumber(phone);
							c.setBirthDay(new BirthDay(day,month,year));


							c=(Client) c.signUp(c);
							//c = (Client) c.getAccount().signUp(c.getType(),  "ali", "01234567896", "sd@x.com", "2020", null, null);
							//c = (Client) c.getAccount().signUp(c.getType(), name, phone, email, pass, null, null);

							//System.out.print(c.getAccount());


							System.out.println("Account created successfully");


							break;
							//admin.approveDriver(d);
					}

					break;

				case ('B'):
					String name="";
					String pass="";

					while (WantToLogin) {
						System.out.println("Enter your Name");
						 name = scan.next();
						System.out.println("Enter your Password");
						 pass = scan.next();
						//driver=(Driver) driver.getAccount().login(system.getSpecificUser(name,pass).getType(),name,pass);
						if (system.getSpecificUser(name,pass) != null||name.equalsIgnoreCase(admin.getName())&&pass.equals(admin.getPassword())) {
							System.out.println("Login Successfully !!");
							break;
						}


						system.getUI().loginFaluireMenu();

						input=scan.next().charAt(0);

						switch (input){
							case('A'):
								break;

							case ('B'):
								WantToLogin=false;
								break;

							default:
								System.out.println("Invalid Input !!");
								break;
						}


					}

					if(!WantToLogin){
						break;
					}

					if (name.equalsIgnoreCase(admin.getName())&&pass.equalsIgnoreCase(admin.getPassword())){
						//admin  info
						System.out.println("Welcome "+admin.getName());
						while (isRunningAdmin) {
							system.getUI().ShowAdminAccountMethodsMenu();
							System.out.println("Enter your Choice");

							char adminChoice = scan.next().charAt(0);
							switch (adminChoice) {
								case ('A'):

									System.out.println("Admin Info :");
									admin.DisplayAllData();
									break;

								case ('B'):

									system.getDatabase().showPendingDrivers();
									System.out.println("Select Number of Driver ");
									int num = scan.nextInt();
									admin.getSystemController().verifyDriver((Driver) system.getDatabase().getAllRequests().get(num-1));
									break;

								case ('C'):

									system.getDatabase().displayUsers();
									break;

								case ('D'):

									system.getDatabase().displayUsers();
									System.out.println("Select Number of User ");
									int num2 = scan.nextInt();
									ArrayList<Users> user=new ArrayList<>(system.getDatabase().getAllUsers());
									admin.getSystemController().suspendUser(user.get(num2-1));
									break;

								case ('E'):

									system.getDatabase().showSuspendedUsers();
									System.out.println("Select Number of User ");
									num2 = scan.nextInt();
									admin.getSystemController().returnFromSuspended(system.getDatabase().getAllSuspended().get(num2-1));
									break;

								case ('F'):

									system.getDatabase().displayUsers();
									System.out.println("Select Number of User ");
									num2 = scan.nextInt();
									admin.getSystem().removeUser(system.getDatabase().getAllSuspended().get(num2-1));
									break;

								case('G'):

									system.getDatabase().displayDrivers();
									break;

								case('H'):

									admin.getSystemController().displayRides();
									break;

								case('I'):
									System.out.println("Enter Area Name");
									String area = scan.next();

									admin.getSystemController().addDiscountForArea(area);

									break;

								case('J'):
									System.out.println("Enter Area Name");
									area = scan.next();

									admin.getSystemController().removeDiscountForArea(area);

									break;


							}

							System.out.println("\nDo you need any other Services ?");
							System.out.println("Yes/No (y/n)");
							while (true) {

								input =scan.next().charAt(0);

								if (input == 'n' || input == 'N') {

									System.out.println("It was a pleasure to serve U :)");
									isRunningAdmin=false;
									break;

								} else if (input == 'y' || input == 'Y') {

									break;

								}else{
									System.out.println("Invalid Input !!\nTry Again ");
								}
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
							while (isRunningDriver) {
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

										driver.fixPendingRides();

										if(!driver.getAllRides().isEmpty()) {
											driver.displayRidesList();

											System.out.println("Select Number of Selected Ride ");
											int num2 = scan.nextInt();

											System.out.println("Enter offer price ");
											float offer = scan.nextInt();
											ArrayList<Ride> ride = new ArrayList<>(driver.getAllRides());
											driver.getRideData().setRide(driver.getSpecificRide(num2 - 1));
											//ride.get(num2-1).setRidePrice(offer);
											//driver.setPriceForSpecificRide(offer,ride.get(num2-1).getRideNumber());
											driver.getRideData().setRole(2);
											driver.getRideData().setOffer(offer);
											//									driver.getRideData().setRide(driver.getRide(num2-1));
											//driver.getRideController().setPriceForSpecificRide(offer,ride.get(num2-1));
										}else{
											System.out.println("There is no Pending Rides");
										}

										break;

									case('G'):

										if(!driver.getAllConfirmedRides().isEmpty()) {
											driver.displayRideHistory();
										}else{
											System.out.println("There is no Previous Rides for this Driver");
										}
										break;

									case('H'):

										driver.fixPendingRides();

										if(driver.getRideController().getActiveRide(driver)!=null) {

											system.getUI().ShowDriverActiveRideMethodsMenu();
											char choice = scan.next().charAt(0);

											switch (choice) {
												case ('A'):
													Date pickUpDate =new Date();

													driver.getSystemController().getSpecificRide(driver.getRideController().getActiveRide(driver)).setPickUpTime(pickUpDate);
													//driver.getRideController().getActiveRide(driver).setPickUpTime(pickUpDate);

													System.out.println("Pick Up Time : "+pickUpDate);
													break;
												case ('B'):

													Date arrivedDate =new Date();
													driver.getSystemController().getSpecificRide(driver.getRideController().getActiveRide(driver)).setReachedDestinationTime(arrivedDate);
													//driver.getRideController().getActiveRide(driver).setReachedDestinationTime(arrivedDate);

													System.out.println("Arrived Time : "+arrivedDate);
													break;

												case ('C'):

													break;
											}
										}else{
											System.out.println("There is no Active Rides");
										}
										break;
								}

								System.out.println("Do you need any other Services ?");
								System.out.println("Yes/No (y/n)");


								while (true) {

									input =scan.next().charAt(0);

									if (input == 'n' || input == 'N') {


										System.out.println("It was a pleasure to serve U :)");
										isRunningDriver=false;
										break;

									} else if (input == 'y' || input == 'Y') {

										break;

									}else{
										System.out.println("Invalid Input !!\nTry Again ");
									}
								}
							}
							break;

						case ("client"):
							Client client=new Client();
							client.setSystem(system);

							client=(Client) client.login(system.getSpecificUser(name,pass));
							System.out.println("///////////////////////////////////////////");
							System.out.println("Welcome "+client.getName());
							while (isRunningClient) {
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
										int passengerNumber;
										while (true) {
											System.out.println("Enter Number of Passengers [Including YOU]");
											passengerNumber = scan.nextInt();
											if(client.getUtility().verifyNumberOfPassangers(passengerNumber)){
												break;
											}

											System.out.println("Please Enter a valid Number [1 to 7]");

										}

										ArrayList<Driver> matched =client.getRideController().getAllDriversMatchesItsFavouriteArea(client,src);

										//client.getRideController().requestRide(client,src, destination);
										client.getRideController().createPotintialRides(client,src,destination,matched,passengerNumber);
										break;

									case ('C'):

										System.out.println("Enter Rate Number [1 to 5]");
										while(true){
											float rate = scan.nextInt();
											if(client.getUtility().verifyRate(rate)) {
												client.getRateController().rateDriver(rate);
												break;

											}else{
												System.out.println("Try Again");
											}
										}
										break;

									case ('D'):

										if(client.getRide()!=null) {
											client.getRide().getDriver().displayRatings();
										}else{
											System.out.print("No available drivers in your area\n");
										}
										break;

									case ('E'):

										system.removeUser(client);
										admin.getSystemController().removeClient(client);
										break;

									case ('F'):

										if(!client.getRideData().getOffers().isEmpty()) {
											client.getRideData().displayOffers();

											System.out.println("Choose your deal [Price]");
											int price = scan.nextInt();

											client.getRideData().setRole(1);
											client.getRideData().setOffer(price);
										}else{
											System.out.println("There is no Offers yet :(");
										}
										//((Client)client.getRideData().getSpecificObserver(num2-1)).getRideData().getRide().setRideSystem(system);
										break;


								}

								System.out.println("\nDo you need any other Services ?");
								System.out.println("Yes/No (y/n)");

								while (true) {

									input =scan.next().charAt(0);

									if (input == 'n' || input == 'N') {

										System.out.println("It was a pleasure to serve U :)");
										isRunningClient=false;
										break;

									} else if (input == 'y' || input == 'Y') {

										break;

									}else{
										System.out.println("Invalid Input !!\nTry Again ");
									}
								}

							}
							break;

					}


			}

			System.out.println("\nDo you need any other Services in the System?");
			System.out.println("Yes/No (y/n)");



			while (true) {

				input =scan.next().charAt(0);

				if (input == 'n' || input == 'N') {

					scan.close();
					System.out.println("It was a pleasure to serve U :)");
					isRunning=false;
					break;

				} else if (input == 'y' || input == 'Y') {
					isRunningClient =true;
					isRunningDriver =true;
					isRunningAdmin =true;
					WantToLogin =true;
					break;

				}else{
					System.out.println("Invalid Input !!\nTry Again ");
				}
			}

		}
	}
		
}


