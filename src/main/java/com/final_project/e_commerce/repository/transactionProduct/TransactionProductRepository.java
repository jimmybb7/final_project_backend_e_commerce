package com.final_project.e_commerce.repository.transactionProduct;

import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
