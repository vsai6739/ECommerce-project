package com.product.ecommerce.Repositories;

import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Services.ProductServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.category.title LIKE CONCAT('%', :category, '%')")
    List<Product> findProductByCategory(@Param("category") String category);
    @Query("SELECT p FROM Product  p where p.title LIKE CONCAT('%',:title,'%')")
    List<Product> findProductByTitle(@Param("title") String title);
}
