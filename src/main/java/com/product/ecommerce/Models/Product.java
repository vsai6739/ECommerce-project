package com.product.ecommerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    String title;
    Double price;
    @ManyToOne
    Category category;
    String description;
    String imageUrl;
}
