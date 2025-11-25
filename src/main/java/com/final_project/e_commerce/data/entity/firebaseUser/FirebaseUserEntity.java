package com.final_project.e_commerce.data.entity.firebaseUser;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "firebase_user",
        indexes = {@Index(name = "idx_firebase_user_email", columnList = "email")}
)
@Data
public class FirebaseUserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String firebaseId;
}
