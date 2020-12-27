package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildCardDemo {
    public static void main(String[] args) {
        List<Actor> actorList = Arrays.asList(new Actor("Teddy"));
        show(actorList);



        // upper bounded wildcard:
        // <? extends Actor> 不能放 Actor, 因为是所有 Actor 的子集
        List<? extends Actor> subList = Arrays.asList(new Actor("Teddy"));
//        subList.add(new Person("Jenny")); // compile error
        subList.get(0);

        // lower bounded widecard
        // <? super Actor> 可以放 Actor， 但拿出来的不一定是Actor!
        List<? super Actor> superList = new ArrayList<>();
        superList.add(new Actor("Teddy"));
        superList.get(0);
    }


    public static void show(List<? extends People> personList) {
        personList.forEach(People::show);
    }
}

abstract class People {
    String name;
    public People(String name) {
        this.name = name;
    }
    abstract void show();
}

class Actor extends People {
    public Actor(String name) {
        super(name);
    }

    @Override
    void show() {
        System.out.println(name + " is acting.");
    }
}
