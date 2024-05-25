package com.product.ecommerce.Services;

import com.product.ecommerce.Exceptions.EmptyProductListException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
@Primary

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageNumber , int pageSize) throws EmptyProductListException;
    Product updateProduct(Long id , Product product) throws ProductNotFoundException;
    Product createProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductByTitle(String title);
}
