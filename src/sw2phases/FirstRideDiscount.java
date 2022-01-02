package sw2phases;

import java.util.ArrayList;

public class FirstRideDiscount extends DiscountDecoder{

    private float discountValue;

    FirstRideDiscount(Discount discount) {
        super(discount);
        discountValue=10;
    }

    @Override
    public float getDiscountPercentage() {
        return super.getDiscountPercentage()+discountValue;
    }

}
