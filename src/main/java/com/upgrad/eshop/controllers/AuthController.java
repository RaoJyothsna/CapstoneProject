package com.upgrad.eshop.controllers;

import antlr.StringUtils;
import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.LoginResponse;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.*;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.services.UserService;
import com.upgrad.eshop.services.UserServiceImpl;
import com.upgrad.eshop.utils.DTOEntityConverter;
import com.upgrad.eshop.utils.EntityDTOConverter;
import com.upgrad.eshop.validators.UserValidator;
import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /*
     * Credentials for ADMIN
     * username: admin
     * email: admin@upgrad.com
     * password: password
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
            throws APIException, BadCredentialsException {
        logger.debug("Processing login request from " + loginRequest.getUserName());

        logger.debug("Started login request validation for " + loginRequest.getUserName());
        userValidator.validateUserLogin(loginRequest);
        logger.debug("Complete login request validation for " + loginRequest.getUserName());

        logger.debug("Started authentication for " + loginRequest.getUserName());
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );
            logger.debug("Authentication successful.");

            logger.debug("Generating Jwt token for " + loginRequest.getUserName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenProvider.generateToken(authentication);
            logger.debug("Jwt Token generated successfully. Token [" + token + "]");

            final LoginResponse loginResponse = new LoginResponse(loginRequest.getUserName(), "Success", token);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/register")
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest registerRequestRequest)
            throws APIException, CustomException {
        logger.debug("Processing Signup request from " + registerRequestRequest.getUserName());

        try {
            userValidator.validateUserRegistration(registerRequestRequest);
            Map<String,String> regModel=new HashMap<>();
            String username=registerRequestRequest.getUserName();
            String password=registerRequestRequest.getPassword();
            if(username==null || password==null)
            {
                regModel.put("Error", "Username is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(regModel);
            }
            final Authentication authreg = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerRequestRequest.getUserName(),
                            registerRequestRequest.getPassword()
                    )
            );
            String rToken = jwtTokenProvider.generateToken(authreg);
            String token = jwtTokenProvider.generateToken(authreg);
            registerRequestRequest.setJwtToken(token);
            registerRequestRequest.setRefreshToken(rToken);
            RegisterRequest newCustomer = new RegisterRequest();
            User savedCustomer = userService.acceptUserDetails(newCustomer);
            RegisterRequest savedCustomerDTO = entityDTOConverter.convertToCustomerDTO(savedCustomer);
            userService.addToken(token);
            userService.addRefreshToken(token,savedCustomer);
            userService.updateRefreshTokenAccessTokenMap(rToken,token);
            userService.updateAccessTokenUserMap("",token,savedCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerRequestRequest);
        } catch (Exception e) {
            throw new CustomException("Username " + registerRequestRequest.getUserName() + " already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
