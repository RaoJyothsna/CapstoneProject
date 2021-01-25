package com.upgrad.eshop.controllers;

import com.sun.el.parser.ParseException;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.services.ProductServiceImpl;
import com.upgrad.eshop.services.ShippingServiceImpl;
import com.upgrad.eshop.utils.DTOEntityConverter;
import com.upgrad.eshop.utils.EntityDTOConverter;
import com.upgrad.eshop.validators.ProductValidatorImpl;
import com.upgrad.eshop.validators.ShippingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductValidatorImpl productValidator;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @GetMapping("/product/{id}")
    public ResponseEntity gerProduct(@PathVariable(name = "id") int id) throws ProductNotFoundException, APIException {
        Product resProduct = productService.getAllDetails().get(id);
        ProductRequest response = entityDTOConverter.convertToProductDTO(resProduct);
        logger.debug("Get movie details :" + response);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value="/product/{id}",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductRequest productRequest) throws  APIException, ParseException, ProductNotFoundException {
        logger.debug("update movie details : movie id :" + id, productRequest);
        productValidator.validateProduct(productRequest);
        Product newP = dtoEntityConverter.convertToProductEntity(productRequest);
        Product updatedMovie = productService.updateProductDetails(id, newP);
        ProductRequest updatedMovieDTO = entityDTOConverter.convertToProductDTO(updatedMovie);
        return  ResponseEntity.ok(updatedMovieDTO);
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable(name = "id") int id) throws ProductNotFoundException{
        productService.deleteProduct(id);
        logger.debug("Deleting Product details : " + id);
        return new ResponseEntity<>("Product details successfully removed ",HttpStatus.OK);
    }



}
