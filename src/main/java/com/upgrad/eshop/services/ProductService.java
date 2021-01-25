package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.ShippingRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product productDetails(ProductRequest productRequest)
            throws ProductNotFoundException, ShippingAddressNotFoundException;
    public Product addProduct(ProductRequest productRequest);

    public Product updateProductDetails(int pId, Product product) throws ProductNotFoundException;
    public boolean deleteProduct(int id)  throws ProductNotFoundException ;

    public List<Product> getAllDetails();
}
