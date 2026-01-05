package com.final_project.e_commerce.mapper.transactionProduct;

import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeToTransactionProductEntity {

    public List<TransactionProductEntity> changeToTransactionProductEntity(TransactionEntity transactionEntity, List<CartEntity> cartEntityList) {
        List<TransactionProductEntity> transactionProductEntityList = cartEntityList.stream()
                .map(cartEntity -> {
                    TransactionProductEntity transactionProductEntity = new TransactionProductEntity();
                    transactionProductEntity.setTransaction(transactionEntity);
                    transactionProductEntity.setPid(cartEntity.getProduct().getPid());
                    transactionProductEntity.setName(cartEntity.getProduct().getName());
                    transactionProductEntity.setDescription(cartEntity.getProduct().getDescription());
                    transactionProductEntity.setPrice(cartEntity.getProduct().getPrice());
                    transactionProductEntity.setImageUrl(cartEntity.getProduct().getImageUrl());
                    transactionProductEntity.setStock(cartEntity.getProduct().getStock());
                    transactionProductEntity.setQuantity(cartEntity.getQuantity());
                    transactionProductEntity.setStripePriceId(cartEntity.getProduct().getStripePriceId());
                    transactionProductEntity.setNameEn(cartEntity.getProduct().getNameEn());
                    return transactionProductEntity;
                })
                .toList();
        return transactionProductEntityList;
    }
}
