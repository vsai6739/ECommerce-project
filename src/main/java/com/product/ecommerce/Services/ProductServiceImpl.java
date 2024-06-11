package com.product.ecommerce.Services;

import com.product.ecommerce.Exceptions.EmptyProductListException;
import com.product.ecommerce.Exceptions.ProductNotFoundException;
import com.product.ecommerce.Models.Category;
import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Repositories.CategoryRepository;
import com.product.ecommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    private RedisTemplate redisTemplate;
    ProductServiceImpl(ProductRepository productRepository ,CategoryRepository categoryRepository,RedisTemplate redisTemplate ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        // Retrieve the product from Redis cache
        Product prod = (Product) redisTemplate.opsForHash().get("PRODUCT", "PROD_" + id);
        if (prod != null) {
            return prod;
        }

        // Fetch product from the database
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }

        // Store the product in Redis cache
        prod = product.get();
        redisTemplate.opsForHash().put("PRODUCT", "PROD_" + id, prod);

        return prod;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber , int pageSize) throws EmptyProductListException {
        Sort sort = Sort.by("price").ascending();
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber,pageSize,sort));
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
