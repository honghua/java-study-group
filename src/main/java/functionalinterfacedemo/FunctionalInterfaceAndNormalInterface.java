package functionalinterfacedemo;

public class FunctionalInterfaceAndNormalInterface {
    public static void main(String[] args) {

        // Anonymous class
        MyInterface funcAnonymous = new MyInterface() {
            @Override
            public void show() {
                System.out.println("Anonymous func show.");
            }
        };

        // Lambda
        MyInterface funcLambda = () -> System.out.println("Lambda func show.");

        funcAnonymous.show();
        funcLambda.show();
    }
}


// Types of interface
// 1. Normal interface, i.e., many abstract methods
// 2. Single abstract method --> Functional interface --> lambda expression

// 1. Normal interface, i.e., many abstract methods
interface MyList {
    int size();

    boolean isEmpty();

    boolean contains(Object o);
}

// 2. Single abstract method --> Functional interface
@FunctionalInterface
interface MyInterface {
    void show();
}




