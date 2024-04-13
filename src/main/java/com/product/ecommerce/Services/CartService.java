package com.product.ecommerce.Services;

import com.product.ecommerce.DTOs.CartSummaryDTO;
import com.product.ecommerce.Exceptions.InvalidQuantityException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {
    void addProductToCart(Long productId , int quantity)throws ProductNotFoundException;
    void removeProductFromCart(Long productId , int quantity) throws InvalidQuantityException;
    void clearCart();
    void updateProductQuantity(Long productId , int quantity) throws ProductNotFoundException;
    double getTotalPrice();
    int getCartSize();
    CartSummaryDTO getCartSummary();
}
