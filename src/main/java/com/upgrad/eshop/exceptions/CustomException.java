package com.upgrad.eshop.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends  Exception {
    public CustomException(String message)
    {
        super(message);
    }
    public CustomException(String message,Throwable errorMessage)
    {
        super(message,errorMessage);
    }
    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomException(String refresh_token_not_valid, HttpStatus unprocessableEntity) {
    }
}
