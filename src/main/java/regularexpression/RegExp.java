package regularexpression;

import java.util.regex.Pattern;

public class RegExp {
    public static void main(String[] args) {
        System.out.println(Pattern.matches("^agreements\\d+", "agreements123"));
        System.out.println(Pattern.matches("\\/", "/"));
        System.out.println(Pattern.matches("\\\\", "\\"));
        System.out.println(Pattern.matches(".*", "\\"));
        System.out.println("matched: " + Pattern.matches("a.*b", "aaaac(seconds: 1609524020ccdedbbbb"));
        System.out.println("matched: " + Pattern.matches("Effective service duration .* does not match expected contractual duration.*", "Effective service duration (seconds: 1609524020" +
               "does not match expected contractual duration P1Y"));
        System.out.println("/");
        System.out.println("\\");
    }
}
