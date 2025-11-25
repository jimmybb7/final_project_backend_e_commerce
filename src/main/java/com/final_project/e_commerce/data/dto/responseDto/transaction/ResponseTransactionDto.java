package com.final_project.e_commerce.data.dto.responseDto.transaction;

import com.final_project.e_commerce.data.domainData.responseDomainData.firebase.ResponseFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.dto.responseDto.transactionProduct.ResponseTransactionProductDto;
import com.final_project.e_commerce.service.transaction.TransactionStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseTransactionDto implements Serializable {
    private Integer tid;
    private Integer buyerUid;
    private LocalDateTime datetime;
    private TransactionStatusEnum status;
    private BigDecimal total;
    private List<ResponseTransactionProductDto> products;
}
