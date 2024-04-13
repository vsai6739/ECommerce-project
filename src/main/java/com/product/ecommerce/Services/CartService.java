package com.product.ecommerce.Services;

import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {
    void addProductToCart(Long productId , int quantity);
    void removeProductFromCart(Product product , int quantity);
    void clearCart();
    void updateProductQuantity(Product product , int quantity);
    double getTotalPrice();
    int getCartSize();
    CartItem getCartSummary();
}
