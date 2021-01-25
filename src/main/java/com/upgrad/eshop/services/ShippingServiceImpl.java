package com.upgrad.eshop.services;


import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "shippingService")
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    @Qualifier("Shipping")
    private UserRepository userRepository;


    private Map<String, User> refreshTokenUserMap;

    private List<String> tokenStore;

    private Map<String, String> refreshTokenAccessTokenMap;

    private Map<String, User> accessTokenUserMap;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public ShippingAddress acceptShippingDetails(ShippingRequest shippingRequest) throws UsernameNotFoundException, ShippingAddressNotFoundException {
        return null;
    }

    @Override
    public ShippingAddress addShippingDetails(ShippingRequest shippingRequest) {
        return null;
    }

    @Override
    public List<ShippingAddress> getAllDetails() {
        return null;
    }
}
