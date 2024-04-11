package com.product.ecommerce.Services;

import com.product.ecommerce.Exceptions.EmptyProductListException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Category;
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
    public List<Product> getAllProducts() throws EmptyProductListException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) throw new EmptyProductListException("No Products Found");
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    public Product createProduct(Product product) {
        Category category = product.getCategory();

        // Check if category exists in the database
        if (category != null && category.getId() != null) {
            Optional<Category> existingCategory = categoryRepository.findById(category.getId());
            if (existingCategory.isPresent()) {
                product.setCategory(existingCategory.get());
            } else {
                // Category doesn't exist, save it first
                category = categoryRepository.save(category);
                product.setCategory(category);
            }
        }

        // Save the product
        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {

    }
}