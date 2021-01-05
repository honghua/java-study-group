package com.guicetutorial;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class MyClassTest {

    @Mock IntSupplier supplier;

    @Inject
    MyClass myClass;

    @Before
    public void setup() {
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        }).injectMembers(this);
    }

    @Test
    public void methodToTest() {
        when(supplier.getIntValue()).thenReturn(1);
        assertThat(myClass.methodToTest()).isEqualTo(1);
    }
}