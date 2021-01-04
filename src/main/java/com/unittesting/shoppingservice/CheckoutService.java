package com.unittesting.shoppingservice;

import com.unittesting.shoppingservice.Item;

public interface CheckoutService {

    boolean checkout(Item... items);
}
