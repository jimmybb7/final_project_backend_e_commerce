package com.final_project.e_commerce.service.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;

import java.util.List;

public interface ProductService {
    List<ResponseProductDomainData> getProduct();

    ResponseProductDomainData getProductById(String pid);
}
