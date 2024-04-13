package com.product.ecommerce.Services;

import com.mysql.cj.Query;
import com.product.ecommerce.DTOs.CartSummaryDTO;
import com.product.ecommerce.DTOs.ItemDTO;
import com.product.ecommerce.Exceptions.InvalidQuantityException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.CartRepository;
import com.product.ecommerce.Repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    public void addProductToCart(Long productId, int quantity) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            Optional<CartItem> optionalCartItem = cartRepository.findByProductId(productId);
            if(optionalCartItem.isPresent()){
                CartItem cartItem = optionalCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity()+quantity);
                cartRepository.save(cartItem);
            }else {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartRepository.save(cartItem);
            }
        }else throw new ProductNotFoundException("Please enter valid productId");
    }

    @Override
    public void removeProductFromCart(Long productId, int quantity) throws InvalidQuantityException {
        Optional<CartItem> optionalCartItem = cartRepository.findByProductId(productId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            int existingQuantity = cartItem.getQuantity();
            int newQuantity = existingQuantity - quantity;
            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                cartRepository.save(cartItem);
            } else if (newQuantity == 0) {
                cartRepository.delete(cartItem);
            } else {
                throw new InvalidQuantityException("Invalid quantity: " + quantity);
            }
        } else {
            throw new InvalidQuantityException("Cart item not found for product id: " + productId);
        }
    }


    @Override
    public void clearCart() {
        cartRepository.deleteAll();
    }

    @Override
    public void updateProductQuantity(Long productId, int quantity) throws ProductNotFoundException {
        Optional<CartItem> optionalCartItem = cartRepository.findByProductId(productId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                CartItem newCartItem = new CartItem();
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
                cartRepository.save(newCartItem);
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }
        }
    }

    @Override
    public double getTotalPrice() {
        Double totalPrice = 0.0D;
        List<CartItem> cartItems = cartRepository.findAll();
        for(CartItem item: cartItems){
            totalPrice+=item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;

        // This is the implementation using JOINS. If we have large dataset and we want to retrieve efficiently
        // then we can use JOINs.

        // Query to join CartItem and Product tables and calculate the total price
//        String query = "SELECT SUM(ci.quantity * p.price) " +
//                "FROM CartItem ci " +
//                "JOIN Product p ON ci.product_id = p.id";
//        Query nativeQuery = entityManager.createNativeQuery(query);
//        BigDecimal totalPrice = (BigDecimal) nativeQuery.getSingleResult();
//        // Check if totalPrice is not null and return it, otherwise return 0
//        return (totalPrice != null) ? totalPrice.doubleValue() : 0.0;

    }



    @Override
    public int getCartSize() {
        return cartRepository.getCartSize();
    }

    @Override
    public CartSummaryDTO getCartSummary() {
        List<CartItem> cartItemsList = cartRepository.findAll();
        Double totalPrice = getTotalPrice();
        int totalQuantity = getCartSize();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(CartItem item : cartItemsList){
            ItemDTO itemDTO = new ItemDTO();
            Product product = productRepository.findById(item.getId()).orElse(null);
            if(product!=null) {
                itemDTO.setItemName(product.getTitle());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPricePerItem(product.getPrice());
                itemDTO.setSubTotal(item.getQuantity() * product.getPrice());
                itemDTOList.add(itemDTO);
            }
        }
        CartSummaryDTO cartSummaryDTO = new CartSummaryDTO();
        cartSummaryDTO.setItemDTOList(itemDTOList);
        cartSummaryDTO.setTotalItems(getCartSize());
        cartSummaryDTO.setTotalPrice(getTotalPrice());
        return cartSummaryDTO;
    }
}
