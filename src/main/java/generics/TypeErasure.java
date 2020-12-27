package generics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TypeErasure {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // run time 插入： after type erasure
        try {
            list.getClass().getMethod("add", Object.class).invoke(list, "hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(list.get(0));
    }
}
