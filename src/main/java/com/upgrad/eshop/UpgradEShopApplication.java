package com.upgrad.eshop;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.entities.User;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class UpgradEShopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UpgradEShopApplication.class, args);
        System.out.println("Hello");
    }
    @Bean
    CommandLineRunner init (@Qualifier("userRepository") UserRepository user){
        return args -> {

        };
    }
}
