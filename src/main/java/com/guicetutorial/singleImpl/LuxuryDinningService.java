package com.guicetutorial.singleImpl;

import com.google.inject.Inject;

public class LuxuryDinningService {

    @Inject
    private Food food;

//    @Inject
//    public DinningService(@MyAnnotations.BasicDinning Food food) {
//        this.food = food;
//    }

    public String getFood() {
        return food.getName();
    }
}
