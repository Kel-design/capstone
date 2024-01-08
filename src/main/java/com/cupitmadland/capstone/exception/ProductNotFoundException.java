package com.cupitmadland.capstone.exception;

/**
 * Custom exception class for handling cases when a product is not found.
 */
public class ProductNotFoundException extends RuntimeException{

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message the detail message saved when called
     */
    public ProductNotFoundException(String message){
        super(message);
    }
}
