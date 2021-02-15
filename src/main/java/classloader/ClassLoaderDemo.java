package classloader;

public class ClassLoaderDemo {
    public static void main(String[] args) throws InterruptedException {
        // Static nested class is Not loaded after instantiation
        Outer outer = new Outer(); // nothing printed

        System.out.println("Wait 5 sec ...");
        Thread.sleep(5000);

        // Static Inner class is now loaded after calling static nested class
        Outer.getInstance(); // static block of the nested class is printed, i.e., "Hello Inner"
    }
}

/**
 * Demo static inner class is NOT called at class loader time
 */
class Outer {
    public Outer(){
        System.out.println("Outer class constructor");
    }

    static class Inner {
        static final Outer INSTANCE;
        static {
            System.out.println("Hello Inner");
            INSTANCE = new Outer();
        }
    }

    public static Outer getInstance() {
        return Inner.INSTANCE;
    }
}
