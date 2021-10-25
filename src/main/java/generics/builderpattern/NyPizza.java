package generics.builderpattern;

class NyPizza extends Pizza {
    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

    }

    private NyPizza(Builder builder) {
        super(builder);
    }
}

class Main {
    public static void main(String[] args) {
        // compile error if using naive implementation in Pizza.java
        NyPizza pizza = new NyPizza.Builder().addTopping(Pizza.Topping.MUSHROOM).addTopping(Pizza.Topping.HAM).build();
    }
}