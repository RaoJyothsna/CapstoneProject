package com.upgrad.eshop.daos;

import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.services.ShippingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("ShippingRepository")
public interface ShippingRepository extends JpaRepository<ShippingAddress, Integer> {

}
