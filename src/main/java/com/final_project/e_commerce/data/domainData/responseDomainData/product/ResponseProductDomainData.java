package com.final_project.e_commerce.data.domainData.responseDomainData.product;

import com.final_project.e_commerce.data.entity.product.ProductCategoryEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ResponseProductDomainData implements Serializable {
    private Integer pid;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private Integer stock;
    private String stripePriceId;
    private ProductCategoryEnum productCategory;
    private String nameEn;
    private String descriptionEn;
}
