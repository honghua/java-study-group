package com.guicetutorial.singleImpl;

import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import org.junit.Rule;
import org.junit.Test;
import shared.GuiceRule;

//Note: No test runner here is used
public class AppTestFakeWithGuiceRule {

    @Rule
    public GuiceRule guiceRule = new GuiceRule(BoundFieldModule.of(this));

    @Inject
    App app;

    @Bind(to = Food.class)
    FakeFood food = new FakeFood();

    @Test
    public void printFood() {
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