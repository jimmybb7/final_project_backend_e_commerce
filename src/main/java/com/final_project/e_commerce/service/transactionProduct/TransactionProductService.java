package com.final_project.e_commerce.service.transactionProduct;

import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {
    List<TransactionProductEntity> getTransactionProductByTid(Integer tid);
}
