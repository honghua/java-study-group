package streamdemo;

import com.google.common.base.Ascii;

import java.util.List;
import java.util.stream.Stream;

public class StreamFilter {
    public static void main(String[] args) {
        List<String> names = List.of("Jack", "Teddy");
        Stream s = names.stream();
        System.out.println(s.allMatch(name -> names.contains(name)));
        System.out.println(s.findFirst());
        s.forEach(System.out::println);s.forEach(System.out::println); s.forEach(System.out::println); s.forEach(System.out::println);


    }
}
