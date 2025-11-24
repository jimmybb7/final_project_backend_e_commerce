package com.final_project.e_commerce.exception;

import com.final_project.e_commerce.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CartItemEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleCartItemEmptyException(CartItemEmptyException ex) {
        logger.warn(ex.getMessage());
        return Result.error("404", ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleProductNotFoundException(ProductNotFoundException ex) {
        logger.warn(ex.getMessage());
        return Result.error("404", ex.getMessage());
    }

    @ExceptionHandler(InputQuantityInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleInputQuantityInvalidException(InputQuantityInvalidException ex) {
        logger.warn(ex.getMessage());
        return Result.error("400", ex.getMessage());
    }

    @ExceptionHandler(StockNotEnoughException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleStockNotEnoughException(StockNotEnoughException ex) {
        logger.warn(ex.getMessage());
        return Result.error("400", ex.getMessage());
    }

    @ExceptionHandler(TransactionIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleTransactionIdNotFoundException(TransactionIdNotFoundException ex) {
        logger.warn(ex.getMessage());
        return Result.error("404", ex.getMessage());
    }

    @ExceptionHandler(TransactionStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleTransactionStatusException(TransactionStatusException ex) {
        logger.warn(ex.getMessage());
        return Result.error("400", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception ex) {
        logger.warn(ex.getMessage());
        return Result.error("500", ex.getMessage());
    }
}
