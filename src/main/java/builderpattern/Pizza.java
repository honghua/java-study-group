package builderpattern;

import java.util.EnumSet;
import java.util.Set;

public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM }
    final Set<Topping> toppings;

    abstract static class Builder {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public Builder addTopping(Topping topping) {
            toppings.add(topping);
            return this;
        }

        abstract Pizza build();
    }

    Pizza(Builder builder) {
        toppings = builder.toppings.clone();
    }
}
