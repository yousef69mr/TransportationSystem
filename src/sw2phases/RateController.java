package sw2phases;

public class RateController {

    private Client client;

    RateController(Client client){
        this.client=client;
    }

    void rateDriver(float rateRange) {

        if(client.getRide()!=null&&client.getRating()==null) {
            client.setRate(new Ratings(rateRange,client,client.getRide().getDriver()));
            client.getRide().getDriver().addRating(client.getRating());
            client.getRide().getDriver().setAverageRatings();

            System.out.println("Driver is rated Successfully");

        }else if(client.getRide()!=null&&client.getRating()!=null){

            System.out.println("This Driver has already rated !!");

        }else {
            System.out.println("you have to make a ride first to rate its driver");
        }
    }

}
