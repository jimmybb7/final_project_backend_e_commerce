//package com.final_project.e_commerce.mapper.product;
//
//import com.final_project.e_commerce.data.domainData.responseDomainData.product.ResponseProductDomainData;
//import com.final_project.e_commerce.data.dto.responseDto.product.ResponseAllDtoProduct;
//import com.final_project.e_commerce.data.dto.responseDto.product.ResponseDtoProduct;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface ChangeToDtoProductTest {
//
//    @Mapping(target = "hasStock", expression = "java(responseProductDomainData.getStock() > 0)")
//    ResponseAllDtoProduct changeDomainToDtoForList(ResponseProductDomainData responseProductDomainData);
//
//    List<ResponseAllDtoProduct> changeProductDomainToResponseDtoList(List<ResponseProductDomainData> responseProductDomainDataList);
//
//    ResponseDtoProduct changeProductDomainToResponseDto(ResponseProductDomainData responseProductDomainData);
//}
