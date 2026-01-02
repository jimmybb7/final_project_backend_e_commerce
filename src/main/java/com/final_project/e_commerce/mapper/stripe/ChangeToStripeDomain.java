package com.final_project.e_commerce.mapper.stripe;

import com.final_project.e_commerce.data.domainData.responseDomainData.stripe.ResponseStripeDomain;
import org.springframework.stereotype.Component;

@Component
public class ChangeToStripeDomain {
    public ResponseStripeDomain  changeToStripeDomain(String stripePaymentSessionUrl){
        ResponseStripeDomain responseStripeDomain = new ResponseStripeDomain();
        responseStripeDomain.setStripeSessionUrl(stripePaymentSessionUrl);
        return responseStripeDomain;
    }
}
