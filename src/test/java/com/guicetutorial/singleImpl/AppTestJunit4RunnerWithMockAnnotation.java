package com.guicetutorial.singleImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class AppTestJunit4RunnerWithMockAnnotation {
    // @Rule: initialize all @Mock annotated objects: this is equivalent to
    // DinningService dinningService = mock(DinningService.class);
    // No need to add this rule if running with MockitoJunitRunner.
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Inject
    App app;

    @Bind @Mock
    DinningService dinningService;


    @Test
    public void printFood() {
        when(dinningService.getFood()).thenReturn("apple");
        Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);

        app.printFood();
    }
}