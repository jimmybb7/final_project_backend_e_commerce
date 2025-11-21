package com.final_project.e_commerce.controller.transaction;

import com.final_project.e_commerce.data.domainData.reqDomainData.firebaseUser.ReqFirebaseUserDomain;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.dto.responseDto.transaction.ResponseTransactionDto;
import com.final_project.e_commerce.mapper.firebaseUser.ChangeToDomainFirebaseUser;
import com.final_project.e_commerce.mapper.transaction.ChangeToTransactionDto;
import com.final_project.e_commerce.service.transaction.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final ChangeToDomainFirebaseUser changeToDomainFirebaseUser;
    private final TransactionService transactionService;
    private final ChangeToTransactionDto changeToTransactionDto;

    public TransactionController(ChangeToDomainFirebaseUser changeToDomainFirebaseUser, TransactionService transactionService, ChangeToTransactionDto changeToTransactionDto) {
        this.changeToDomainFirebaseUser = changeToDomainFirebaseUser;
        this.transactionService = transactionService;
        this.changeToTransactionDto = changeToTransactionDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseTransactionDto createTransaction(@AuthenticationPrincipal Jwt jwt) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseTransactionDomain responseTransactionDomain = transactionService.createTransaction(reqFirebaseUserDomain);
        return changeToTransactionDto.responseTransactionDomainChangeToResponseTransactionDto(responseTransactionDomain, responseTransactionDomain.getProducts());
    }

    @GetMapping("/{tid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTransactionDto getTransaction(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        ResponseTransactionDomain responseTransactionDomain = transactionService.getTransaction(reqFirebaseUserDomain, tid);
        return changeToTransactionDto.responseTransactionDomainChangeToResponseTransactionDto(responseTransactionDomain, responseTransactionDomain.getProducts());
    }

    @PatchMapping("/{tid}/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTransactionStatusToProcessing(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        ReqFirebaseUserDomain reqFirebaseUserDomain = changeToDomainFirebaseUser.changeJwtToReqDomainFirebaseUser(jwt);
        transactionService.updateTransactionStatusToProcessing(reqFirebaseUserDomain, tid);
    }
}
