package com.product.ecommerce.Services;

import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.CartRepository;
import com.product.ecommerce.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    CartRepository cartRepository;
    ProductRepository productRepository;
    CartServiceImpl(CartRepository cartRepository , ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void addProductToCart(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
        }

    }

    @Override
    public void removeProductFromCart(Product product, int quantity) {

    }

    @Override
    public void clearCart() {

    }

    @Override
    public void updateProductQuantity(Product product, int quantity) {

    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public int getCartSize() {
        return 0;
    }

    @Override
    public CartItem getCartSummary() {
        return null;
    }
}
