package streamdemo;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
//        reduce (合成) --> sum
        int sum = numbers.stream()
                .reduce(0, (partialSol, nextElement)-> partialSol + nextElement);
        System.out.println(sum);


        List<String> strings = Arrays.asList("a", "b", "c");
        String result = strings.stream()
                .reduce("", (partialSol, nextElement) -> partialSol  + nextElement);
        System.out.println(result);
    }
}


