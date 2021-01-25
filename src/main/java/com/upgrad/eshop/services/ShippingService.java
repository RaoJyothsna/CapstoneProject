package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShippingService {
    public ShippingAddress acceptShippingDetails(ShippingRequest shippingRequest)
            throws UsernameNotFoundException, ShippingAddressNotFoundException;
    public ShippingAddress addShippingDetails(ShippingRequest shippingRequest);
    public List<ShippingAddress> getAllDetails();
}
