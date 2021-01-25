package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class ShippingValidatorImpl implements ShippingValidator {
    @Override
    public void validateShippingDetails(ShippingRequest shippingRequest) throws APIException {
        if(shippingRequest.getCity() == null || shippingRequest.getCity().length() <= 0)
            throw new APIException("Invalid City Detail");
        if(shippingRequest.getLandmark() == null || shippingRequest.getLandmark().length() <= 0 )
            throw new APIException("Invalid Landmark Detail");
        if(shippingRequest.getState() == null || shippingRequest.getState().length() <= 0   )
            throw new APIException("Invalid State Detail");
        if(shippingRequest.getStreet() == null || shippingRequest.getState().length() <= 0 )
            throw new APIException("Invalid Street Details");
        if(shippingRequest.getZipcode() == null || shippingRequest.getZipcode().length() <= 0 )
            throw new APIException("Invalid Zip Details");
        if(shippingRequest.getPhone() == null || shippingRequest.getPhone().length() <= 0 )
            throw new APIException("Invalid Phone Number");
        if(shippingRequest.getName() == null || shippingRequest.getName().length() <= 0 )
            throw new APIException("Invalid Name");

    }
}
