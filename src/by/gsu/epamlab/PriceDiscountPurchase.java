package by.gsu.epamlab;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(String name, int price, int unitsNumber, int discount) {
        super(name, price, unitsNumber);
        this.discount = new Byn(discount);
    }
    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }
}
