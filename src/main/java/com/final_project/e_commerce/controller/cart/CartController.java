package com.final_project.e_commerce.controller.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToDomainFirebaseUser;
import com.final_project.e_commerce.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/items")
public class CartController {

    private final ChangeToDomainFirebaseUser changeToDomainFirebaseUser;
    private final CartService cartService;

    public CartController(ChangeToDomainFirebaseUser changeToDomainFirebaseUser, CartService cartService) {
        this.changeToDomainFirebaseUser = changeToDomainFirebaseUser;
        this.cartService = cartService;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCartItems(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid, @PathVariable Integer quantity){
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.addCartItems(reqFirebaseUserDomain, pid, quantity);
    }
}
