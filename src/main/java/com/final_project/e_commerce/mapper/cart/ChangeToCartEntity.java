package com.final_project.e_commerce.mapper.cart;

import com.final_project.e_commerce.data.entity.cart.CartEntity;
import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ChangeToCartEntity {

    public void changeToCartEntity(ProductEntity productEntity, FirebaseUserEntity firebaseUserEntity, Integer quantity) {}
}
