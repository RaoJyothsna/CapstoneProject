package com.upgrad.eshop.aspects;

import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import com.upgrad.eshop.response.UserdefinedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserExceptions {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserdefinedResponse> userDetailsNotFoundexceptions(Exception e)
    {
        UserdefinedResponse response=new UserdefinedResponse((e.getMessage()), HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler(UsernameAlreadyRegisteredException.class)
    public ResponseEntity<UserdefinedResponse> userAlreadyExistsExceptions(Exception e)
    {
        UserdefinedResponse response=new UserdefinedResponse((e.getMessage()), HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<UserdefinedResponse> emailAlreadyExistException(Exception e)
    {
        UserdefinedResponse response=new UserdefinedResponse((e.getMessage()), HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
    }
}
