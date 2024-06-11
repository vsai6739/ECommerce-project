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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Primary
public class ProductController {
    ProductService productService;
    private AuthenticationCommons authenticationCommons;
    private RestTemplate restTemplate;

    ProductController( ProductService productService,
                       AuthenticationCommons authenticationCommons,
                       RestTemplate restTemplate){
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)throws ProductNotFoundException {
        Product product = productService.getProductById(id);


        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://userservice/users/12", String.class);

        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }
    @GetMapping("/")
    Page<Product> getAllProducts(@RequestParam("pageNumber")int pageNumber,
                                 @RequestParam("pageSize") int pageSize) throws EmptyProductListException {
//        UserDto userDto = authenticationCommons.validateToken(token);
//        if(userDto==null){
//            // Token is invalid
//            return new ArrayList<>();
//        }
        return (Page<Product>) productService.getAllProducts(pageNumber,pageSize);
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
        productService.deleteProduct(id);
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
