package com.final_project.e_commerce.repository.transaction;

import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    @Query(nativeQuery = true,
    value = "select * from transaction where firebase_user_uid = ?1 and tid = ?2")
    Optional<TransactionEntity> getTransactionByTid(Integer uid, Integer tid);
}
