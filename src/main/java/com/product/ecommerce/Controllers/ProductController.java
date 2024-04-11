package com.product.ecommerce.Controllers;

import com.product.ecommerce.Models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id){
        return new Product();
    }
    @GetMapping
    List<Product> getAllProducts(){
        return new ArrayList<>();
    }
    @PutMapping("/{id}")
    ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id ,@RequestBody Product product){
        if(product!=null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}")
    Product updateProduct(@PathVariable("id")Long id , @RequestBody Product product){
        return product;
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
