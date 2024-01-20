package org.example;
import org.springframework.stereotype.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingServiceTest {

    private static final String VALID_PRODUCT_NAME = "ValidProduct";
    private static final double VALID_PRODUCT_PRICE = 10.0;
    private static final String INVALID_PRODUCT_NAME = "InvalidProduct";
    private static final int VALID_PRODUCT_ID = 1;

    @Autowired
    private ShoppingService shoppingService;

    @MockBean
    private Warehouse warehouse;

    private Product validProduct;
    private OrderPosition orderPosition;
    private Customer customer;

    @BeforeEach
    void setUp() {
        validProduct = new Product(VALID_PRODUCT_ID, VALID_PRODUCT_NAME, VALID_PRODUCT_PRICE);
        orderPosition = new OrderPosition(1, new Product(1, "milk", 20.00), 20);
        customer = new Customer("ola", 2);
        when(warehouse.getInstance()).thenReturn(warehouse);
    }

    @Test
    void shouldFinalizePurchaseWhenBalanceIsSufficient() {
        // Given
        double walletBalance = 100.0;
        customer.setWalletBalance(walletBalance);
        customer.getShoppingCart().setProducts(Arrays.asList(VALID_PRODUCT_NAME));

        when(warehouse.getOrderPositionsList()).thenReturn(Arrays.asList(orderPosition));

        // When
        String result = shoppingService.finalizePurchase(customer);

        // Then
        assertEquals("Purchase successful. Remaining balance: 90.0", result);
        assertEquals(walletBalance - VALID_PRODUCT_PRICE, customer.getWalletBalance());
    }
    @Test
    void shouldHandleNegativeProductAmountInCart() {
        // Given
        double walletBalance = 100.0;
        customer.setWalletBalance(walletBalance);
        customer.getShoppingCart().setProducts(Arrays.asList(VALID_PRODUCT_NAME));
        OrderPosition negativeAmountOrderPosition = new OrderPosition(1, validProduct, -1);

        when(warehouse.getOrderPositionsList()).thenReturn(Arrays.asList(negativeAmountOrderPosition));

        // When
        String result = shoppingService.finalizePurchase(customer);

        // Then
        assertEquals("Purchase failed. Invalid product amount in the shopping cart.", result);
        assertEquals(walletBalance, customer.getWalletBalance());
    }

    @Test
    void shouldHandleZeroProductAmountInCart() {
        // Given
        double walletBalance = 100.0;
        customer.setWalletBalance(walletBalance);
        customer.getShoppingCart().setProducts(Arrays.asList(VALID_PRODUCT_NAME));
        OrderPosition zeroAmountOrderPosition = new OrderPosition(1, validProduct, 0);

        when(warehouse.getOrderPositionsList()).thenReturn(Arrays.asList(zeroAmountOrderPosition));

        // When
        String result = shoppingService.finalizePurchase(customer);

        // Then
        assertEquals("Purchase failed. Product amount in the shopping cart must be greater than zero.", result);
        assertEquals(walletBalance, customer.getWalletBalance());
    }

    @Test
    void shouldHandleNegativeWalletBalance() {
        // Given
        double negativeWalletBalance = -10.0;
        customer.setWalletBalance(negativeWalletBalance);
        customer.getShoppingCart().setProducts(Arrays.asList(VALID_PRODUCT_NAME));

        // When
        String result = shoppingService.finalizePurchase(customer);

        // Then
        assertEquals("Purchase failed. Invalid wallet balance.", result);
        assertEquals(negativeWalletBalance, customer.getWalletBalance());
    }

    @Test
    void shouldHandleNullWarehouse() {
        // Given
        double walletBalance = 100.0;
        customer.setWalletBalance(walletBalance);
        customer.getShoppingCart().setProducts(Arrays.asList(VALID_PRODUCT_NAME));

        when(warehouse.getInstance()).thenReturn(null);

        // When
        String result = shoppingService.finalizePurchase(customer);

        // Then
        assertEquals("Purchase failed. Warehouse is null.", result);
        assertEquals(walletBalance, customer.getWalletBalance());
    }



}
