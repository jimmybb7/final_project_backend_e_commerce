package com.final_project.e_commerce.mapper.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeToDtoProductTesting {

    public ResponseDtoProduct changeProductDomainToResponseDtoSingle(ResponseProductDomainData responseProductDomainData) {
        ResponseDtoProduct responseDtoProduct = new ResponseDtoProduct();
        responseDtoProduct.setImageUrl(responseProductDomainData.getImageUrl());
        responseDtoProduct.setName(responseProductDomainData.getName());
        responseDtoProduct.setPrice(responseProductDomainData.getPrice());
        responseDtoProduct.setPid(responseProductDomainData.getPid());
        responseDtoProduct.setStock(responseProductDomainData.getStock());
        responseDtoProduct.setDescription(responseProductDomainData.getDescription());
        return responseDtoProduct;
    }

    public List<ResponseDtoProduct> changeProductDomainToResponseDtoList(List<ResponseProductDomainData> responseProductDomainDataList) {
        List<ResponseDtoProduct> responseDtoProductList = new ArrayList<>();
        for (ResponseProductDomainData responseProductDomainData : responseProductDomainDataList) {
            responseDtoProductList.add(changeProductDomainToResponseDtoForList(responseProductDomainData));
        }
        return responseDtoProductList;
    }

    public ResponseDtoProduct changeProductDomainToResponseDtoForList(ResponseProductDomainData responseProductDomainData) {
        ResponseDtoProduct responseDtoProduct = new ResponseDtoProduct();
        if (responseProductDomainData.getStock() > 0) {
            responseDtoProduct.setHasStock(true);
        } else {
            responseDtoProduct.setHasStock(false);
        }
        responseDtoProduct.setImageUrl(responseProductDomainData.getImageUrl());
        responseDtoProduct.setName(responseProductDomainData.getName());
        responseDtoProduct.setPrice(responseProductDomainData.getPrice());
        responseDtoProduct.setPid(responseProductDomainData.getPid());
        responseDtoProduct.setDescription(responseProductDomainData.getDescription());
        return responseDtoProduct;
    }
}
