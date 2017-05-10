package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.factory.RoundingFactory;

import java.math.RoundingMode;

public class Byn implements Comparable<Byn>{
    private int coins;

        public Byn(){
            this(0);
        }

        public Byn(int value) {
            this.coins = value;
        }

        public Byn(int rubs, int coins){
            this((rubs * 100) + coins);
        }

        public Byn(Byn byn){
            this(byn.coins);
        }

        public int getRubs(){
            return coins / 100;
        }

        public int getCoins(){
            return coins % 100;
        }

        public Byn add(Byn byn){
            coins += byn.coins;
            return this;
        }

        public Byn sub(Byn byn){
            coins -= byn.coins;
            return this;
        }

        public Byn mul(int value){
            coins *= value;
            return this;
        }

        public Byn mul(double value, RoundingMode mode){
            return mul(value, mode,0);
        }

        public Byn mul(double value, RoundingMode mode, int roundingCoefficient){
            coins = roundPrice(coins * value, mode, roundingCoefficient);
            return this;
        }

        public Byn divide(double value){
            return divide(value, RoundingMode.HALF_UP);
        }

        public Byn divide(double value, RoundingMode mode){
            return divide(value, mode, 0);
        }

        public Byn divide(double value, RoundingMode mode, int roundingCoefficient){
            return mul(1.0 / value, mode, roundingCoefficient);
        }

        private int roundPrice(double value, RoundingMode mode, int roundingCoefficient){
            double coef = Math.pow(10, roundingCoefficient);
            return RoundingFactory.getRoundedPriceFromFactory(value, mode, coef);
        }
        public int getPriceInCoins(){
            return coins;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Byn byn = (Byn) o;

        return coins == byn.coins;
    }

    @Override
    public int hashCode() {
        return coins;
    }

    @Override
        public String toString() {
            return String.format(Constants.BYN_FORMAT, getRubs(), getCoins());
        }

        @Override
        public int compareTo(Byn byn) {
            return this.coins - byn.coins;
        }
    }

