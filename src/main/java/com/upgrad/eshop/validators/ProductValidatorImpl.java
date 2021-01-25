package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductValidatorImpl implements ProductValidator {
    @Override
    public void validateProduct(ProductRequest productRequest) throws APIException, ProductNotFoundException {

    }
}
