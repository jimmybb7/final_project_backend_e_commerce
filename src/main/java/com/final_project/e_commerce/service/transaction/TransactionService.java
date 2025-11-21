package com.final_project.e_commerce.service.transaction;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService {
    @Transactional
    ResponseTransactionDomain createTransaction(ReqFirebaseUserDomain reqFirebaseUserDomain);

    ResponseTransactionDomain getTransaction(ReqFirebaseUserDomain reqFirebaseUserDomain, Integer tid);

    @Transactional
    void updateTransactionStatusToProcessing(ReqFirebaseUserDomain reqFirebaseUserDomain, Integer tid);
}
