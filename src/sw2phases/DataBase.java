package sw2phases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataBase {

    private TransportationSystem system;

    private Set<Users> users;

    private ArrayList<Driver> drivers;
    private ArrayList<Client> clients;
    private ArrayList<Ride> rides;

    private ArrayList<Users> suspended;
    private ArrayList<Users> requests;
    private ArrayList<Users> confirmed;
    private ArrayList<Users> deleted;


    DataBase(TransportationSystem system){
        this.system=system;
        users = new HashSet<>();
        drivers = new ArrayList<>();
        clients = new ArrayList<>();
        rides = new ArrayList<>();
        suspended=new ArrayList<>();
        requests=new ArrayList<>();
        confirmed=new ArrayList<>();
        deleted=new ArrayList<>();
    }


    void displayDrivers() {
        for(int i=0;i<getAllDrivers().size();i++) {
            getAllDrivers().get(i).DisplayAllData();
        }
    }

    void displayUsers() {
        ArrayList<Users> user=new ArrayList<>(getAllUsers());
/*
		for(int i=0;i<user.size();i++) {

			if(user.get(i).getName().equalsIgnoreCase("")){
				user.remove(user.get(i));
			}

		}
*/
        System.out.println("\nActive Users:");

        for(int i=0;i<user.size();i++) {

            System.out.println(i+1+")"+user.get(i).getName());

        }
    }

    void showPendingDrivers() {

        System.out.println("\nPending Drivers:");

        for(int i=0;i<getAllRequests().size();i++) {
            System.out.println(i+1+")"+getAllRequests().get(i).getName());
        }
    }

    void showSuspendedUsers() {
        System.out.println("\nSuspended Drivers:");

        for(int i=0;i<getAllSuspended().size();i++) {
            System.out.println(i+1+")"+getAllSuspended().get(i).getName());
        }
    }



    TransportationSystem getSystem(){
        return this.system;
    }

    ArrayList<Driver> getAllDrivers(){
        return drivers;
    }

    ArrayList<Client> getAllClients(){
        return clients;
    }

    Set<Users> getAllUsers(){
        return users;
    }

    ArrayList<Users> getAllRequests(){
        return requests;
    }

    ArrayList<Users> getAllConfirmed(){
        return confirmed;
    }

    ArrayList<Users> getAllSuspended(){
        return suspended;
    }

    ArrayList<Users> getAllDeleted(){
        return deleted;
    }

    ArrayList<Ride> getAllRides(){
        return rides;
    }
}
