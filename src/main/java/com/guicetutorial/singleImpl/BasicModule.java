package com.guicetutorial.singleImpl;

import com.google.inject.AbstractModule;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FoodModule()); // food feature
    }
}
