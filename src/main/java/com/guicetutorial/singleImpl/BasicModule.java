package com.guicetutorial.singleImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Food.class).to(SeaFood.class);
    }

}
