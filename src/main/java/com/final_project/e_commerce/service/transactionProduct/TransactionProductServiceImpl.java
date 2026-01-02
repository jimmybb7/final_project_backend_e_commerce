package com.final_project.e_commerce.service.transactionProduct;

import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import com.final_project.e_commerce.mapper.transactionProduct.ChangeToDomainTransactionProduct;
import com.final_project.e_commerce.repository.transactionProduct.TransactionProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public List<TransactionProductEntity> getTransactionProductByTid(Integer tid) {
        return transactionProductRepository.getTransactionProductByTid(tid);
    }
}
