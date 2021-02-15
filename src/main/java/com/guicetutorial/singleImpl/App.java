package com.guicetutorial.singleImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class App {
    private DinningService dinningService;

    @Inject
    public App(DinningService dinningService) {
        this.dinningService = dinningService;
    }

    public void printFood() {
        String food = dinningService.getFood();
        System.out.println("Teddy is eating " + food + ".");
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());
        App app = injector.getInstance(App.class);
        app.printFood();
    }
}
