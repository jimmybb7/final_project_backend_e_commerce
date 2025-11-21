package com.final_project.e_commerce.mapper.transaction;

import com.final_project.e_commerce.data.domainData.responseDomainData.firebase.ResponseFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToDomainFirebaseUser;
import com.final_project.e_commerce.mapper.transactionProduct.ChangeToDomainTransactionProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeToDomainTransaction {
    private final ChangeToDomainFirebaseUser changeToDomainFirebaseUser;
    private final ChangeToDomainTransactionProduct changeToDomainTransactionProduct;

    public ChangeToDomainTransaction(ChangeToDomainFirebaseUser changeToDomainFirebaseUser, ChangeToDomainTransactionProduct changeToDomainTransactionProduct) {
        this.changeToDomainFirebaseUser = changeToDomainFirebaseUser;
        this.changeToDomainTransactionProduct = changeToDomainTransactionProduct;
    }

    public ResponseTransactionDomain transactionEntityChangeToResponseTransactionDomain(TransactionEntity transactionEntity, List<TransactionProductEntity>  transactionProductEntityList) {
        ResponseTransactionDomain responseTransactionDomain = new ResponseTransactionDomain();
        responseTransactionDomain.setTid(transactionEntity.getTid());
        responseTransactionDomain.setBuyerUid(transactionEntity.getFirebaseUser().getUid());
        responseTransactionDomain.setDatetime(transactionEntity.getDatetime());
        responseTransactionDomain.setStatus(transactionEntity.getStatus());
        List<ResponseTransactionProductDomain> responseTransactionProductDomainList = changeToDomainTransactionProduct.transactionProductEntityChangeToResponseTransactionProductDomainList(transactionProductEntityList);
        responseTransactionDomain.setProducts(responseTransactionProductDomainList);
        responseTransactionDomain.setTotal(transactionEntity.getTotal());
        return responseTransactionDomain;
    }
}
