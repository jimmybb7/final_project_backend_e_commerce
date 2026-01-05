package com.final_project.e_commerce.data.dto.responseDto.cart;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ResponseFirebaseUserCartItemDto implements Serializable {
    private Integer pid;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Integer cartQuantity;
    private Integer stock;
    private String nameEn;
}
