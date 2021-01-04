package com.unittesting.shoppingservice;

import com.unittesting.shoppingservice.Cart;
import com.unittesting.shoppingservice.ShoppingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingServiceTest {

    @Mock
    ShoppingService service;

    @Test
    public void getInventory() {
    }

    @Test
    public void getCart() throws AuthenticationException {
        Cart cart = Cart.Create();
        when(service.getCart(1)).thenReturn(cart);
        assertEquals(cart, service.getCart(1));
    }
}