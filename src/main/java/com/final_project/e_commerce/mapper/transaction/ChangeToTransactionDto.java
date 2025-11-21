package com.final_project.e_commerce.mapper.transaction;

import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.dto.responseDto.transaction.ResponseTransactionDto;
import com.final_project.e_commerce.data.dto.responseDto.transactionProduct.ResponseTransactionProductDto;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import com.final_project.e_commerce.mapper.transactionProduct.ChangeToTransactionProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeToTransactionDto {
    private final ChangeToTransactionProductDto changeToTransactionProductDto;

    public ChangeToTransactionDto(ChangeToTransactionProductDto changeToTransactionProductDto) {
        this.changeToTransactionProductDto = changeToTransactionProductDto;
    }


    public ResponseTransactionDto responseTransactionDomainChangeToResponseTransactionDto(ResponseTransactionDomain responseTransactionDomain, List<ResponseTransactionProductDomain> responseTransactionProductDomain) {
        ResponseTransactionDto responseTransactionDto = new ResponseTransactionDto();
        responseTransactionDto.setTid(responseTransactionDomain.getTid());
        responseTransactionDto.setBuyerUid(responseTransactionDomain.getBuyerUid());
        responseTransactionDto.setDatetime(responseTransactionDomain.getDatetime());
        responseTransactionDto.setStatus(responseTransactionDomain.getStatus());
        List<ResponseTransactionProductDto> responseTransactionProductDtoList = changeToTransactionProductDto.transactionProductEntityChangeToResponseTransactionProductDomainList(responseTransactionProductDomain);
        responseTransactionDto.setProducts(responseTransactionProductDtoList);
        responseTransactionDto.setTotal(responseTransactionDomain.getTotal());
        return responseTransactionDto;
    }
}
