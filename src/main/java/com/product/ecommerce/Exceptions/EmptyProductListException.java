package com.product.ecommerce.Exceptions;

public class EmptyProductListException extends RuntimeException{
    public EmptyProductListException(String message){
        super(message);
    }
}
