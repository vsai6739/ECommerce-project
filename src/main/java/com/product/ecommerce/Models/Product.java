package com.product.ecommerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    String title;
    Double price;
    @ManyToOne
    Category category;
    String description;
    String imageUrl;
}
