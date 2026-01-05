package com.final_project.e_commerce.mapper.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeToDomainDataProduct {

    public ResponseProductDomainData changeProductEntityToResponseDomain(ProductEntity productEntity){
        ResponseProductDomainData responseProductDomainData = new ResponseProductDomainData();
        responseProductDomainData.setDescription(productEntity.getDescription());
        responseProductDomainData.setPrice(productEntity.getPrice());
        responseProductDomainData.setName(productEntity.getName());
        responseProductDomainData.setStock(productEntity.getStock());
        responseProductDomainData.setPid(productEntity.getPid());
        responseProductDomainData.setImageUrl(productEntity.getImageUrl());
        responseProductDomainData.setStripePriceId(productEntity.getStripePriceId());
        responseProductDomainData.setProductCategory(productEntity.getProductCategory());
        responseProductDomainData.setNameEn(productEntity.getNameEn());
        responseProductDomainData.setDescriptionEn(productEntity.getDescriptionEn());
        return responseProductDomainData;
    }

    public List<ResponseProductDomainData> changeProductEntityToResponseDomainList(List<ProductEntity> productEntityList){
        List<ResponseProductDomainData> responseProductDomainDataList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList) {
            responseProductDomainDataList.add(changeProductEntityToResponseDomain(productEntity));
        }
        return responseProductDomainDataList;
    }
}
