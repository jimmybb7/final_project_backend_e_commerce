package com.final_project.e_commerce.data.entity.cart;

import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.data.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "cart_item")
@Data
public class CartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn(name = "product_pid",  nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "firebase_user_uid",  nullable = false)
    private FirebaseUserEntity firebaseUser;

    @Column(nullable = false)
    private Integer quantity;
}
