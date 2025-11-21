package com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseTransactionProductDomain {
    private Integer tpid;
    private ResponseProductDomainData product;
    private Integer quantity;
    private BigDecimal subtotal;
}
