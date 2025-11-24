package com.final_project.e_commerce.exception;

public class StockNotEnoughException extends RuntimeException {
    public StockNotEnoughException(int quantity, String productName) {
        super("Product: " + productName + " - Quantity: " + quantity + ", stock is not enough");
    }
}
