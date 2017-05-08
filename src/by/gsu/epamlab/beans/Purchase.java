package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

public class Purchase{
    private String name;
    private Byn price;
    private int number;

    public Purchase() {
        super();
        name = null;
    }

    public Purchase(String name, int price, int number) {
        super();
        this.name = name;
        this.price = new Byn(price);
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price){
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public Byn getCost(){
        return new Byn(price).mul(number);
    }

    protected String fieldsToString(){
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.DELIMITER + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Purchase)) return false;

        Purchase purchase = (Purchase) o;

        if (!name.equals(purchase.name)) return false;
        return price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
