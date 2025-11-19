package com.final_project.e_commerce.repository.cart;

import com.final_project.e_commerce.data.entity.cart.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity,Integer> {

@Query(nativeQuery = true,
value = "select * from cart_item where firebase_user_uid = ?1 and product_pid = ?2")
    Optional<CartEntity> getCartByPidAndUid(int pid,int uid);
}
