package com.final_project.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionIdNotFoundException extends RuntimeException {
    public TransactionIdNotFoundException(Integer tid) {
        super("Transaction with tid " + tid + " not found");
    }
}
