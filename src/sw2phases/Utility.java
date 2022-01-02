package sw2phases;

import java.util.ArrayList;

public class Utility {

    private Users user;

    Utility(Users user){
        this.user=user;
    }

    boolean verifyName(String name){
        if(name.length()>0) {
            return true;
        }

        return false;
    }
    boolean verifyEmail(String email){
        if(email.contains("@")&&email.contains(".com")){
            return true;
        }

        return false;
    }

    boolean verifyNationalID(String nationalID){
        if(nationalID.length()==14) {
            return true;
        }

        return false;
    }

    boolean verifyPhoneNumber(String phone){
        if(phone.length()==11){
            return true;
        }

        return false;
    }

    boolean verifyDateFormat(String day,String month,String year){
        if(day.length()<=2&&day.length()>0&&month.length()<=2&&month.length()>0&&year.length()==4){
            return true;
        }

        return false;
    }

    boolean verifyNumberOfPassangers(int num){
        if(num>0&&num<8){
            return true;
        }
        return false;
    }

    boolean match(Ride ride, ArrayList<Ride> rides){
        for (int i=0;i<rides.size();i++) {
            if (rides.get(i).getClient().equals(ride.getClient()) && rides.get(i).getSource().equals(ride.getSource()) && rides.get(i).getDestination().equals(ride.getDestination())) {
                return true;
            }
        }
        return false;
    }


    boolean verifyRate(float rate){
        if(rate>=1&&rate<=5) {
            return true;
        }
        System.out.println("Out of Range !!");
        return false;
    }

    Users getUser(){
        return  this.user;
    }
}
