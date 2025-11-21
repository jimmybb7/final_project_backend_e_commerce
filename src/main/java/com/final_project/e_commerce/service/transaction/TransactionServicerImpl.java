package com.final_project.e_commerce.service.transaction;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import com.final_project.e_commerce.mapper.transaction.ChangeToDomainTransaction;
import com.final_project.e_commerce.mapper.transaction.ChangeToTransactionEntity;
import com.final_project.e_commerce.mapper.transactionProduct.ChangeToTransactionProductEntity;
import com.final_project.e_commerce.repository.transaction.TransactionRepository;
import com.final_project.e_commerce.repository.transactionProduct.TransactionProductRepository;
import com.final_project.e_commerce.service.cart.CartService;
import com.final_project.e_commerce.service.firebaseUser.FirebaseUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServicerImpl implements TransactionService {

    private final FirebaseUserService firebaseUserService;
    private final CartService cartService;
    private final ChangeToTransactionEntity changeToTransactionEntity;
    private final TransactionRepository transactionRepository;
    private final TransactionProductRepository transactionProductRepository;
    private final ChangeToTransactionProductEntity changeToTransactionProductEntity;
    private final ChangeToDomainTransaction changeToDomainTransaction;

    public TransactionServicerImpl(FirebaseUserService firebaseUserService, CartService cartService, ChangeToTransactionEntity changeToTransactionEntity, TransactionRepository transactionRepository, TransactionProductRepository transactionProductRepository, ChangeToTransactionProductEntity changeToTransactionProductEntity, ChangeToDomainTransaction changeToDomainTransaction) {
        this.firebaseUserService = firebaseUserService;
        this.cartService = cartService;
        this.changeToTransactionEntity = changeToTransactionEntity;
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.changeToTransactionProductEntity = changeToTransactionProductEntity;
        this.changeToDomainTransaction = changeToDomainTransaction;
    }

    @Transactional
    @Override
    public ResponseTransactionDomain createTransaction(ReqFirebaseUserDomain reqFirebaseUserDomain) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFirebaseUserDomain);
        List<CartEntity> cartItemEntityList = cartService.getCartItemEntityListByUid(firebaseUserEntity);
        TransactionEntity transactionEntity = changeToTransactionEntity.changeToTransactionEntity(firebaseUserEntity, cartItemEntityList);
        TransactionEntity savedTransactionEntity = transactionRepository.save(transactionEntity);
        List<TransactionProductEntity> transactionProductEntityList = changeToTransactionProductEntity.changeToTransactionProductEntity(savedTransactionEntity, cartItemEntityList);
        transactionProductRepository.saveAll(transactionProductEntityList);
        return changeToDomainTransaction.transactionEntityChangeToResponseTransactionDomain(savedTransactionEntity, transactionProductEntityList);
    }
}
