package com.final_project.e_commerce.data.entity.transaction;

import com.final_project.e_commerce.data.entity.firebaseUser.FirebaseUserEntity;
import com.final_project.e_commerce.service.transaction.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "firebase_user_uid", nullable = false)
    private FirebaseUserEntity firebaseUser;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatusEnum status;

    @Column(nullable = false)
    private BigDecimal total;

    private String stripeSessionId;
}
