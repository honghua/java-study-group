package com.guicetutorial;

import com.google.inject.Inject;

public class MyClass {
    @Inject
    private IntSupplier intSupplier;

    public int methodToTest() {
        return intSupplier.getIntValue();
    }
}

class IntSupplier {
    public int getIntValue() {
        return 1;
    }
}
