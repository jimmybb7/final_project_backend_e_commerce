package com.final_project.e_commerce.exception;

import com.final_project.e_commerce.service.transaction.TransactionStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionStatusException extends RuntimeException {
    public TransactionStatusException(Integer tid, String transactionStatus) {
        super("tid: " + tid + " Transaction status not in " + transactionStatus);
    }
}
