package com.final_project.e_commerce.data.entity.transactionProduct;

import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction_product",
indexes = {
        @Index(name = "idx_transaction_product_name", columnList = "name")
})
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @ManyToOne
    @JoinColumn(name = "transaction_tid",  nullable = false)
    private TransactionEntity transaction;

    @Column(nullable = false)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer quantity;
}
