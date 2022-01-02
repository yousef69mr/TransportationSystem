package sw2phases;

public class HolidayDiscount extends DiscountDecoder{
    private float discountValue;

    HolidayDiscount(Discount discount) {
        super(discount);
        discountValue=5;
    }

    @Override
    public float getDiscountPercentage() {
        return super.getDiscountPercentage()+discountValue;
    }
}
