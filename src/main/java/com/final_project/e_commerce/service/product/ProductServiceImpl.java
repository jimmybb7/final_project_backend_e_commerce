package com.final_project.e_commerce.service.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import com.final_project.e_commerce.exception.ProductNotFoundException;
import com.final_project.e_commerce.exception.StockNotEnoughException;
import com.final_project.e_commerce.mapper.product.ChangeToDomainDataProduct;
import com.final_project.e_commerce.repository.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ChangeToDomainDataProduct changeToDomainDataProduct;

    public ProductServiceImpl(ProductRepository productRepository, ChangeToDomainDataProduct changeToDomainDataProduct) {
        this.productRepository = productRepository;
        this.changeToDomainDataProduct = changeToDomainDataProduct;
    }

    @Override
    public List<ResponseProductDomainData> getProduct() {
        List<ProductEntity> productList = productRepository.getProduct();
        return changeToDomainDataProduct.changeProductEntityToResponseDomainList(productList);
    }

    @Override
    public ResponseProductDomainData getProductById(String pid) {
        ProductEntity productEntity = checkProductWhetherExit(pid);
        return changeToDomainDataProduct.changeProductEntityToResponseDomain(productEntity);
    }

    @Override
    public ProductEntity checkProductWhetherExit(String pid) {
        Optional<ProductEntity> productById = productRepository.getProductById(pid);
        if (productById.isPresent()) {
            return productById.get();
        }
        logger.warn("the product with id " + pid + " does not exist");
        throw new ProductNotFoundException(pid);
    }


//  for loop method
//    @Transactional
//    @Override
//    public void deduceStock(List<CartEntity> cartItemEntityList) {
//        for (CartEntity cartEntity : cartItemEntityList) {
//            if (cartEntity.getProduct().getStock() < cartEntity.getQuantity()) {
//                logger.warn("Product: {} - Quantity: {}, stock is not enough", cartEntity.getProduct().getName(), cartEntity.getQuantity());
//                throw new StockNotEnoughException(cartEntity.getQuantity(), cartEntity.getProduct().getName());
//            } else {
//                cartEntity.getProduct().setStock(cartEntity.getProduct().getStock() - cartEntity.getQuantity());
//            }
//        }
//    }


    //    stream method
    @Transactional
    @Override
    public void deduceStock(List<CartEntity> cartItemEntityList) {
        cartItemEntityList.stream().forEach(cartEntity -> {
            if (cartEntity.getProduct().getStock() < cartEntity.getQuantity()) {
                logger.warn("Product: {} - Quantity: {}, stock is not enough", cartEntity.getProduct().getName(), cartEntity.getQuantity());
                throw new StockNotEnoughException(cartEntity.getQuantity(), cartEntity.getProduct().getName());
            }
        });

        cartItemEntityList.stream().forEach(cartEntity -> {
            cartEntity.getProduct().setStock(cartEntity.getProduct().getStock() - cartEntity.getQuantity());
        });
    }
}
