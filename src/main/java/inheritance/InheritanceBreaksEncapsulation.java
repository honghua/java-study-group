package inheritance;

import java.lang.reflect.Array;
import java.util.*;

public class InheritanceBreaksEncapsulation {
    public static void main(String[] args) {
        // example-1: from CS-61B
//        MyDog dog = new MyDog();
//        dog.barkMany(3);

        // example-2: from Effective Java
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount());
    }
}

class Dog {
    void bark() {
        barkMany(1);
    }

    void barkMany(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("bark!");
        }
    }
}

class MyDog extends Dog {
    @Override
    void barkMany(int n) {
        for (int i = 0; i < n; i++) {
            bark();
            System.out.println();
        }
    }
}

/**
 * Example class from Effective Java, demo inheritance breaks encapsulation.
 * @param <E>
 */
class InstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}


