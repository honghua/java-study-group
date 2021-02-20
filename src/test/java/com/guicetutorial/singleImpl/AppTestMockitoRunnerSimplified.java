package com.guicetutorial.singleImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTestMockitoRunnerSimplified {
    @Inject
    App app;

    // @Bind: no need Bind annotation
    @Mock
    DinningService dinningService;

    @Test
    public void printFood() {
        // MockitoJunitRunner automatically creates injection points, i.e.,
        // No need of createInjector and BoundFieldModule.of
//        Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);

        when(dinningService.getFood()).thenReturn("apple");

        app.printFood();
    }
}