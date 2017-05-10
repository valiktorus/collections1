package by.gsu.epamlab.factory;

import by.gsu.epamlab.enums.PurchaseFieldsEnum;
import by.gsu.epamlab.beans.PricePurchase;
import by.gsu.epamlab.beans.Purchase;

public class PurchasesFactory {
    private final static int PURCHASE_FIELDS_NUMBER = Purchase.class.getDeclaredFields().length;

    public static Purchase getClassFromFactory(String line){
        String[] values = line.split(";");
        String name = values[PurchaseFieldsEnum.NAME.ordinal()];
        int price = Integer.parseInt(values[PurchaseFieldsEnum.PRICE.ordinal()]);
        int number = Integer.parseInt(values[PurchaseFieldsEnum.NUMBER.ordinal()]);
        Purchase purchase;
        if (values.length == PURCHASE_FIELDS_NUMBER){
            purchase = new Purchase(name, price, number);
        } else {
            int discount = Integer.parseInt(values[PurchaseFieldsEnum.DISCOUNT.ordinal()]);
            purchase = new PricePurchase(name, price, number, discount);
        }
        return purchase;
    }
}