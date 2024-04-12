package com.product.ecommerce.Controllers;

import com.product.ecommerce.Exceptions.EmptyProductListException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.ProductRepository;
import com.product.ecommerce.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    private final ProductRepository productRepository;

    ProductController(ProductService productService,
                      ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id)throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping
    List<Product> getAllProducts() throws EmptyProductListException {
        return productService.getAllProducts();
    }
    @PatchMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") Long id ,@RequestBody Product product)throws ProductNotFoundException{
        Product updatedProduct = productService.updateProduct(id,product);
        return ResponseEntity.ok(updatedProduct);
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
    @GetMapping("/{title}/")
    List<Product> getProductsByTitle(@PathVariable("title")String title){
        return productService.getProductByTitle(title);
    }
}
