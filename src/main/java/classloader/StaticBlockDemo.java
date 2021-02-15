package classloader;

public class StaticBlockDemo {
    // This is to demo the static block is ONLY executed ONCE for multiple instantiations
    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5000);
        StaticBlockClass staticBlockClass1 = new StaticBlockClass();
        StaticBlockClass staticBlockClass2 = new StaticBlockClass();
        StaticBlockClass staticBlockClass3 = new StaticBlockClass();
    }
}
class StaticBlockClass {
    private static final String name; // static block
    public StaticBlockClass() { // constructor
        System.out.println("hello in constructor");
    }
    static { // static block
        name = "T";
        System.out.print(name + " ");
        System.out.println("Hello in static block");
    }
}