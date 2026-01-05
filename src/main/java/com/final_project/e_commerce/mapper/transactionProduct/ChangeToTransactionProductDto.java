package com.final_project.e_commerce.mapper.transactionProduct;

import com.final_project.e_commerce.data.domainData.responseDomainData.transactionProduct.ResponseTransactionProductDomain;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import com.final_project.e_commerce.data.dto.responseDto.transactionProduct.ResponseTransactionProductDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ChangeToTransactionProductDto {
    public ResponseTransactionProductDto responseTransactionProductDomainChangeToResponseTransactionProductDto(ResponseTransactionProductDomain responseTransactionProductDomain){
        ResponseTransactionProductDto responseTransactionProductDto = new ResponseTransactionProductDto();
        responseTransactionProductDto.setTpid(responseTransactionProductDomain.getTpid());
        responseTransactionProductDto.setQuantity(responseTransactionProductDomain.getQuantity());
        responseTransactionProductDto.setSubtotal(responseTransactionProductDomain.getProduct().getPrice().multiply(new BigDecimal(responseTransactionProductDomain.getQuantity())));
        ResponseDtoProduct responseDtoProduct = getResponseDtoProduct(responseTransactionProductDomain);
        responseTransactionProductDto.setProduct(responseDtoProduct);
        return responseTransactionProductDto;
    }

    private ResponseDtoProduct getResponseDtoProduct(ResponseTransactionProductDomain responseTransactionProductDomain) {
        ResponseDtoProduct responseDtoProduct = new ResponseDtoProduct();
        responseDtoProduct.setPid(responseTransactionProductDomain.getProduct().getPid());
        responseDtoProduct.setName(responseTransactionProductDomain.getProduct().getName());
        responseDtoProduct.setPrice(responseTransactionProductDomain.getProduct().getPrice());
        responseDtoProduct.setImageUrl(responseTransactionProductDomain.getProduct().getImageUrl());
        responseDtoProduct.setDescription(responseTransactionProductDomain.getProduct().getDescription());
        responseDtoProduct.setStock(responseTransactionProductDomain.getProduct().getStock());
        responseDtoProduct.setNameEn(responseTransactionProductDomain.getProduct().getNameEn());
        return responseDtoProduct;
    }

    public List<ResponseTransactionProductDto> transactionProductEntityChangeToResponseTransactionProductDomainList(List<ResponseTransactionProductDomain> responseTransactionProductDomain){
        return responseTransactionProductDomain.stream()
                .map(this::responseTransactionProductDomainChangeToResponseTransactionProductDto)
                .toList();
    }
}
