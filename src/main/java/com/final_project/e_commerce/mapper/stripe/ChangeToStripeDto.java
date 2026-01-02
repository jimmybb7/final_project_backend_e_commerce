package com.final_project.e_commerce.mapper.stripe;

import com.final_project.e_commerce.data.domainData.responseDomainData.stripe.ResponseStripeDomain;
import com.final_project.e_commerce.data.dto.responseDto.stripe.ResponseStripeDto;
import org.springframework.stereotype.Component;

@Component
public class ChangeToStripeDto {

    public ResponseStripeDto changeResponseStripeDomainToStripeDto(ResponseStripeDomain responseStripeDomain) {
        ResponseStripeDto responseStripeDto = new ResponseStripeDto();
        responseStripeDto.setStripeSessionUrl(responseStripeDomain.getStripeSessionUrl());
        return responseStripeDto;
    }
}
