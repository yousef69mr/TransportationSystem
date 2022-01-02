package sw2phases;

public class BirthDayDiscount extends DiscountDecoder{
    private float discountValue;

    BirthDayDiscount(Discount discount) {
        super(discount);
        discountValue=10;
    }

    @Override
    public float getDiscountPercentage() {
        return super.getDiscountPercentage()+discountValue;
    }
}
