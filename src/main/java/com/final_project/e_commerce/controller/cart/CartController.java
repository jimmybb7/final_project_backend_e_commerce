package com.final_project.e_commerce.controller.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToDomainFirebaseUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/items")
public class CartController {

    private final ChangeToDomainFirebaseUser changeToDomainFirebaseUser;

    public CartController(ChangeToDomainFirebaseUser changeToDomainFirebaseUser) {
        this.changeToDomainFirebaseUser = changeToDomainFirebaseUser;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCartItems(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid, @PathVariable Integer quantity){
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);

    }
}
