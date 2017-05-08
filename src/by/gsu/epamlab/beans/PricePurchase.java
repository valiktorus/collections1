package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

public class PricePurchase extends Purchase {
    private Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public PricePurchase() {
        super();
    }

    public PricePurchase(String name, int price, int unitsNumber, int discount) {
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
