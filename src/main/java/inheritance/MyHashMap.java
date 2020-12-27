package inheritance;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap extends HashMap<Integer, Double> {
    public static void main(String[] args) {
        Map<Integer, Double> mp = new MyHashMap();
    }
}
