package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;

public interface ProductValidator {
    public void validateProduct(ProductRequest productRequest) throws APIException, ProductNotFoundException;

}
