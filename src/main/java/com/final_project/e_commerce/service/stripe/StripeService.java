package com.final_project.e_commerce.service.stripe;

import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.stripe.exception.StripeException;

public interface StripeService {

    String creatStripePaymentSession(TransactionEntity transactionEntity) throws StripeException;

    boolean isPaymentCompleted(TransactionEntity transactionEntity) throws StripeException;
}
