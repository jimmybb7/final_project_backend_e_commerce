package com.final_project.e_commerce.data.entity.product;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product",
        indexes = {@Index(name = "idx_product_name", columnList = "name")})
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private String createDateTime;

    @Column(nullable = false)
    private String updateDateTime;


}
