package com.product.ecommerce.Controllers;

import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;
    CartController(CartService cartService){
        this.cartService = cartService;
    }
    @PostMapping("{id}/{quantity}")
    ResponseEntity<String> addToCart(@PathVariable("id") Long productId, @PathVariable("quantity")int quantity){
        cartService.addProductToCart(productId, quantity);
        return ResponseEntity.ok("Successfully Added to the cart");
    }
}
