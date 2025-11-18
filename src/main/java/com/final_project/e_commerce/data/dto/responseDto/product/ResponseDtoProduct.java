package com.final_project.e_commerce.data.dto.responseDto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseDtoProduct {
    private Integer pid;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private Integer stock;
    private Boolean hasStock;


}
