package com.final_project.e_commerce.service.transaction;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import com.final_project.e_commerce.exception.TransactionIdNotFoundException;
import com.final_project.e_commerce.exception.TransactionStatusException;
import com.final_project.e_commerce.mapper.transaction.ChangeToDomainTransaction;
import com.final_project.e_commerce.mapper.transaction.ChangeToTransactionEntity;
import com.final_project.e_commerce.mapper.transactionProduct.ChangeToTransactionProductEntity;
import com.final_project.e_commerce.repository.transaction.TransactionRepository;
import com.final_project.e_commerce.repository.transactionProduct.TransactionProductRepository;
import com.final_project.e_commerce.service.cart.CartService;
import com.final_project.e_commerce.service.firebaseUser.FirebaseUserService;
import com.final_project.e_commerce.service.transactionProduct.TransactionProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServicerImpl implements TransactionService {

    private final FirebaseUserService firebaseUserService;
    private final CartService cartService;
    private final ChangeToTransactionEntity changeToTransactionEntity;
    private final TransactionRepository transactionRepository;
    private final TransactionProductRepository transactionProductRepository;
    private final ChangeToTransactionProductEntity changeToTransactionProductEntity;
    private final ChangeToDomainTransaction changeToDomainTransaction;
    private final Logger logger = LoggerFactory.getLogger(TransactionServicerImpl.class);
    private final TransactionProductService transactionProductService;

    public TransactionServicerImpl(FirebaseUserService firebaseUserService, CartService cartService, ChangeToTransactionEntity changeToTransactionEntity, TransactionRepository transactionRepository, TransactionProductRepository transactionProductRepository, ChangeToTransactionProductEntity changeToTransactionProductEntity, ChangeToDomainTransaction changeToDomainTransaction, TransactionProductService transactionProductService) {
        this.firebaseUserService = firebaseUserService;
        this.cartService = cartService;
        this.changeToTransactionEntity = changeToTransactionEntity;
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.changeToTransactionProductEntity = changeToTransactionProductEntity;
        this.changeToDomainTransaction = changeToDomainTransaction;
        this.transactionProductService = transactionProductService;
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

    @Override
    public ResponseTransactionDomain getTransaction(ReqFirebaseUserDomain reqFirebaseUserDomain, Integer tid) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFirebaseUserDomain);
        Optional<TransactionEntity> transactionByTid = transactionRepository.getTransactionByTid(firebaseUserEntity.getUid(), tid);
        if (transactionByTid.isEmpty()) {
            logger.warn("Transaction with tid {} not found", tid);
            throw new TransactionIdNotFoundException(tid);
        }
        TransactionEntity transactionEntity = transactionByTid.get();
        List<TransactionProductEntity> transactionProductList = transactionProductService.getTransactionProductByTid(tid);
        return changeToDomainTransaction.transactionEntityChangeToResponseTransactionDomain(transactionEntity, transactionProductList);
    }

    @Transactional
    @Override
    public void updateTransactionStatusToProcessing(ReqFirebaseUserDomain reqFirebaseUserDomain, Integer tid) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFirebaseUserDomain);
        Optional<TransactionEntity> transactionByTid = transactionRepository.getTransactionByTid(firebaseUserEntity.getUid(), tid);
        if (transactionByTid.isEmpty()) {
            logger.warn("Transaction with tid {} not found", tid);
            throw new TransactionIdNotFoundException(tid);
        }
        TransactionEntity transactionEntity = transactionByTid.get();
        if (transactionEntity.getStatus() != TransactionStatusEnum.PREPARE){
            logger.warn("tid: {} Transaction status not in PREPARE", tid);
            throw new TransactionStatusException(tid);
        }
        transactionEntity.setStatus(TransactionStatusEnum.PROCESSING);
    }
}
