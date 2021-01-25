package com.upgrad.eshop.utils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.upgrad.eshop.aspects.APIExceptions;
import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOEntityConverter {

    @Autowired
    UserService userService;


    public User convertToCustomerEntity(RegisterRequest registerDTO) throws UserNotFoundException {
        User customer = new User();
        customer.setFirstName(registerDTO.getFirstName());
        customer.setLastName(registerDTO.getLastName());
        customer.setUserName(registerDTO.getUserName());
        customer.setPassword(registerDTO.getPassword());
        customer.setPhoneNumber(registerDTO.getPhoneNumber());
        customer.setRole("User");
        return customer;
    }
    public Product convertToProductEntity(ProductRequest productRequest) throws ProductNotFoundException {
        Product product = new Product();
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setImage_url(productRequest.getImage_url());
        product.setManufacturer(productRequest.getManufacturer());
        product.setName(productRequest.getName());
        product.setCreated(productRequest.getCreated());
        product.setUpdated(productRequest.getUpdated());
        return product;
    }
}
