package com.guicetutorial.singleImpl;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DinningService {

    private Food food;

    @Inject
    public DinningService(Provider<Food> food) {
        this.food = food.get();
    }

    public String getFoodForGuest(Person person) {
        return food.getName();
    }
}
