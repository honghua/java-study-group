package hashMapdemo;

import com.google.common.collect.ImmutableList;

import java.util.*;

public class EqualsDemo {
    public static void main(String[] args) {
        Person p1 = new Person("Teddy");
        Person p2 = new RichPerson("Teddy");
        RichPerson p3 = new RichPerson("Teddy");
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p1));
        System.out.println(p3.equals(null));

        System.out.println(p1.getClass() + ";" + p2.getClass() + ";" + p3.getClass());

        List<Integer> list = Arrays.asList(1);
        List<Integer> list1 = new ArrayList<>(list);
        List<Integer> list2 = ImmutableList.<Integer>of(1);
        LinkedList<Integer> list3 = new LinkedList<>(list);

        System.out.println(list.equals(list1));
        System.out.println(list1.equals(list2));
        System.out.println(list.equals(list2));
        System.out.println(list2.equals(list3));
        System.out.println(list3.equals(list2));
    }
}

class Person {
    public String name;
    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof Person)){
            return false;
        }
        Person another = (Person) obj;
        // handle null
        return another.name == name || (name != null && another.name.equals(name));
    }
}

class RichPerson extends Person {
    public RichPerson(String name) {
        super(name);
    }
}
