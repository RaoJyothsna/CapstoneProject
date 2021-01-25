package com.upgrad.eshop.utils;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityDTOConverter {
    @Autowired
    UserService customerService;



    public RegisterRequest convertToCustomerDTO(User customer){
        RegisterRequest registerDTO = new RegisterRequest();

        registerDTO.setFirstName(customer.getFirstName());
        registerDTO.setLastName(customer.getLastName());
        registerDTO.setPassword(customer.getPassword());
        registerDTO.setUserName(customer.getUserName());
        registerDTO.setPhoneNumber(customer.getPhoneNumber());

        return registerDTO;
    }
    public ProductRequest convertToProductDTO(Product product){
        ProductRequest productRequest = new ProductRequest();

        productRequest.setCategory(product.getCategory());
        productRequest.setDescription(product.getDescription());
        productRequest.setImage_url(product.getImage_url());
        productRequest.setManufacturer(product.getManufacturer());
        productRequest.setName(product.getName());
        productRequest.setCreated(product.getCreated());
        productRequest.setUpdated(product.getUpdated());
        return productRequest;
    }

}
