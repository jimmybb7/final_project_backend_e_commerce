package com.final_project.e_commerce.repository.transactionProduct;

import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {

    @Query(nativeQuery = true,
    value = "select * from transaction_product where transaction_tid = ?1")
    List<TransactionProductEntity> getTransactionProductByTid(Integer tid);
}
