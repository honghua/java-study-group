package streamdemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiDemo {
    // intermediate operation
    // terminal operation: forEach, collect, reduce

    public static void main(String[] args) {
        List<String> friends = Arrays.asList("Jenny", "Tianshu", "Tianyu", "Teddy");

        friends.stream()  // created a stream
                .filter(StreamApiDemo::myCondition) // intermediate operations
                .forEach(System.out::println);
    }

    private static boolean myCondition(String s) {
        System.out.println("hello");
        if (s.startsWith("J")) {
            return  true;
        }
        return false;
    }




    // IntStream
    private static void demo1IntStream(){
        // Integer Stream
        IntStream.range(1, 10)
                .forEach(System.out::print);
        System.out.println();

        // Stream with skip
        IntStream.range(1, 10)
                .skip(5)
                .forEach(x -> System.out.println(x));

        // Sum
        IntStream.range(1, 5)
                .sum();


        // Stream.of, sorted and findFirst
        Stream.of("Qyy", "Tianshu", "Teddy")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);


        // Stream from Array, sort, filter
        String[] names = {"Qyy", "Tianshu", "Teddy"};
        Arrays.stream(names)
                .filter(x -> x.startsWith("T"))
                .sorted()
                .forEach(System.out::println);


        // average of squares of an int array
        Arrays.stream(new int[]{2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        List<Integer> list = Arrays.asList(1, 2, 3);

        Predicate<Integer> predicateBigger = i -> i < 2;
        System.out.println(
                "Look here: " +
                list.stream().filter(predicateBigger.negate().and(i -> i > 3)).findFirst().orElse(100)
        );
    }

    private static boolean isDivisible(int i) {
        System.out.println("isDivisible");
        return i % 5 == 0;
    }
}
