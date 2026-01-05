package com.final_project.e_commerce.data.domainData.responseDomainData.transaction;

import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.entity.transaction.TransactionStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseTransactionDomain implements Serializable {
    private Integer tid;
    private Integer buyerUid;
    private LocalDateTime datetime;
    private TransactionStatusEnum status;
    private BigDecimal total;
    private List<ResponseTransactionProductDomain> products;

}
