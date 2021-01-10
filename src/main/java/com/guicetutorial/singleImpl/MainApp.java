package com.guicetutorial.singleImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class MainApp {
    private final DinningService dinningService;

    @Inject
    public MainApp(DinningService dinningService) {
        this.dinningService = dinningService;
    }

    void run() {
        Person guest = Person.create("Teddy");
        String food = dinningService.getFoodForGuest(guest);
        System.out.println(guest.name() + " is eating " + food + ".");
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());
        MainApp app = injector.getInstance(MainApp.class);
        app.run();
    }
}
