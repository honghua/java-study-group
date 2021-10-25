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
        System.out.println(new NyPizza.Builder().addTopping(Pizza.Topping.MUSHROOM).getClass());
//        NyPizza pizza =
        System.out.println(new NyPizza.Builder().addTopping(Pizza.Topping.MUSHROOM).build().getClass());
    }
}