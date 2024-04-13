package com.product.ecommerce.Repositories;

import com.product.ecommerce.Models.CartItem;
import com.product.ecommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CartRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT c FROM CartItem c WHERE c.product.id = :productId")
    Optional<CartItem> findByProductId(Long productId);
    @Query("SELECT SUM(c.quantity) FROM CartItem AS c")
    int getCartSize();
    /*

    select p.productTitle from CartItem
    JOIN ON CartItem.product_id = p.id
    GROUP BY p.productTitle;
     */
    @Query("SELECT DISTINCT p.title " +
            "FROM CartItem ci " +
            "JOIN ci.product p")
    List<String> findDistinctProductTitlesInCart();

}
