package com.guicetutorial.singleImpl;

import com.google.inject.Inject;
import javax.annotation.Nullable;

public class DinningService {

    private Food food;

    @Inject
    public DinningService(Food food) {
        this.food = food;
    }

    public String getFood() {
        return food.getName();
    }
}
