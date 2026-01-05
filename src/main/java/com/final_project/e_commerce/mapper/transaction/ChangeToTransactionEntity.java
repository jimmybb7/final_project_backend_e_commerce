package com.final_project.e_commerce.mapper.transaction;

import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transaction.TransactionStatusEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ChangeToTransactionEntity {

    public TransactionEntity changeToTransactionEntity(FirebaseUserEntity firebaseUserEntity,List<CartEntity> cartItemEntityList) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setFirebaseUser(firebaseUserEntity);
        transactionEntity.setDatetime(LocalDateTime.now());
        transactionEntity.setStatus(TransactionStatusEnum.PREPARE);

        BigDecimal total = cartItemEntityList.stream()
                .map(cartEntity ->
                        cartEntity.getProduct().getPrice().multiply(new BigDecimal(cartEntity.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        transactionEntity.setTotal(total);
        return transactionEntity;
    }
}
