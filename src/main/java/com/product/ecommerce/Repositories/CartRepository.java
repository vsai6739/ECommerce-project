package com.product.ecommerce.Repositories;

import com.product.ecommerce.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartRepository extends JpaRepository<CartItem,Long> {
}
