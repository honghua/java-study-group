package generics.builderpattern;

import java.util.EnumSet;
import java.util.Set;

public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM }
    final Set<Topping> toppings;

    /**
     * Naive implementation:
     * The below code snippet would require Type casting when a subtype Pizza class calls addTopping.
     * Because addTopping returns Pizza.Builder, while for a subtype Pizza class a subtype is preferred.
     */
//    abstract static class Builder {
//        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
//        public Builder addTopping(Topping topping) {
//            toppings.add(topping);
//            return this;
//        }
//
//        abstract Pizza build();
//    }


    /**
     * Improved implementation to use generics with recursive type:
     */
    abstract static class Builder<T extends Builder> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public  T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        protected abstract T self();

        abstract Pizza build();
    }

    Pizza(Builder builder) {
        toppings = builder.toppings.clone();
    }
}
