package com.final_project.e_commerce.controller.cart;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.cart.ResponseFirebaseUserCartItemDomain;
import com.final_project.e_commerce.data.dto.responseDto.cart.ResponseFirebaseUserCartItemDto;
import com.final_project.e_commerce.mapper.cart.ChangeToFirebaseUserCartItemDto;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToDomainFirebaseUser;
import com.final_project.e_commerce.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/items")
public class CartController {

    private final ChangeToDomainFirebaseUser changeToDomainFirebaseUser;
    private final CartService cartService;
    private final ChangeToFirebaseUserCartItemDto changeToFirebaseUserCartItemDto;

    public CartController(ChangeToDomainFirebaseUser changeToDomainFirebaseUser, CartService cartService, ChangeToFirebaseUserCartItemDto changeToFirebaseUserCartItemDto) {
        this.changeToDomainFirebaseUser = changeToDomainFirebaseUser;
        this.cartService = cartService;
        this.changeToFirebaseUserCartItemDto = changeToFirebaseUserCartItemDto;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCartItems(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid, @PathVariable Integer quantity) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.addCartItems(reqFirebaseUserDomain, pid, quantity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseFirebaseUserCartItemDto> getFirebaseUserCartItems(@AuthenticationPrincipal Jwt jwt) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        List<ResponseFirebaseUserCartItemDomain> ResponseFirebaseUserCartItemDomainList = cartService.getFirebaseUserCartItems(reqFirebaseUserDomain);
        return changeToFirebaseUserCartItemDto.changeFirebaseUserCartItemDomainToResponseFirebaseUserCartItemDtoList(ResponseFirebaseUserCartItemDomainList);
    }

    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid, @PathVariable Integer quantity) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.updateCartQuantity(reqFirebaseUserDomain, pid, quantity);
    }

    @DeleteMapping("{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.deleteSingleCartItem(reqFirebaseUserDomain, pid);
    }
}
