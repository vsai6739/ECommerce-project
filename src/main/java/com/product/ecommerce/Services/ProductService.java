package com.product.ecommerce.Services;

import com.product.ecommerce.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id , Product product);
    Product replaceProduct(Long id , Product product);
    Product createProduct(Product product);
    void deleteProduct(Long id);
}
