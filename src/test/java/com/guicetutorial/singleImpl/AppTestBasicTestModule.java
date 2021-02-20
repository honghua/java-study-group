package com.guicetutorial.singleImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;


public class AppTestBasicTestModule {

    @Test
    public void printFood() {
        Injector injector = Guice.createInjector(new TestModule());
        App app = injector.getInstance(App.class);
        app.printFood();
    }
}
class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Food.class).to(AppTestFake.FakeFood.class);
    }
}

