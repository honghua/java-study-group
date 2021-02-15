package com.guicetutorial.singleImpl.naivedemo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class SandwichModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    CheeseSandwich provide(Bread bread, Cheese cheese) {
        return new CheeseSandwich(bread, cheese);
    }
}
