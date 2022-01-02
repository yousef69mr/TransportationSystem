package sw2phases;

public class DiscountDecoder implements Discount{

    private Discount discount;

    DiscountDecoder(Discount discount){
        this.discount=discount;
    }

    @Override
    public float getDiscountPercentage() {
        return this.discount.getDiscountPercentage();
    }
}
