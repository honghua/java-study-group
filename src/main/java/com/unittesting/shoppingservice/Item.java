package com.unittesting.shoppingservice;

import com.google.auto.value.AutoValue;

/**  price in Dollar for each item */

@AutoValue
public abstract class Item {
    /**  price in Dollar for each item */
    public static Item create(String name, int price) {
        return new AutoValue_Item(name, price);
    }

    public abstract String getName();
    public abstract int getPrice();
}
