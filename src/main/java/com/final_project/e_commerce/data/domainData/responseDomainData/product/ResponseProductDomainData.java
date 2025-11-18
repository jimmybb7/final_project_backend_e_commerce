package com.final_project.e_commerce.data.domainData.responseDomainData.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseProductDomainData {
    private Integer pid;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private Integer stock;

}
