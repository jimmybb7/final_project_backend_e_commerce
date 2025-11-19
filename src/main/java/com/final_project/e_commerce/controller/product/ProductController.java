package com.final_project.e_commerce.controller.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseAllDtoProduct;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import com.final_project.e_commerce.mapper.product.ChangeToDtoProductTesting;
import com.final_project.e_commerce.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/products")
public class ProductController {
    private final ProductService productService;
    private final ChangeToDtoProductTesting changeToDtoProductTesting;


    public ProductController(ProductService productService, ChangeToDtoProductTesting changeToDtoProductTesting) {
        this.productService = productService;
        this.changeToDtoProductTesting = changeToDtoProductTesting;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseAllDtoProduct> getProducts() {
        List<ResponseProductDomainData> productlist = productService.getProduct();
        return changeToDtoProductTesting.changeProductDomainToResponseDtoList(productlist);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDtoProduct getProductById(@PathVariable(name = "id") String pid) {
        ResponseProductDomainData responseProductDomainData = productService.getProductById(pid);
        return changeToDtoProductTesting.changeProductDomainToResponseDtoById(responseProductDomainData);
    }

}
