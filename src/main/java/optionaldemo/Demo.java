package optionaldemo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class Demo {
    private static TreeSet<String> friendSet = new TreeSet<>();

    static void optionalTest() {
        List<String> friends = Arrays.asList("Jenny", "Tianshu", "Tianyu", "Teddy");

//        String firstFriend = friends.stream()
//                .filter(s -> s.startsWith("A"))
//                .findFirst()
//        addFriend(firstFriend);
        Optional<String> optionalFirstFriend = friends.stream()
                .filter(s -> s.startsWith("A"))
                .findFirst();

        addFriend(optionalFirstFriend.orElse("Jenny"));
    }

    private static boolean addFriend(String name) {
        return friendSet.add(name);
    }

    public static void main(String[] args) throws Exception {
//        optionalTest();
//        System.out.println(friendSet);

//        String name = "Jenny";
        String name = null;

        // create optional
        Optional<String> optionalname = Optional.ofNullable(name);
        System.out.println(optionalname);

//        Optional<String> emptyOptional = Optional.empty();


        // get value from optional
        String name2 = optionalname.orElse("");

        // handle exception
        optionalname.orElseThrow(() -> new Exception(""));
    }
}