package com.final_project.e_commerce.repository.cart;

import com.final_project.e_commerce.data.domainData.responseDomainData.cart.ResponseFirebaseUserCartItemDomain;
import com.final_project.e_commerce.data.entity.cart.CartEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {

    @Query(nativeQuery = true,
            value = "select * from cart_item where product_pid = ?1 and firebase_user_uid = ?2")
    Optional<CartEntity> getCartByPidAndUid(int pid, int uid);


    @Query(nativeQuery = true,
            value = "select product.pid, product.name, product.price, product.image_url, cart_item.quantity, product.stock, product.name_en" +
                    " from firebase_user " +
                    "left join cart_item on firebase_user.uid = cart_item.firebase_user_uid " +
                    "left join product on cart_item.product_pid = product.pid " +
                    "where firebase_user.uid = ?1")
    List<ResponseFirebaseUserCartItemDomain> getFirebaseUserCartItemByUid(Integer uid);


    @Query(nativeQuery = true,
            value = "delete from cart_item where product_pid = ?1 and firebase_user_uid = ?2")
    @Modifying
    void deleteCartItemByPidAndUid(String pid, int uid);


    @Query(nativeQuery = true,
            value = "select * from cart_item where firebase_user_uid = ?1")
    List<CartEntity> getCartItemEntityListByUid(int uid);


    @Query(nativeQuery = true,
            value = "delete from cart_item where firebase_user_uid = ?1")
    @Modifying
    void deleteCartItemByUserId(Integer uid);
}
