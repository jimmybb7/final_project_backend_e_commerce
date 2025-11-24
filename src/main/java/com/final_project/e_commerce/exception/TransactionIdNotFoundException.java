package com.final_project.e_commerce.exception;

public class TransactionIdNotFoundException extends RuntimeException {
    public TransactionIdNotFoundException(Integer tid) {
        super("Transaction with tid " + tid + " not found");
    }
}
