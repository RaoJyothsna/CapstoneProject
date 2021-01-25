package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.exceptions.APIException;

public interface ShippingValidator {
    public void validateShippingDetails(ShippingRequest shippingRequest) throws APIException;

}
