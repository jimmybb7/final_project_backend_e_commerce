package com.final_project.e_commerce.service.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    Page<ResponseProductDomainData> getAllProduct(String keyword, String category, String language, Integer pageNum);

    ResponseProductDomainData getProductById(String pid);

    ProductEntity checkProductWhetherExit(String pid);

    @Transactional
    void deduceStock(List<CartEntity> cartItemEntityList);
}
