package functionalinterfacedemo;

@FunctionalInterface
interface IntUnaryFunction {
	int apply(int x);
}

class TenX implements IntUnaryFunction {
	@Override
	public int apply(int x) {
		return 10 * x;
	}
}

public class HoFDemo {
	public static int doTwice(IntUnaryFunction f, int x) {
		return f.apply(f.apply(x));
	}
	public static void main(String[] args) {
		IntUnaryFunction tenXClass = new TenX();
		IntUnaryFunction tenXLambda = x -> 10 * x;

		System.out.println(doTwice(tenXClass, 2));
		System.out.println(doTwice(tenXLambda, 2));
		System.out.println(doTwice(HoFDemo::tenX, 2));
	}

	private static int tenX(int x) {
		return 10 * x;
	}
}
