package com.upgrad.eshop.daos;

import com.upgrad.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);
    public Optional<User> findByEmail(String email);
}
