package com.final_project.e_commerce.service.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.cart.ResponseFirebaseUserCartItemDomain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {
    @Transactional
    void addCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain, String pid, Integer quantity);

    List<ResponseFirebaseUserCartItemDomain> getFirebaseUserCartItems(ReqFirebaseUserDomain reqFireBaseUserDomain);
}
