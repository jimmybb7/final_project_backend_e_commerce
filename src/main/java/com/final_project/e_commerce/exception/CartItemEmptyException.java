package com.final_project.e_commerce.exception;

public class CartItemEmptyException extends RuntimeException {
    public CartItemEmptyException(int uid) {
        super("user's cart item is empty");
    }
}
