package com.final_project.e_commerce.mapper.cart;

import com.final_project.e_commerce.data.domainData.responseDomainData.cart.ResponseFirebaseUserCartItemDomain;
import com.final_project.e_commerce.data.dto.responseDto.cart.ResponseFirebaseUserCartItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeToFirebaseUserCartItemDto {
    public ResponseFirebaseUserCartItemDto changeFirebaseUserCartItemDomainToResponseFirebaseUserCartItemDto(ResponseFirebaseUserCartItemDomain responseFirebaseUserCartItemDomain) {
        ResponseFirebaseUserCartItemDto responseFirebaseUserCartItemDto = new ResponseFirebaseUserCartItemDto();
        responseFirebaseUserCartItemDto.setCartQuantity(responseFirebaseUserCartItemDomain.getCartQuantity());
        responseFirebaseUserCartItemDto.setName(responseFirebaseUserCartItemDomain.getName());
        responseFirebaseUserCartItemDto.setPrice(responseFirebaseUserCartItemDomain.getPrice());
        responseFirebaseUserCartItemDto.setPid(responseFirebaseUserCartItemDomain.getPid());
        responseFirebaseUserCartItemDto.setStock(responseFirebaseUserCartItemDomain.getStock());
        responseFirebaseUserCartItemDto.setImageUrl(responseFirebaseUserCartItemDomain.getImageUrl());
        return responseFirebaseUserCartItemDto;
    }

    public List<ResponseFirebaseUserCartItemDto> changeFirebaseUserCartItemDomainToResponseFirebaseUserCartItemDtoList(List<ResponseFirebaseUserCartItemDomain> responseFirebaseUserCartItemDomainList) {
        List<ResponseFirebaseUserCartItemDto> responseFirebaseUserCartItemDtoList = new ArrayList<>();
        for (ResponseFirebaseUserCartItemDomain responseFirebaseUserCartItemDomain : responseFirebaseUserCartItemDomainList) {
            responseFirebaseUserCartItemDtoList.add(changeFirebaseUserCartItemDomainToResponseFirebaseUserCartItemDto(responseFirebaseUserCartItemDomain));
        }
        return responseFirebaseUserCartItemDtoList;
    }
}
