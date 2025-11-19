package com.final_project.e_commerce.mapper.cart;

import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ChangeToCartEntity {

    public CartEntity changeToCartEntity(ProductEntity productEntity, FirebaseUserEntity firebaseUserEntity, Integer quantity) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setProduct(productEntity);
        cartEntity.setQuantity(quantity);
        cartEntity.setFirebaseUser(firebaseUserEntity);
        return cartEntity;
    }
}
