package com.final_project.e_commerce.mapper.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseAllDtoProduct;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeToDtoProductTesting {

    public ResponseDtoProduct changeProductDomainToResponseDtoById(ResponseProductDomainData responseProductDomainData) {
        ResponseDtoProduct responseDtoProduct = new ResponseDtoProduct();
        responseDtoProduct.setImageUrl(responseProductDomainData.getImageUrl());
        responseDtoProduct.setName(responseProductDomainData.getName());
        responseDtoProduct.setPrice(responseProductDomainData.getPrice());
        responseDtoProduct.setPid(responseProductDomainData.getPid());
        responseDtoProduct.setStock(responseProductDomainData.getStock());
        responseDtoProduct.setDescription(responseProductDomainData.getDescription());
        return responseDtoProduct;
    }

    public List<ResponseAllDtoProduct> changeProductDomainToResponseDtoList(List<ResponseProductDomainData> responseProductDomainDataList) {
        List<ResponseAllDtoProduct> responseAllDtoProductList = new ArrayList<>();
        for (ResponseProductDomainData responseProductDomainData : responseProductDomainDataList) {
            responseAllDtoProductList.add(changeProductDomainToResponseDtoForList(responseProductDomainData));
        }
        return responseAllDtoProductList;
    }

    public ResponseAllDtoProduct changeProductDomainToResponseDtoForList(ResponseProductDomainData responseProductDomainData) {
        ResponseAllDtoProduct responseAllDtoProduct = new ResponseAllDtoProduct();
        if (responseProductDomainData.getStock() > 0) {
            responseAllDtoProduct.setHasStock(true);
        } else {
            responseAllDtoProduct.setHasStock(false);
        }
        responseAllDtoProduct.setImageUrl(responseProductDomainData.getImageUrl());
        responseAllDtoProduct.setName(responseProductDomainData.getName());
        responseAllDtoProduct.setPrice(responseProductDomainData.getPrice());
        responseAllDtoProduct.setPid(responseProductDomainData.getPid());
        responseAllDtoProduct.setDescription(responseProductDomainData.getDescription());
        return responseAllDtoProduct;
    }
}
