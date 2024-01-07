package com.cupitmadland.capstone.exception;

// Used to throw exception when a product is not found.
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
