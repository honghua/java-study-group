package streamdemo;

//import com.google.auto.value.AutoValue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollect {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                User.create("Teddy", Gender.MALE, 200),
                User.create("Tianshu", Gender.MALE, 200),
                User.create("Tianyu", Gender.FEMALE, 40),
                User.create("Jenny", Gender.FEMALE, 500)
        );

        Map<Gender, Double> averageSalaryByGender = users.stream()
                .collect(
                        Collectors.groupingBy(User::gender,
                                Collectors.averagingDouble(User::salary))
                );

        System.out.println("Female: " + averageSalaryByGender.get(Gender.FEMALE) + "\n" +
                "Male: " + averageSalaryByGender.get(Gender.MALE));


        int cost = users.stream()
                .reduce(0, (partialSol,  nextE) -> partialSol + nextE.salary(), Integer::sum);
        System.out.println(cost);
    }
}

//@AutoValue
abstract class User {
    abstract String name();
    abstract Gender gender();
    abstract int salary();
    public static User create(String name, Gender gender, int salary){
//        return new AutoValue_User(name, gender, salary);
        return new User() {

            @Override
            String name() {
                return null;
            }

            @Override
            Gender gender() {
                return null;
            }

            @Override
            int salary() {
                return 0;
            }
        };
    }
}

enum Gender {
    MALE,
    FEMALE;
}