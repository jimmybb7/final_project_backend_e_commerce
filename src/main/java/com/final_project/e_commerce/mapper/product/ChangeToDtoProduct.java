package com.final_project.e_commerce.mapper.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChangeToDtoProduct {
//    以下寫法係列明changeProductDomainToResponseDtoList會用changeDomainToDtoForList(listElementMapper)
//
//    ResponseDtoProduct changeProductDomainToResponseDto(ResponseProductDomainData domainData);
//
//    @Named("listElementMapper")
//    @Mapping(target = "stock", ignore = true)
//    @Mapping(target = "hasStock", expression = "java(judgeHasStock(responseProductDomainData))")
//    ResponseDtoProduct changeDomainToDtoForList(ResponseProductDomainData responseProductDomainData);
//
//    @IterableMapping(qualifiedByName = "listElementMapper")
//    List<ResponseDtoProduct> changeProductDomainToResponseDtoList(List<ResponseProductDomainData> responseDomainData);
//
//    default boolean judgeHasStock(ResponseProductDomainData responseProductDomainData) {
//        return responseProductDomainData != null && responseProductDomainData.getStock() != null && responseProductDomainData.getStock() > 0;
//    }
}
