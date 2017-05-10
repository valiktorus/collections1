import by.gsu.epamlab.checker.EntryChecker;
import by.gsu.epamlab.beans.Byn;
import by.gsu.epamlab.beans.PricePurchase;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.enums.WeekDay;
import by.gsu.epamlab.factory.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader(GeneralConstants.FILE_NAME))){
            Map<Purchase, WeekDay> lastPurchasesMap = new HashMap<>();
            Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> enumeratedMap = new EnumMap<>(WeekDay.class);
            List<PricePurchase> pricePurchases = new LinkedList<>();
            while (scanner.hasNextLine()) {
                Purchase purchase = PurchasesFactory.getPurchaseFromFactory(scanner.nextLine());
                if (purchase.getClass() == PricePurchase.class){
                    pricePurchases.add((PricePurchase) purchase);
                }
                WeekDay weekDay = WeekDay.valueOf(scanner.nextLine());
                lastPurchasesMap.put(purchase, weekDay);
                if (!firstPurchaseMap.containsKey(purchase)){
                    firstPurchaseMap.put(purchase, weekDay);
                }
                if (!enumeratedMap.containsKey(weekDay)){
                    enumeratedMap.put(weekDay, new LinkedList<>());
                }
                enumeratedMap.get(weekDay).add(purchase);
            }

            printMap(lastPurchasesMap, GeneralConstants.LAST_PURCHASE_MAP);
            printMap(firstPurchaseMap, GeneralConstants.FIRST_PURCHASE_MAP);

            findAndPrintValue(firstPurchaseMap, GeneralConstants.FIRST_BREAD_PURCHASE, GeneralConstants.FIRST_PURCHASE_MAP);
            findAndPrintValue(lastPurchasesMap, GeneralConstants.FIRST_BREAD_PURCHASE, GeneralConstants.LAST_PURCHASE_MAP);
            findAndPrintValue(firstPurchaseMap, GeneralConstants.SECOND_BREAD_PURCHASE, GeneralConstants.FIRST_PURCHASE_MAP);

            removeEntries(lastPurchasesMap, new EntryChecker<Purchase, WeekDay>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return GeneralConstants.MEAT.equals(entry.getKey().getName());
                }
            });
            removeEntries(firstPurchaseMap, new EntryChecker<Purchase, WeekDay>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return WeekDay.FRIDAY == entry.getValue();
                }
            });

            printMap(lastPurchasesMap, GeneralConstants.LAST_PURCHASE_MAP);
            printMap(firstPurchaseMap, GeneralConstants.FIRST_PURCHASE_MAP);

            printTotalCost(pricePurchases);

            printMap(enumeratedMap, GeneralConstants.ENUMERATED_MAP);

            printEachDayTotalCost(enumeratedMap);

            findAndPrintValue(enumeratedMap, WeekDay.MONDAY, GeneralConstants.ENUMERATED_MAP);

        } catch (FileNotFoundException e) {
            System.err.println(GeneralConstants.FILE_NOT_FOUND);
        }
    }

    private static <K, V> void printMap(Map<K, V> map, String title){
        System.out.println(title);
        for (Map.Entry<K, V> entry: map.entrySet()) {
            System.out.printf(GeneralConstants.PRINT_FORMAT,entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    private static <K, V> void findAndPrintValue(Map<K, V> map, K key, String mapName){
        System.out.printf(GeneralConstants.FIND_VALUE_FORMAT, key, mapName);
        V requiredValue = map.get(key);
        System.out.println(requiredValue != null ? requiredValue : GeneralConstants.IS_NOT_FOUND);
    }

    private static <K extends Purchase, V extends WeekDay>  void removeEntries(Map<K, V> map, EntryChecker<K, V> checker){
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            if(checker.check(iterator.next())){
                iterator.remove();
            }
        }
    }

    private static void printEachDayTotalCost(Map<WeekDay, ? extends List<Purchase>> map){
        for (WeekDay weekDay: WeekDay.values()) {
            List<Purchase> purchaseList = map.get(weekDay);
            if (purchaseList == null){
                System.out.println(weekDay + GeneralConstants.TOTAL_COST_0);
            }else {
                System.out.print(weekDay + GeneralConstants.SPACE);
                printTotalCost(purchaseList);
            }
        }
    }

    private static void printTotalCost(List<? extends Purchase> purchases){
        Byn totalCost = new Byn();
        for (Purchase purchase: purchases) {
            totalCost.add(purchase.getCost());
        }
        System.out.println(GeneralConstants.TOTAL_COST + totalCost);
    }
}
