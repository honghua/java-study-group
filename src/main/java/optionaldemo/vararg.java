package optionaldemo;

import java.util.ArrayList;
import java.util.List;

public class vararg {
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        addStrings("Tianshu", "Jenny");
        System.out.println(list);
    }

    private static void addStrings(String... strings) {
        System.out.println(strings.getClass());
        String[] array = strings;

        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
    }
}
