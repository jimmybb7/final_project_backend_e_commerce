package com.final_project.e_commerce.data.domainData.responseDomainData.product;

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

}
