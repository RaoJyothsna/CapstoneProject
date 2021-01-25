package com.upgrad.eshop.controllers;

import com.upgrad.eshop.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTypeController {

    @Autowired
    UserServiceImpl userService;

}
