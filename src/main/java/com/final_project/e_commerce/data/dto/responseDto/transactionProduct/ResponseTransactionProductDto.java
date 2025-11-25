package com.final_project.e_commerce.data.dto.responseDto.transactionProduct;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ResponseTransactionProductDto implements Serializable {
    private Integer tpid;
    private ResponseDtoProduct product;
    private Integer quantity;
    private BigDecimal subtotal;
}
