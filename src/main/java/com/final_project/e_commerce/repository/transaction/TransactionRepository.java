package com.final_project.e_commerce.repository.transaction;

import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
