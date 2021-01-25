package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {


    private Map<String, User> refreshTokenUserMap;

    private List<String> tokenStore;

    private Map<String, String> refreshTokenAccessTokenMap;

    private Map<String, User> accessTokenUserMap;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Override
    public Product productDetails(ProductRequest productRequest) throws ProductNotFoundException, ShippingAddressNotFoundException {
        return null;
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public Product updateProductDetails(int pId, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) throws ProductNotFoundException {
        return false;
    }

    @Override
    public List<Product> getAllDetails() {
        return null;
    }
}
