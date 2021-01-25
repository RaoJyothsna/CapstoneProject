package com.upgrad.eshop.controllers;

import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.CustomException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.services.UserServiceImpl;
import com.upgrad.eshop.utils.DTOEntityConverter;
import com.upgrad.eshop.utils.EntityDTOConverter;
import com.upgrad.eshop.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/users/details")
    @ResponseBody
    public ResponseEntity<?> getUser(@RequestHeader(value = "X-ACCESS-TOKEN") String accessToken)
            throws APIException, CustomException, UserNotFoundException, UsernameNotFoundException {
        logger.debug("Processing Get User Details request from ");

        User user = userService.getUserFromAccessToken(accessToken.trim());
        Map<String, String> model = new HashMap<>();
        model.put("username", user.getUserName());
        model.put("name", user.getFirstName() + " " + user.getLastName());
        model.put("email",user.getEmail());
        model.put("password",user.getPassword());
        model.put("create", String.valueOf(user.getCreated()));
        model.put("phoneNumber",user.getPhoneNumber());
        model.put("role",user.getRole());
        model.put("updated", String.valueOf(user.getUpdated()));

        return ResponseEntity.ok(model);
    }
}
