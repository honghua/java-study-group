package com.unittesting.shoppingservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart  {
    private List<Item> list = new ArrayList<>();

    public static Cart Create() {
        return new Cart();
    }
    private Cart() {
    }

    public Cart add(Item o) {
        list.add(o);
        return this;
    }

    public Iterator<Item> iterator() {
        return list.iterator();
    }
}
