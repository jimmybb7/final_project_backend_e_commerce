package com.final_project.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockNotEnoughException extends RuntimeException {
    public StockNotEnoughException(int quantity) {
        super("quantity: " + quantity + ", stock is not enough");
    }
}
