package builderpattern;

class NyPizza extends Pizza {
    public static class Builder extends Pizza.Builder {

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
        NyPizza pizza = (NyPizza) new NyPizza.Builder().addTopping(Pizza.Topping.MUSHROOM).build();
    }
}