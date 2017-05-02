package by.gsu.epamlab;

public class RoundingFactory {
    public enum RoundingMode{
        UP{
            int roundPrice(double value, double coef){
                return (int)(Math.ceil(value / coef) * coef);
            }
        },
        DOWN{
            int roundPrice(double value, double coef){
                return (int)(Math.floor(value / coef) * coef);
            }
        },
        HALF_UP{
            int roundPrice(double value, double coef){
                return (int)(Math.round(value / coef) * coef);
            }
        };
        abstract int roundPrice(double value, double coef);
    }
    public static int getRoundedPriceFromFactory(double value, java.math.RoundingMode roundingMode, double coef){
        return RoundingMode.valueOf(roundingMode.name()).roundPrice(value,coef);
    }
}
