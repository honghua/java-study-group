package functionalinterfacedemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        // Explicit Consumer class
        System.out.println("Explicit Consumer demo");
        list.forEach(new MyConsumer());


        // Anonymous class
        System.out.println("\nAnonymous Consumer demo");
        list.forEach(new Consumer<Integer>(){
            @Override
            public void accept(Integer integer) {
                System.out.print(integer + "; ");
            }
        });

        // Lambda function in full format
        System.out.println("\nLambda function in full format");
        Consumer<Integer> consumerFull = (Integer i) -> {
            System.out.print(i + "; ");
        };
        list.forEach(consumerFull);

        // Lambda function simplified
        System.out.println("\nLambda function in simplified format");
        Consumer<Integer> consumer = i -> System.out.print(i + "; ");
        list.forEach(consumer);

        // Inline lambda
        System.out.println("\nInline lambda");
        list.forEach(i -> System.out.print(i + "; "));

        // Static method reference
        System.out.println("\nBuild in Static method call");
        list.forEach(System.out::print);

        // Static method reference using custom static helper methods
        System.out.println("\nCustom Static method ");
        list.forEach(LambdaDemo::formatPrint);
    }

    private static void formatPrint(Integer i) {
        System.out.print(
                String.format("  Formatted print %s: \n", i)
        );
    }
}

class MyConsumer implements Consumer<Integer> {
    @Override
    public void accept(Integer integer) {
        System.out.print(integer + "; ");
    }
}

