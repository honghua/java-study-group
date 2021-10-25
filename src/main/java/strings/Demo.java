package strings;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

class Demo {
    public static void main(String[] args) {
        long customerId = 1;
        String CUSTOMER_ID_FIELD = "sold_to_customer_id=";
        String filter = String.format("%s%s", CUSTOMER_ID_FIELD, customerId);
        System.out.println(filter);

        String tmp = "sold_to_customer_id/2402-0548-94125";
        String res = Iterables.get(Splitter.on("/").split(tmp),1);
        System.out.println(res);
    }
}