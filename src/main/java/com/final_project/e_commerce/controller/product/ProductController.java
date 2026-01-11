package com.final_project.e_commerce.controller.product;

import com.final_project.e_commerce.common.Result;
import com.final_project.e_commerce.config.EnvConfig;
import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseAllDtoProduct;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import com.final_project.e_commerce.mapper.product.ChangeToDtoProduct;
import com.final_project.e_commerce.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/products")
@CrossOrigin({EnvConfig.DEV_BASEURL, EnvConfig.PROD_BASEURL})
public class ProductController {
    private final ProductService productService;
    private final ChangeToDtoProduct changeToDtoProduct;


    public ProductController(ProductService productService, ChangeToDtoProduct changeToDtoProduct) {
        this.productService = productService;
        this.changeToDtoProduct = changeToDtoProduct;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Result getAllProducts(@RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "zh") String language) {
        Page<ResponseProductDomainData> responseProductDomainDataPageList = productService.getAllProduct(keyword, category, language, pageNum);
        Page<ResponseAllDtoProduct> responseAllDtoProductPageListZ = responseProductDomainDataPageList.map(responseProductDomainData -> changeToDtoProduct.changeProductDomainToResponseDtoForList(responseProductDomainData));
        return Result.successWithReturnType("200", responseAllDtoProductPageListZ);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result getProductById(@PathVariable(name = "id") String pid) {
        ResponseProductDomainData responseProductDomainData = productService.getProductById(pid);
        ResponseDtoProduct responseDtoProduct = changeToDtoProduct.changeProductDomainToResponseDtoById(responseProductDomainData);
        return Result.successWithReturnType("200", responseDtoProduct);
    }

}
