package com.ecommerce.productservice.advices;

import com.ecommerce.productservice.dtos.ExceptionDto;
import com.ecommerce.productservice.exceptions.ProductLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling extends Exception {

    @ExceptionHandler
    public ResponseEntity<String> handleException(NullPointerException e){
        return ResponseEntity.status(404).body("unknown error occured");
    }
    @ExceptionHandler
    public  ResponseEntity<ExceptionDto> handleException(ProductLimitException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorCode("400");
        exceptionDto.setErrorMessage("Product limit exceeded: Maximum 100 products are allowed.");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>("something went wrong",HttpStatus.NOT_FOUND);
    }

}
