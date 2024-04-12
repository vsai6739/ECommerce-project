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
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product existingProduct;
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException("Invalid ID, the product corresponding to given id is not present in database");
        else {
            existingProduct = optionalProduct.get();
            if(product.getCategory()!=null) existingProduct.setCategory(product.getCategory());
            if(product.getTitle()!=null) existingProduct.setTitle(product.getTitle());
            if(product.getPrice()!=null) existingProduct.setPrice(product.getPrice());
            if(product.getDescription()!=null) existingProduct.setDescription(product.getDescription());
            if(product.getImageUrl()!=null) existingProduct.setImageUrl(product.getImageUrl());
        }
        productRepository.save(existingProduct);
        return existingProduct;
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
    public void deleteProduct(Long id)throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException("Product not found to delete");
        productRepository.deleteById(id);
    }
    public List<Product> getProductsByCategory(String category){
        List<Product> productList = productRepository.findProductByCategory(category);
        return productList;
    }
    public List<Product> getProductByTitle(String title){
       List<Product> productList = productRepository.findProductByTitle(title);
       return productList;
    }
}
