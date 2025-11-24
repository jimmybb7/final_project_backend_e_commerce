package com.final_project.e_commerce.exception;

public class TransactionStatusException extends RuntimeException {
    public TransactionStatusException(Integer tid, String transactionStatus) {
        super("tid: " + tid + " Transaction status not in " + transactionStatus);
    }
}
