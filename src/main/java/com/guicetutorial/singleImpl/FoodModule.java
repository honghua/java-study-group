package com.guicetutorial.singleImpl;

import com.google.inject.AbstractModule;

public class FoodModule extends AbstractModule {
    @Override
    protected void configure() { // shopping list
        bind(Food.class).annotatedWith(MyAnnotations.BasicDinning.class).to(FastFood.class);
        bind(Food.class).annotatedWith(MyAnnotations.LuxuryDinning.class).to(SeaFood.class);
        bind(Food.class).to(FastFood.class);
    }
}
