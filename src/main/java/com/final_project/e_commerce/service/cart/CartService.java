package com.final_project.e_commerce.service.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {
    @Transactional
    void addCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain, String pid, Integer quantity);
}
