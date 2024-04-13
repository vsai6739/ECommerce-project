package com.product.ecommerce.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartSummaryDTO {

    String message = "Your cart details are as follows";
    int totalItems;
    Double totalPrice;
    List<ItemDTO> itemDTOList;
}
