package unittest.shoppingservice;

import javax.naming.AuthenticationException;
import java.util.Map;

public interface ShoppingService {
    Map<Item, Integer> getInventory(String storeId);

    Cart getCart(long userId) throws AuthenticationException;

    Cart shop(Item[] items);
}
