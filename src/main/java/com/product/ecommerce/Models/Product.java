package com.product.ecommerce.Models;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    String title;
    Double price;
    @ManyToOne
    Category category;
    String description;
    String imageUrl;
}
