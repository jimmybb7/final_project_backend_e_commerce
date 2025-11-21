package com.final_project.e_commerce.mapper.transactionProduct;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ChangeToDomainTransactionProduct {

    public ResponseTransactionProductDomain transactionProductEntityChangeToResponseTransactionProductDomain(TransactionProductEntity transactionProductEntity){
        ResponseTransactionProductDomain responseTransactionProductDomain = new ResponseTransactionProductDomain();
        responseTransactionProductDomain.setTpid(transactionProductEntity.getTpid());
        responseTransactionProductDomain.setQuantity(transactionProductEntity.getQuantity());
        responseTransactionProductDomain.setSubtotal(transactionProductEntity.getPrice().multiply(new BigDecimal(transactionProductEntity.getQuantity())));
        ResponseProductDomainData responseProductDomainData = getResponseProductDomainData(transactionProductEntity);
        responseTransactionProductDomain.setProduct(responseProductDomainData);
        return responseTransactionProductDomain;
    }

    private ResponseProductDomainData getResponseProductDomainData(TransactionProductEntity transactionProductEntity) {
        ResponseProductDomainData responseProductDomainData = new ResponseProductDomainData();
        responseProductDomainData.setPid(transactionProductEntity.getPid());
        responseProductDomainData.setName(transactionProductEntity.getName());
        responseProductDomainData.setPrice(transactionProductEntity.getPrice());
        responseProductDomainData.setImageUrl(transactionProductEntity.getImageUrl());
        responseProductDomainData.setDescription(transactionProductEntity.getDescription());
        responseProductDomainData.setStock(transactionProductEntity.getStock());
        return responseProductDomainData;
    }

    public List<ResponseTransactionProductDomain> transactionProductEntityChangeToResponseTransactionProductDomainList(List<TransactionProductEntity> transactionProductEntityList){
        return transactionProductEntityList.stream()
                .map(this::transactionProductEntityChangeToResponseTransactionProductDomain)
                .toList();
    }
}
