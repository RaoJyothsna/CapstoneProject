package com.upgrad.eshop.aspects;

import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.response.UserdefinedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptions {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<UserdefinedResponse> orderNotFound(Exception e)
    {
        UserdefinedResponse response=new UserdefinedResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
    }
}
