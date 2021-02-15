package com.guicetutorial.singleImpl.naivedemo;

import com.google.inject.Guice;
import com.google.inject.Inject;

public class CheeseSandwich {
    private Bread bread;
    private Cheese cheese;

    @Inject
    CheeseSandwich(Bread bread, Cheese cheese) {
        this.bread = bread;
        this.cheese = cheese;
    }

    public static void main(String[] args) {
        // 手动 创建
//        CheeseSandwich sandwich = new CheeseSandwich(new Bread(), new Cheese());

        // Guice inject
        CheeseSandwich sandwich = Guice.createInjector().getInstance(CheeseSandwich.class);
        System.out.println(sandwich.bread);
        System.out.println(sandwich.cheese);
    }
}

class Bread {
}

class Cheese {
}