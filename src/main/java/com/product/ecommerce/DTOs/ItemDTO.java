package com.product.ecommerce.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    String itemName;
    int quantity;
    Double pricePerItem;
    Double subTotal;
}
