import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.WeekDaysEnum;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("src/in.csv"))){
            Map<Purchase, WeekDaysEnum> purchasesMap = new HashMap<>();
            while (scanner.hasNextLine()){
            }


        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
    }
}
