package unittest.shoppingservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingClientTest {

    @Mock
    ShoppingService service;

    @InjectMocks
    ShoppingClient client;

    @Captor
    ArgumentCaptor<Long> idCaptor;

    private static final long USER_ID_1 = 101L;
    private static final long USER_ID_2 = 201L;

    @Test
    public void getCartTotal_happyCase() throws AuthenticationException {
        Item apple = Item.create("Apple", 1);
        Item lobster = Item.create("Lobster", 19);
        long expectedTotal = apple.getPrice() + lobster.getPrice();

        Cart cart = Cart.Create().add(apple).add(lobster);
        when(service.getCart(USER_ID_1)).thenReturn(cart);

        long total = client.getCartTotal(USER_ID_1);
        assertThat(total).isEqualTo(expectedTotal);
    }

    @Test
    public void getCartTotal_captorStub() throws AuthenticationException {
        Item apple = Item.create("Apple", 1);
        Item lobster = Item.create("Lobster", 19);
        long expectedTotal = apple.getPrice() + lobster.getPrice();

        Cart cart = Cart.Create().add(apple).add(lobster);
        when(service.getCart(idCaptor.capture())).thenReturn(cart);
        client.getCartTotal(idCaptor.capture());

        long id = idCaptor.getValue();
        long total = client.getCartTotal(id);
        assertThat(total).isEqualTo(expectedTotal);
    }

    @Test
    public void getCartTotal_captorVerify() throws AuthenticationException {
        Item apple = Item.create("Apple", 1);
        Item lobster = Item.create("Lobster", 19);
        long expectedTotal = apple.getPrice() + lobster.getPrice();

        Cart cart = Cart.Create().add(apple).add(lobster);
        when(service.getCart(USER_ID_1)).thenReturn(cart);

        client.getCartTotal(USER_ID_1);
        Mockito.verify(service).getCart(idCaptor.capture());

        long id = idCaptor.getValue();
        assertThat(id).isEqualTo(USER_ID_1);
    }
}


