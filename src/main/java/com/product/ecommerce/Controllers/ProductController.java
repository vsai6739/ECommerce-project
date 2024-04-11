package com.product.ecommerce.Controllers;

import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id)throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping
    List<Product> getAllProducts(){
        return new ArrayList<>();
    }
    @PatchMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") Long id ,@RequestBody Product product){
        if(product!=null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    Product createProduct(@RequestBody Product product){
        return product;
    }
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable("id")Long id){
        return;
    }
}
