package com.product.ecommerce.Controllers;

import com.product.ecommerce.DTOs.UserDto;
import com.product.ecommerce.Exceptions.EmptyProductListException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.ProductRepository;
import com.product.ecommerce.Services.ProductService;
import com.product.ecommerce.commons.AuthenticationCommons;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Primary
public class ProductController {
    ProductService productService;
    private final ProductRepository productRepository;
    private AuthenticationCommons authenticationCommons;

    ProductController( ProductService productService,
                      ProductRepository productRepository,
                       AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.productRepository = productRepository;
        this.authenticationCommons = authenticationCommons;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)throws ProductNotFoundException {
//        Product product = productService.getProductById(id);
//        return new ResponseEntity<Product>(product,HttpStatus.OK);
//    }
    @GetMapping("/{tokenValue}")
    List<Product> getAllProducts(@PathVariable("tokenValue") String tokenValue) throws EmptyProductListException {
        UserDto userDto = authenticationCommons.validateToken(tokenValue);
        if(userDto==null){
            // Token is invalid
            return new ArrayList<>();
        }
        return productService.getAllProducts();
    }
    @PatchMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") Long id ,@RequestBody Product product)throws ProductNotFoundException{
        Product updatedProduct = productService.updateProduct(id,product);
        return new ResponseEntity<Product>(updatedProduct , HttpStatus.CREATED);
    }
    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable("id")Long id){
        productRepository.deleteById(id);
    }
    @GetMapping("/{id}/{category}")
    List<Product> getProductsByCategory(@PathVariable("category")String category){
        return productService.getProductsByCategory(category);
    }
//    @GetMapping("/{title}/")
//    List<Product> getProductsByTitle(@PathVariable("title")String title){
//        return productService.getProductByTitle(title);
//    }
}
