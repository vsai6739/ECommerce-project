package com.product.ecommerce.Controllers;

import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Category;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.ProductRepository;
import com.product.ecommerce.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;
    @MockBean
    ProductService productService;
    @Autowired
    private ProductRepository productRepository;

//    @Test
//    void testProductByIdValidCase() throws ProductNotFoundException{
//        // Arrange
//        Product product = new Product();
//        product.setDescription("Testing the getProduct By ID");
//        product.setTitle("Testing Title");
//        product.setId(10L);
//
//        // Act
//        when(productService.getProductById(100L)).thenReturn(product);
//
//        // Arrange
//        assertEquals (product,productController.getProductById(100L).getBody());
//    }
//    @Test
//    void testProductByIdInvalidCase() throws ProductNotFoundException{
//        // Act
//        when(productService.getProductById(1000L)).
//                thenThrow(new ProductNotFoundException("Product not found while testing"));
//        // Assert
//        assertThrows(ProductNotFoundException.class,
//                ()-> productController.getProductById(1000L));
//    }
//    @Test
//    void testGetALlProducts(String token){
//        // Arrange
//        List<Product> products = new ArrayList<>();
//        // Act
//        when(productService.getAllProducts())
//                .thenReturn(products);
//        // Assert
//        assertEquals(products,productController.getAllProducts(token));
//    }
//    @Test
//    void testGetProductByIdNegativeCase() {
//        // Arrange
//        when(productService.getProductById(-1L))
//                .thenThrow(new ProductNotFoundException("Product not found"));
//        // Assert
//        assertThrows(ProductNotFoundException.class,
//                ()->productController.getProductById(-1L));
//    }
}