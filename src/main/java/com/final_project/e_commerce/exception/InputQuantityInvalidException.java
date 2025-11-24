package com.final_project.e_commerce.exception;

public class InputQuantityInvalidException extends RuntimeException {
    public InputQuantityInvalidException(int quantity) {
        super("input quantity invalid " + quantity + ", input must be a positive integer");
    }
}
