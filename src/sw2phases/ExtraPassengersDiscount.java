package sw2phases;

public class ExtraPassengersDiscount extends DiscountDecoder{
    private float discountValue;

    ExtraPassengersDiscount(Discount discount) {
        super(discount);
        discountValue=5;
    }

    @Override
    public float getDiscountPercentage() {
        return super.getDiscountPercentage()+discountValue;
    }
}
