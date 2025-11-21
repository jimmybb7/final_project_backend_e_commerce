package com.final_project.e_commerce.service.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.cart.ResponseFirebaseUserCartItemDomain;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import com.final_project.e_commerce.exception.CartItemEmptyException;
import com.final_project.e_commerce.exception.InputQuantityInvalidException;
import com.final_project.e_commerce.exception.StockNotEnoughException;
import com.final_project.e_commerce.mapper.cart.ChangeToCartEntity;
import com.final_project.e_commerce.repository.cart.CartRepository;
import com.final_project.e_commerce.service.firebaseUser.FirebaseUserService;
import com.final_project.e_commerce.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final FirebaseUserService firebaseUserService;
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final ChangeToCartEntity changeToCartEntity;
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public CartServiceImpl(FirebaseUserService firebaseUserService, ProductService productService, CartRepository cartRepository, ChangeToCartEntity changeToCartEntity) {
        this.firebaseUserService = firebaseUserService;
        this.productService = productService;
        this.cartRepository = cartRepository;
        this.changeToCartEntity = changeToCartEntity;
    }

    @Transactional
    @Override
    public void addCartItems(ReqFirebaseUserDomain reqFirebaseUserDomain, String pid, Integer quantity) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFirebaseUserDomain);
        ProductEntity productEntity = productService.checkProductWhetherExit(pid);
        if (checkValidQuantity(productEntity, quantity)) {
            Optional<CartEntity> cartByPidAndUid = cartRepository.getCartByPidAndUid(productEntity.getPid(), firebaseUserEntity.getUid());
            if (cartByPidAndUid.isPresent()) {
                CartEntity cartEntity = cartByPidAndUid.get();
                Integer originalQuantity = cartEntity.getQuantity();
                if (originalQuantity + quantity > productEntity.getStock()) {
                    logger.warn(productEntity.getName() + " quantity add more than the stock");
                    throw new StockNotEnoughException(quantity);
                } else {
                    cartEntity.setQuantity(originalQuantity + quantity);
                }
            } else {
                cartRepository.save(changeToCartEntity.changeToCartEntity(productEntity, firebaseUserEntity, quantity));
            }
        }
    }

    public boolean checkValidQuantity(ProductEntity productEntity, int quantity) {
        if (quantity <= 0) {
            logger.warn("input quantity invalid " + quantity + ", input must be a positive integer");
            throw new InputQuantityInvalidException(quantity);
        }
        if (productEntity.getStock() < quantity) {
            logger.warn(productEntity.getName() + " quantity add more than the stock");
            throw new StockNotEnoughException(quantity);
        }
        return true;
    }

    @Override
    public List<ResponseFirebaseUserCartItemDomain> getFirebaseUserCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFireBaseUserDomain);
        return cartRepository.getFirebaseUserCartItemByUid(firebaseUserEntity.getUid());
    }

    @Transactional
    @Override
    public void updateCartQuantity(ReqFirebaseUserDomain reqFireBaseUserDomain, String pid, Integer quantity) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFireBaseUserDomain);
        ProductEntity productEntity = productService.checkProductWhetherExit(pid);
        if (checkValidQuantity(productEntity, quantity)) {
            Optional<CartEntity> cartByPidAndUid = cartRepository.getCartByPidAndUid(productEntity.getPid(), firebaseUserEntity.getUid());
            if (cartByPidAndUid.isPresent()) {
                cartByPidAndUid.get().setQuantity(quantity);
            } else {
                cartRepository.save(changeToCartEntity.changeToCartEntity(productEntity, firebaseUserEntity, quantity));
            }
        }
    }

    @Transactional
    @Override
    public void deleteCartItem(ReqFirebaseUserDomain reqFirebaseUserDomain, String pid) {
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFirebaseUserDomain);
        cartRepository.deleteCartItemByPidAndUid(pid, firebaseUserEntity.getUid());
    }

    @Override
    public List<CartEntity> getCartItemEntityListByUid(FirebaseUserEntity firebaseUserEntity) {
        List<CartEntity> cartItemEntityList = cartRepository.getCartItemEntityListByUid(firebaseUserEntity.getUid());
        if (cartItemEntityList.isEmpty()) {
            logger.warn("No cartItemEntity found");
            throw new CartItemEmptyException(firebaseUserEntity.getUid());
        }
        return cartItemEntityList;
    }
}
