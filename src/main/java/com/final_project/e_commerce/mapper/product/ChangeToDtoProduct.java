package com.final_project.e_commerce.mapper.product;

import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseAllDtoProduct;
import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChangeToDtoProduct {
    //    以下寫法係列明changeProductDomainToResponseDtoList會用changeDomainToDtoForList(listElementMapper)
//
//    ResponseDtoProduct changeProductDomainToResponseDto(ResponseProductDomainData domainData);
//
//
//    @Mapping(target = "hasStock", expression = "java(judgeHasStock(responseProductDomainData))")
//    ResponseAllDtoProduct changeDomainToDtoForList(ResponseProductDomainData responseProductDomainData);
//
//
//    List<ResponseAllDtoProduct> changeProductDomainToResponseDtoList(List<ResponseProductDomainData> responseDomainData);
//
//    default boolean judgeHasStock(ResponseProductDomainData responseProductDomainData) {
//        return responseProductDomainData != null && responseProductDomainData.getStock() != null && responseProductDomainData.getStock() > 0;
//    }
    ResponseAllDtoProduct changeDomainToDtoForList(ResponseProductDomainData responseProductDomainData);

    default Boolean stockToHasStock(Integer stock) {
        return stock != null && stock > 0;
    }

    List<ResponseAllDtoProduct> changeProductDomainToResponseDtoList(List<ResponseProductDomainData> responseProductDomainDataList);

    ResponseDtoProduct changeProductDomainToResponseDto(ResponseProductDomainData responseProductDomainData);
}
