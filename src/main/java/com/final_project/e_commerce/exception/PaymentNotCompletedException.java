package com.final_project.e_commerce.exception;

public class PaymentNotCompletedException extends RuntimeException {
    public PaymentNotCompletedException(Integer tid) {
        super("transaction " + tid + " haven't been completed yet");
    }
}
