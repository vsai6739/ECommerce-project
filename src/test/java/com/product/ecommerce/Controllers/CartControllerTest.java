package com.product.ecommerce.Controllers;

import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Services.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CartControllerTest {
    @Autowired
    CartController cartController;
    @MockBean
    CartService cartService;

    @Test
    void addToCart() {
        // Arrange
        Long productId = 10L;
        int quantity = 10;
        doNothing().when(cartService).addProductToCart(productId, quantity);

        // Act
        ResponseEntity<String> responseEntity = cartController.addToCart(productId, quantity);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully Added to the cart", responseEntity.getBody());
        }

    @Test
    void clearCart() {
        // Arrange
        // Nothing to arrange
        // Act
        doNothing().when(cartService).clearCart();
        ResponseEntity<String> responseEntity = cartController.clearCart();
        assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
        assertEquals("Successfully cleared cart",responseEntity.getBody());
    }

    @Test
    void updateProductQuantity() {
        Long productId = 10L;
        int quantity = 5;
        doNothing().when(cartService).updateProductQuantity(productId, quantity);

        // Act
        ResponseEntity<String> responseEntity = cartController.updateProductQuantity(productId, quantity);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated cartItem quantity successfully by : " + quantity, responseEntity.getBody());

        // Verify that cartService.updateProductQuantity was called
        verify(cartService).updateProductQuantity(productId, quantity);
    }
}