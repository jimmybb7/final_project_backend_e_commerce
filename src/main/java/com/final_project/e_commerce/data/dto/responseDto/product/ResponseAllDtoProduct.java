package com.final_project.e_commerce.data.dto.responseDto.product;

import com.final_project.e_commerce.data.entity.product.ProductCategoryEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ResponseAllDtoProduct implements Serializable {
    private Integer pid;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private Boolean hasStock;
    private ProductCategoryEnum  productCategory;
    private String nameEn;
}
