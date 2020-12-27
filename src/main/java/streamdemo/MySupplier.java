package streamdemo;

import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MySupplier {
    public static void main(String[] args) {
        MyRandomIntSupplier supplier = new MyRandomIntSupplier(1, 2, 3);
        final int count = (int)1e5;
        int[] array = new int[count];
        IntStream.range(0, count).forEach(
                i -> array[i] = supplier.get()
        );
        IntSummaryStatistics intSummaryStatistics = IntStream.of(array).summaryStatistics();
        System.out.println(intSummaryStatistics.getCount() + ";" + intSummaryStatistics.getAverage());

        MyRandomSupplier<Integer> integerMyRandomSupplier = new MyRandomSupplier<>(1, 2, 3);
        IntStream.range(0, count).forEach(
                i -> array[i] = supplier.get()
        );
        intSummaryStatistics = IntStream.of(array).summaryStatistics();
        System.out.println(intSummaryStatistics.getCount() + ";" + intSummaryStatistics.getAverage());
    }
}

class MyRandomIntSupplier implements Supplier<Integer> {
    private static Random random = new Random();
    private Integer[] integers;
    public MyRandomIntSupplier(Integer... integers) {
        this.integers = integers;
    }
    @Override
    public Integer get() {
        int randomIndex = random.nextInt(integers.length);
        return integers[randomIndex];
    }
}

class MyRandomSupplier<T> implements Supplier<T> {
    private static Random random = new Random();
    private T[] data;
    public MyRandomSupplier(T... data) {
        this.data = data;
    }

    @Override
    public T get() {
        int randomIndex = random.nextInt(data.length);
        return data[randomIndex];
    }
}
