package generics;

public class GenericFunctionDemo {
    public static void main(String[] args) {
        String[] strings = {"a", "b", "z"};
        String stringMax = MyUtility.max(strings);
        System.out.println(stringMax);

        Integer[] integers = {1,8, 2};
        Integer integerMax = MyUtility.max(integers);
        System.out.println(integerMax);

//        Object[] objects = new Object[]{};
//        System.out.println(MyUtility.max(objects));

        Comparable[] comparables = new Comparable[]{Integer.MAX_VALUE, "Teddy"};
//        MyUtility.<Integer>max(comparables);
    }
}

class MyUtility {
//    // option1: direct generics
//    public static <E> E max(E[] elements) {
//
//    }
//
//     option2: Comparable
//    public static Comparable max(Comparable[] elements) {
//        int maxIndex = 0;
//        for (int i = 1; i < elements.length; i += 1) {
//            if (elements[i].compareTo(elements[maxIndex]) > 0) {
//                maxIndex = i;
//            }
//        }
//        return elements[maxIndex];
//    }
//
//    // option3: Comparable<T>
//    public static <T> T max(Comparable<T>[] elements) {
//    }
//
//    // option4: bounded generics
//    public static <T extends Comparable> T max(T[] elements) {
//    }
//
//    // option5: best way
//    public static <T extends Comparable<T>> T max(T[] elements) {

    public static <T extends Comparable<T>> T max(T[] elements) {
        int maxIndex = 0;
        for (int i = 1; i < elements.length; i += 1) {
            if (elements[i].compareTo(elements[maxIndex]) > 1) {
                maxIndex = i;
            }
        }
        return elements[maxIndex];
    }
}