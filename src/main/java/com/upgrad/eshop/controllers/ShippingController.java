package com.upgrad.eshop.controllers;

import com.sun.el.parser.ParseException;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.BadCredentialsException;
import com.upgrad.eshop.exceptions.CustomException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.services.ShippingServiceImpl;
import com.upgrad.eshop.services.UserServiceImpl;
import com.upgrad.eshop.utils.DTOEntityConverter;
import com.upgrad.eshop.utils.EntityDTOConverter;
import com.upgrad.eshop.validators.ShippingValidator;
import com.upgrad.eshop.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-address/")
public class ShippingController {

    @Autowired
    private ShippingValidator shippingValidator;

    @Autowired
    private ShippingServiceImpl shippingService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @RequestMapping(method = RequestMethod.POST, value = "/user-addresses")
    @ResponseBody
    public ResponseEntity<?> addAddress(@RequestBody ShippingRequest address)
            throws APIException, ShippingAddressNotFoundException {
        logger.debug("Processing Shipping Address ");
            ResponseEntity responseEntity=null;
        try {
            shippingValidator.validateShippingDetails(address);
            Map<String,String> regModel=new HashMap<>();
            String state=address.getState();
            String city=address.getCity();
            String zipcode=address.getZipcode();
            String street=address.getStreet();
            String landmark=address.getLandmark();
            String phone=address.getPhone();
            String name=address.getName();
            if(state==null || city==null || zipcode==null || street==null||landmark==null||phone==null||name==null)
            {
                regModel.put("Error", "Invalid Shipping details");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(regModel);
            }

            shippingValidator.validateShippingDetails(address);
            ShippingAddress shippingAddress = shippingService.acceptShippingDetails(address);
            responseEntity = ResponseEntity.ok(shippingAddress);
        } catch (AuthenticationException e) {
            throw new ShippingAddressNotFoundException("Invalid Details!");
        }
        return responseEntity;
    }
}
