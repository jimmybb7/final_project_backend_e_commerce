package com.final_project.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemEmptyException extends RuntimeException {
    public CartItemEmptyException(int uid) {
        super("user's cart item is empty");
    }
}
