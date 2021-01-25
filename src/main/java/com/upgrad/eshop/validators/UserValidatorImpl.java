package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator {

    @Override
    public void validateUserRegistration(RegisterRequest registerRequest) throws APIException {
        if(registerRequest.getFirstName() == null || registerRequest.getFirstName().length() <= 0)
            throw new APIException("Invalid firstname");
        if(registerRequest.getUserName() == null || registerRequest.getUserName().length() <= 0 )
            throw new APIException("Invalid Username");
        if(registerRequest.getPassword() == null || registerRequest.getPassword().length() <= 0   )
            throw new APIException("Invalid password");
        if(registerRequest.getEmail() == null)
            throw new APIException("Invalid MailId");

    }

    @Override
    public void validateUserLogin(LoginRequest loginRequest) throws APIException {
        if(loginRequest.getUserName() == null || loginRequest.getUserName().length() <= 0)
            throw new APIException("Invalid UserName");
        if(loginRequest.getPassword() == null || loginRequest.getPassword().length() <= 0   )
            throw new APIException("Invalid password");
    }
}
