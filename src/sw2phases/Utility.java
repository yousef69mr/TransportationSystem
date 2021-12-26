package sw2phases;

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
    Users getUser(){
        return  this.user;
    }
}
