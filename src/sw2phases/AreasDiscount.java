package sw2phases;

import java.util.ArrayList;

public class AreasDiscount extends DiscountDecoder{

    private ArrayList<String> areas;
    private float discountValue;

    AreasDiscount(Discount discount) {
        super(discount);
        areas=new ArrayList<>();
        discountValue=10;
    }

    ArrayList<String> getAreas(){
        return this.areas;
    }

    void addArea(String area){
        areas.add(area);
        System.out.println("Area added Successfully");
    }

    void removeArea(String area){
        areas.remove(area);
        System.out.println("Area removed Successfully");
    }

    boolean matchSource(Ride ride){
        for (int i=0;i<areas.size();i++){
            if(ride.getSource().equalsIgnoreCase(areas.get(i))){
                return true;
            }
        }
        return false;
    }

    boolean matchDestination(Ride ride){
        for (int i=0;i<areas.size();i++){
            if(ride.getDestination().equalsIgnoreCase(areas.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    public float getDiscountPercentage() {
        return super.getDiscountPercentage()+discountValue;
    }
}
