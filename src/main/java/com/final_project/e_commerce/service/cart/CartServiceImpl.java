package com.final_project.e_commerce.service.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.cart.ResponseFirebaseUserCartItemDomain;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import com.final_project.e_commerce.mapper.cart.ChangeToCartEntity;
import com.final_project.e_commerce.repository.cart.CartRepository;
import com.final_project.e_commerce.service.firebaseUser.FirebaseUserService;
import com.final_project.e_commerce.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    private final FirebaseUserService firebaseUserService;
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final ChangeToCartEntity changeToCartEntity;

    public CartServiceImpl(FirebaseUserService firebaseUserService, ProductService productService, CartRepository cartRepository, ChangeToCartEntity changeToCartEntity) {
        this.firebaseUserService = firebaseUserService;
        this.productService = productService;
        this.cartRepository = cartRepository;
        this.changeToCartEntity = changeToCartEntity;
    }

    @Transactional
    @Override
    public void addCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain, String pid, Integer quantity){
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFireBaseUserDomain);
        ProductEntity productEntity = productService.checkProductWhetherExit(pid);
        Optional<CartEntity> cartByPidAndUid = cartRepository.getCartByPidAndUid(productEntity.getPid(), firebaseUserEntity.getUid());
        if(cartByPidAndUid.isPresent()){
            CartEntity cartEntity = cartByPidAndUid.get();
            Integer originalQuantity = cartEntity.getQuantity();
            cartEntity.setQuantity(originalQuantity + quantity);
        }else {
            cartRepository.save(changeToCartEntity.changeToCartEntity(productEntity,firebaseUserEntity,quantity));
        }
    }

    @Override
    public List<ResponseFirebaseUserCartItemDomain> getFirebaseUserCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain){
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFireBaseUserDomain);
        return cartRepository.getFirebaseUserCartItemByUid(firebaseUserEntity.getUid());
    }


}
