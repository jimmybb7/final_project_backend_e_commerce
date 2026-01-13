package com.final_project.e_commerce.service.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import com.final_project.e_commerce.exception.ProductNotFoundException;
import com.final_project.e_commerce.exception.StockNotEnoughException;
import com.final_project.e_commerce.mapper.product.ChangeToDomainDataProduct;
import com.final_project.e_commerce.repository.product.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ChangeToDomainDataProduct changeToDomainDataProduct;

    public ProductServiceImpl(ProductRepository productRepository, ChangeToDomainDataProduct changeToDomainDataProduct) {
        this.productRepository = productRepository;
        this.changeToDomainDataProduct = changeToDomainDataProduct;
    }

    @Cacheable(cacheNames = "getAllProduct", key = "'allProducts_'+#keyword+'_'+#category+'_'+#language+'_'+#pageNum")
    @Override
    public Page<ResponseProductDomainData> getAllProduct(String keyword, String category, String language, Integer pageNum) {
        if ("ALL".equals(category)) {
            category = null;
        }
        if("".equals(keyword)) {
            keyword = null;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        Pageable pageable = PageRequest.of(pageNum - 1, 10);
        if (language.equals("zh")) {
            Page<ProductEntity> allProductZh = productRepository.getAllProductZh(keyword, category, pageable);
            return allProductZh.map(productEntity -> changeToDomainDataProduct.changeProductEntityToResponseDomain(productEntity));
        } else {
            Page<ProductEntity> allProductEn = productRepository.getAllProductEn(keyword, category, pageable);
            return allProductEn.map(productEntity -> changeToDomainDataProduct.changeProductEntityToResponseDomain(productEntity));
        }
    }

    @Cacheable(cacheNames = "getProductById", key = "#pid")
    @Override
    public ResponseProductDomainData getProductById(String pid) {
        ProductEntity productEntity = checkProductWhetherExit(pid);
        return changeToDomainDataProduct.changeProductEntityToResponseDomain(productEntity);
    }

    @Cacheable(cacheNames = "checkProductWhetherExit", key = "#pid")
    @Override
    public ProductEntity checkProductWhetherExit(String pid) {
        Optional<ProductEntity> productById = productRepository.getProductById(pid);
        if (productById.isPresent()) {
            return productById.get();
        }
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
    @Caching(evict = {
            @CacheEvict(cacheNames = "getProductById", allEntries = true),
            @CacheEvict(cacheNames = "getAllProduct", allEntries = true),
            @CacheEvict(cacheNames = "checkProductWhetherExit", allEntries = true)
    })
    @Transactional
    @Override
    public void deduceStock(List<CartEntity> cartItemEntityList) {
        cartItemEntityList.stream().forEach(cartEntity -> {
            if (cartEntity.getProduct().getStock() < cartEntity.getQuantity()) {
                throw new StockNotEnoughException(cartEntity.getQuantity(), cartEntity.getProduct().getName());
            }
        });

        cartItemEntityList.stream().forEach(cartEntity -> {
            cartEntity.getProduct().setStock(cartEntity.getProduct().getStock() - cartEntity.getQuantity());
            productRepository.save(cartEntity.getProduct());
        });
    }
}
