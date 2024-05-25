package com.product.ecommerce.Controllers;

import com.product.ecommerce.DTOs.CartSummaryDTO;
import com.product.ecommerce.Exceptions.InvalidQuantityException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Services.CartService;
import org.springframework.http.HttpStatus;
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
        try {
            cartService.addProductToCart(productId, quantity);
            return ResponseEntity.ok("Successfully Added to the cart");
        } catch(ProductNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @DeleteMapping("/{productId}/{quantity}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable("productId") Long productId,
                                                        @PathVariable("quantity") int quantity) {
        try {
            cartService.removeProductFromCart(productId, quantity);
            return ResponseEntity.ok("Successfully deleted from cart with quantity: " + quantity);
        } catch (InvalidQuantityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> clearCart(){
        cartService.clearCart();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully cleared cart");
    }
    @PatchMapping("/{productId}/{quantity}")
    ResponseEntity<String> updateProductQuantity(@PathVariable("productId")Long productId , @PathVariable("quantity")int quantity){
        try{
            cartService.updateProductQuantity(productId,quantity);
            return   ResponseEntity.ok("Updated cartItem quantity successfully by : "+quantity);
        } catch (ProductNotFoundException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping("/totalprice")
    ResponseEntity<String> getTotalPrice(){
        Double totalPrice = cartService.getTotalPrice();
        return ResponseEntity.ok("Total price of cart items is : " + totalPrice);
    }
    @GetMapping("/cartSize")
    ResponseEntity<String> getCartSize(){
        int cartSize = cartService.getCartSize();
        return ResponseEntity.ok("Number of items present in your cart is : "+ cartSize);
    }
    @GetMapping("/summary")
    ResponseEntity<CartSummaryDTO> getCartSummary(){
        CartSummaryDTO cartSummaryDTO = cartService.getCartSummary();
        return ResponseEntity.ok(cartSummaryDTO);
    }
}
