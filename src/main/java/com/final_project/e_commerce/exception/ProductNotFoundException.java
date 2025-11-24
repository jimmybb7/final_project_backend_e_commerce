package com.final_project.e_commerce.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super("the product with id " + productId + " does not exist");
    }
}
