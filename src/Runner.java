import by.gsu.epamlab.beans.Byn;
import by.gsu.epamlab.beans.PricePurchase;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.enums.WeekDaysEnum;
import by.gsu.epamlab.factory.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("src/in.csv"))){
            Map<Purchase, WeekDaysEnum> lastPurchasesMap = new HashMap<>();
            Map<Purchase, WeekDaysEnum> firstPurchaseMap = new HashMap<>();
            Map<WeekDaysEnum, List<Purchase>> weekDayPurchaseMap = new EnumMap<>(WeekDaysEnum.class);
            List<PricePurchase> pricePurchases = new ArrayList<>();
            while (scanner.hasNextLine()) {
                Purchase purchase = PurchasesFactory.getClassFromFactory(scanner.nextLine());
                if (purchase.getClass() == PricePurchase.class){
                    pricePurchases.add((PricePurchase) purchase);
                }
                WeekDaysEnum weekDaysEnum = WeekDaysEnum.valueOf(scanner.nextLine());
                lastPurchasesMap.put(purchase, weekDaysEnum);
                if (!firstPurchaseMap.containsKey(purchase)){
                    firstPurchaseMap.put(purchase, weekDaysEnum);
                }
                if (weekDayPurchaseMap.containsKey(weekDaysEnum)){
                    weekDayPurchaseMap.get(weekDaysEnum).add(purchase);
                }else {
                    List<Purchase> purchases = new ArrayList<>();
                    purchases.add(purchase);
                    weekDayPurchaseMap.put(weekDaysEnum, purchases);
                }
            }

            printMap(lastPurchasesMap, "Last purchase map:");

            System.out.println();

            printMap(firstPurchaseMap, "First purchase map:");
            System.out.println();

            findWeekDay(firstPurchaseMap, new Purchase("bread", 155, 0));
            findWeekDay(lastPurchasesMap, new Purchase("bread", 155, 0));
            findWeekDay(firstPurchaseMap, new Purchase("bread", 170, 0));

            removeEntries(lastPurchasesMap, "meat");
            removeEntries(firstPurchaseMap, WeekDaysEnum.FRIDAY);

            printMap(lastPurchasesMap, "Last purchase map:");
            System.out.println();
            printMap(firstPurchaseMap, "First purchase map:");

            System.out.println();

            printTotalCost(pricePurchases);

            printMap(weekDayPurchaseMap, "Enumerated map: ");

            printEachDayTotalCost(weekDayPurchaseMap);

        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
    }

    private static <K, V> void printMap(Map<K, V> map, String title){
        System.out.println(title);
        for (Map.Entry<K, V> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
    private static <K extends Purchase, V extends WeekDaysEnum> void findWeekDay(Map<K, V> map, K purchase){
        System.out.print("Required weekday: ");
        V requiredWeekDay = null;
        if (map.containsKey(purchase)){
            requiredWeekDay =  map.get(purchase);
        }
        System.out.println(requiredWeekDay != null ? requiredWeekDay : "is not found");
    }

    private static <K extends Purchase, V extends WeekDaysEnum, T> void removeEntries(Map<K, V> map, T elementToDelete){
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        if (elementToDelete.getClass() == String.class){
            while (iterator.hasNext()){
                if (elementToDelete.equals(iterator.next().getKey().getName())){
                    iterator.remove();
                }
            }
        }else {
            while (iterator.hasNext()){
                if (elementToDelete == iterator.next().getValue()){
                    iterator.remove();
                }
            }
        }
    }

    private static void printEachDayTotalCost(Map<WeekDaysEnum, ? extends List<Purchase>> map){
        for (WeekDaysEnum weekDay: WeekDaysEnum.values()) {
            List<Purchase> purchaseList = map.get(weekDay);
            if (purchaseList == null){
                System.out.println(weekDay + " Total cost: 0" );
            }else {
                System.out.print(weekDay + " ");
                printTotalCost(purchaseList);
            }

        }
    }

    private static void printTotalCost(List<? extends Purchase> purchases){
        Byn totalCost = new Byn();
        for (Purchase purchase: purchases) {
            totalCost.add(purchase.getCost());
        }
        System.out.println("Total cost: " + totalCost);
    }
}
