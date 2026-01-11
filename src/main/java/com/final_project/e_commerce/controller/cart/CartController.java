package com.final_project.e_commerce.controller.cart;

import com.final_project.e_commerce.common.Result;
import com.final_project.e_commerce.config.EnvConfig;
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
@CrossOrigin({EnvConfig.DEV_BASEURL, EnvConfig.PROD_BASEURL})
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
    public Result addCartItems(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid, @PathVariable Integer quantity) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.addCartItems(reqFirebaseUserDomain, pid, quantity);
        return Result.successNoReturnType("204");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Result getFirebaseUserCartItems(@AuthenticationPrincipal Jwt jwt) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        List<ResponseFirebaseUserCartItemDomain> ResponseFirebaseUserCartItemDomainList = cartService.getFirebaseUserCartItems(reqFirebaseUserDomain);
        List<ResponseFirebaseUserCartItemDto> responseFirebaseUserCartItemDtoList = changeToFirebaseUserCartItemDto.changeFirebaseUserCartItemDomainToResponseFirebaseUserCartItemDtoList(ResponseFirebaseUserCartItemDomainList);
        return Result.successWithReturnType("200", responseFirebaseUserCartItemDtoList);
    }

    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Result updateCartQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid, @PathVariable Integer quantity) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.updateCartQuantity(reqFirebaseUserDomain, pid, quantity);
        return Result.successNoReturnType("204");
    }

    @DeleteMapping("{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Result deleteCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable String pid) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        cartService.deleteSingleCartItem(reqFirebaseUserDomain, pid);
        return Result.successNoReturnType("204");
    }
}
