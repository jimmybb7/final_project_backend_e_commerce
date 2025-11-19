package com.final_project.e_commerce.service.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import com.final_project.e_commerce.repository.cart.CartRepository;
import com.final_project.e_commerce.service.firebaseUser.FirebaseUserService;
import com.final_project.e_commerce.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    private final FirebaseUserService firebaseUserService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    public CartServiceImpl(FirebaseUserService firebaseUserService, ProductService productService, CartRepository cartRepository) {
        this.firebaseUserService = firebaseUserService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

//    @Override
    @Transactional
    public void addCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain, String pid, Integer quantity){
        FirebaseUserEntity firebaseUserEntity = firebaseUserService.getFirebaseUserByEmail(reqFireBaseUserDomain);
        ProductEntity productEntity = productService.checkProductWhetherExit(pid);
        Optional<CartEntity> cartByPidAndUid = cartRepository.getCartByPidAndUid(productEntity.getPid(), firebaseUserEntity.getUid());
        if(cartByPidAndUid.isPresent()){
            CartEntity cartEntity = cartByPidAndUid.get();
            Integer originalQuantity = cartEntity.getQuantity();
            cartEntity.setQuantity(originalQuantity + quantity);
        }else {

        }
    }


}
