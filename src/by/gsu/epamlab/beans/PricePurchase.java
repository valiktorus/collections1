package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

public class PricePurchase extends Purchase {

    private Byn discount;

    public PricePurchase() {
        this(Constants.EMPTY_LINE, 0, 0, 0);
    }

    public PricePurchase(String name, int price, int unitsNumber, int discount) {
        super(name, price, unitsNumber);
        this.discount = new Byn(discount);
    }

    public Byn getDiscount() {
        return discount;
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
