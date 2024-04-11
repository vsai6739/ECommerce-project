package com.product.ecommerce.Repositories;

import com.product.ecommerce.Models.Product;
import com.product.ecommerce.Services.ProductServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
