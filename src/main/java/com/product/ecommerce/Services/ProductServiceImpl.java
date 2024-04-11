package com.product.ecommerce.Services;

import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.CategoryRepository;
import com.product.ecommerce.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductServiceImpl(ProductRepository productRepository ,CategoryRepository categoryRepository ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        // Here Optional will automatically convert database attributes to product object.
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
