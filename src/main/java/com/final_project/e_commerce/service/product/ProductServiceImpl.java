package com.final_project.e_commerce.service.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import com.final_project.e_commerce.exception.ProductNotFoundException;
import com.final_project.e_commerce.mapper.product.ChangeToDomainDataProduct;
import com.final_project.e_commerce.mapper.product.ChangeToDomainDataProductTesting;
import com.final_project.e_commerce.repository.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ChangeToDomainDataProductTesting changeToDomainDataProductTesting;

    public ProductServiceImpl(ProductRepository productRepository, ChangeToDomainDataProductTesting changeToDomainDataProductTesting) {
        this.productRepository = productRepository;
        this.changeToDomainDataProductTesting = changeToDomainDataProductTesting;
    }

    @Override
    public List<ResponseProductDomainData> getProduct(){
        List<ProductEntity> productList = productRepository.getProduct();
        return changeToDomainDataProductTesting.changeProductEntityToResponseDomainList(productList);
    }

    @Override
    public ResponseProductDomainData getProductById(String pid){
        ProductEntity productEntity = checkProductWhetherExit(pid);
        return changeToDomainDataProductTesting.changeProductEntityToResponseDomain(productEntity);
    }

    public ProductEntity checkProductWhetherExit(String pid){
        Optional<ProductEntity> productById = productRepository.getProductById(pid);
        if(productById.isPresent()){
            return productById.get();
        }
        logger.warn("the product with id " + pid + " does not exist");
        throw new ProductNotFoundException(pid);
    }
}
