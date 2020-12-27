package streamdemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMap {
    public static void main(String[] args) {
        List<String> rich = Arrays.asList("Teddy", "Jenny");
        List<String> poor = Arrays.asList("Tianshu", "Honghua");

        List<String> richPeople = rich.stream()
                .map(name -> "Rich-" + name)
                .collect(Collectors.toList());


        List<List<String>> listList = Arrays.asList(rich, poor);
        List<List<List<String>>> lists = Arrays.asList(listList);
        System.out.println(listList);

        System.out.println(
                lists.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList())
        );

//        System.out.println(rich.stream()
//                .map(s -> "Rich-" + s)
//                .collect(Collectors.toList()));
//
//
//
//        List<List<String>> listOfList = Arrays.asList(rich, poor);
//        List<String> flatAll = listOfList.stream()
//                .flatMap(list -> list.stream())
//                .collect(Collectors.toList());
//        System.out.println(flatAll);
    }
}
