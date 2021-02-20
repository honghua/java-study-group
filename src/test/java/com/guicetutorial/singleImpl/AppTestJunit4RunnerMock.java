package com.guicetutorial.singleImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class AppTestJunit4RunnerMock {
    @Inject
    App app;

    @Bind
    DinningService dinningService = mock(DinningService.class);

    @Test
    public void printFood() {
        Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
        when(dinningService.getFood()).thenReturn("apple");

        app.printFood();
    }
}