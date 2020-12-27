package generics;

import java.util.ArrayList;
import java.util.List;

public class Intro {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Person("Teddy"));
        list.add("Teddy");

        Person p = (Person) list.get(1);

//        List<Person> personList = new ArrayList<>();
//        personList.add(new Person("Teddy"));
//        personList.add("Teddy");
    }
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}
