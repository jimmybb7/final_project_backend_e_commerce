package com.final_project.e_commerce.controller.transaction;

import com.final_project.e_commerce.common.Result;
import com.final_project.e_commerce.config.EnvConfig;
import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.stripe.ResponseStripeDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.dto.responseDto.stripe.ResponseStripeDto;
import com.final_project.e_commerce.data.dto.responseDto.transaction.ResponseTransactionDto;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToDomainFirebaseUser;
import com.final_project.e_commerce.mapper.stripe.ChangeToStripeDto;
import com.final_project.e_commerce.mapper.transaction.ChangeToTransactionDto;
import com.final_project.e_commerce.service.transaction.TransactionService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin({EnvConfig.DEV_BASEURL, EnvConfig.PROD_BASEURL})
public class TransactionController {

    private final ChangeToDomainFirebaseUser changeToDomainFirebaseUser;
    private final TransactionService transactionService;
    private final ChangeToTransactionDto changeToTransactionDto;
    private final ChangeToStripeDto changeToStripeDto;

    public TransactionController(ChangeToDomainFirebaseUser changeToDomainFirebaseUser, TransactionService transactionService, ChangeToTransactionDto changeToTransactionDto, ChangeToStripeDto changeToStripeDto) {
        this.changeToDomainFirebaseUser = changeToDomainFirebaseUser;
        this.transactionService = transactionService;
        this.changeToTransactionDto = changeToTransactionDto;
        this.changeToStripeDto = changeToStripeDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result createTransaction(@AuthenticationPrincipal Jwt jwt) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseTransactionDomain responseTransactionDomain = transactionService.createTransaction(reqFirebaseUserDomain);
        ResponseTransactionDto responseTransactionDto = changeToTransactionDto.responseTransactionDomainChangeToResponseTransactionDto(responseTransactionDomain, responseTransactionDomain.getProducts());
        return Result.successWithReturnType("201", responseTransactionDto);
    }

    @GetMapping("/{tid}")
    @ResponseStatus(HttpStatus.OK)
    public Result getTransaction(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseTransactionDomain responseTransactionDomain = transactionService.getTransaction(reqFirebaseUserDomain, tid);
        ResponseTransactionDto responseTransactionDto = changeToTransactionDto.responseTransactionDomainChangeToResponseTransactionDto(responseTransactionDomain, responseTransactionDomain.getProducts());
        return Result.successWithReturnType("200", responseTransactionDto);
    }

    @PatchMapping("/{tid}/payment")
    @ResponseStatus(HttpStatus.OK)
    public Result updateTransactionStatusToProcessing(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) throws StripeException {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseStripeDomain responseStripeDomain = transactionService.updateTransactionStatusToProcessing(reqFirebaseUserDomain, tid);
        ResponseStripeDto responseStripeDto = changeToStripeDto.changeResponseStripeDomainToStripeDto(responseStripeDomain);
        return Result.successWithReturnType("200", responseStripeDto);
    }

    @PatchMapping("/{tid}/success")
    @ResponseStatus(HttpStatus.OK)
    public Result updateTransactionStatusToSuccess(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) throws StripeException {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseTransactionDomain responseTransactionDomain = transactionService.updateTransactionStatusToSuccess(reqFirebaseUserDomain, tid);
        ResponseTransactionDto responseTransactionDto = changeToTransactionDto.responseTransactionDomainChangeToResponseTransactionDto(responseTransactionDomain, responseTransactionDomain.getProducts());
        return Result.successWithReturnType("200", responseTransactionDto);
    }

    @GetMapping("orderRecordList")
    @ResponseStatus(HttpStatus.OK)
    public Result getUserOrderRecordList(@AuthenticationPrincipal Jwt jwt) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        List<ResponseTransactionDomain> responseTransactionDomainList = transactionService.getUserOrderRecordList(reqFirebaseUserDomain);
        List<ResponseTransactionDto> responseTransactionDtoList = changeToTransactionDto.responseTransactionDomainListToResponseTransactionDtoList(responseTransactionDomainList);
        return  Result.successWithReturnType("200", responseTransactionDtoList);
    }

    @GetMapping("/transactionProduct/{tid}")
    @ResponseStatus(HttpStatus.OK)
    public Result getUserOrderRecordDetail(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseTransactionDomain responseTransactionDomain = transactionService.getTransaction(reqFirebaseUserDomain, tid);
        ResponseTransactionDto responseTransactionDto = changeToTransactionDto.responseTransactionDomainChangeToResponseTransactionDto(responseTransactionDomain, responseTransactionDomain.getProducts());
        return Result.successWithReturnType("200", responseTransactionDto);
    }


}
