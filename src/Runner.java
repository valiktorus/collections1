import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.enums.WeekDaysEnum;
import by.gsu.epamlab.factory.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try(Scanner firstPurchaseMapScanner = new Scanner(new FileReader("src/in.csv"));
            Scanner lastPurchaseMapScanner = new Scanner(new FileReader("src/in.csv"))){
            Map<Purchase, WeekDaysEnum> firstPurchasesMap = new HashMap<>();
            while (firstPurchaseMapScanner.hasNextLine()) {
                Purchase purchase = PurchasesFactory.getClassFromFactory(firstPurchaseMapScanner.nextLine());
                firstPurchasesMap.put(purchase, WeekDaysEnum.valueOf(firstPurchaseMapScanner.nextLine()));
            }
            System.out.println("First purchase map:");

            printMap(firstPurchasesMap);

            Map<Purchase, WeekDaysEnum> lastPurchaseMap = new HashMap<>();
            while (lastPurchaseMapScanner.hasNextLine()){
                Purchase purchase = PurchasesFactory.getClassFromFactory(lastPurchaseMapScanner.nextLine());
                if (!lastPurchaseMap.containsKey(purchase)){
                    lastPurchaseMap.put(purchase, WeekDaysEnum.valueOf(lastPurchaseMapScanner.nextLine()));
                }
            }
            System.out.println("Last purchase map:");
            printMap(lastPurchaseMap);


        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
    }

    private static <K extends Purchase, V extends WeekDaysEnum> void printMap(Map<K, V> map){
        for (Map.Entry<K, V> entry: map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
