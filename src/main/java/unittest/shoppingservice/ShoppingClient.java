package unittest.shoppingservice;

import javax.naming.AuthenticationException;
import java.util.Iterator;

public class ShoppingClient {
    private final ShoppingService shoppingService;

    public ShoppingClient(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    public long getCartTotal(long userId) {
        long total = 0;
//        Iterator<Item> it;
//        while ((it= getCart(userId).iterator()).hasNext()) {
//            total += it.next().getPrice();
//        }
        Iterator<Item> it = getCart(userId).iterator();
        while (it.hasNext()) {
            total += it.next().getPrice();
        }
        
//        for (Item item : getCart((userId))) {
//
//        }
        return total;
    }

    private Cart getCart(long userId) {
        try {
            return shoppingService.getCart(userId);
        } catch (AuthenticationException e) {
            return Cart.Create();
        }
    }
}
