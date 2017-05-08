package by.gsu.epamlab;

import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.enums.WeekDaysEnum;

import java.util.Map;

public interface EntryChecker<K, V> {
    boolean check(Map.Entry<K, V>  entry);
}
