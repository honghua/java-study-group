package com.guicetutorial.singleImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import org.junit.Test;

//Note: No test runner here is used
public class AppTestFake {
    @Inject
    App app;

    @Bind(to = Food.class)
    FakeFood food = new FakeFood();

    @Test
    public void printFood() {
        Injector injector = Guice.createInjector(BoundFieldModule.of(this));

        // method1: ask Injector to instantiate one specified object
        app = injector.getInstance(App.class);

        // method2: ask Injector to instantiate all @Inject annotated object
        injector.injectMembers(this);

        // method3: equivalent to method1 using injectMembers()
        injector.injectMembers(App.class);

        app.printFood();
    }

    // Fake a food implementation
    static class FakeFood implements Food {
        @Override
        public String getName() {
            return "FakeFood";
        }
    }
}