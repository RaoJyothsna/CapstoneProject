package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepository;


    private Map<String, User> refreshTokenUserMap;

    private List<String> tokenStore;

    private Map<String, String> refreshTokenAccessTokenMap;

    private Map<String, User> accessTokenUserMap;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//  TODO: Define the acceptUserDetails(), findByUsername(), findById(), addUser()and saveUser() methods by
//        using the necessary logic as per the method name and by overriding them from the UserService interface
@PostConstruct
public void init() {
    refreshTokenUserMap = new HashMap<>();
    tokenStore = new ArrayList<>();
    refreshTokenAccessTokenMap = new HashMap<>();
    accessTokenUserMap = new HashMap<>();
}
    public User getUserFromAccessToken(String accessToken) {
        return accessTokenUserMap.get(accessToken);
    }

    public void removeUserFromAccessTokenMap(String accessToken) {
        if (accessTokenUserMap.containsKey(accessToken)) {
            accessTokenUserMap.remove(accessToken);
        }
    }


    public void updateAccessTokenUserMap(String accessTokenPrev, String accessTokenNew, User u) {
        if (accessTokenUserMap.containsKey(accessTokenPrev)) {
            User user = accessTokenUserMap.get(accessTokenPrev);
            accessTokenUserMap.remove(accessTokenPrev);
            accessTokenUserMap.put(accessTokenNew, user);
        } else {
            accessTokenUserMap.put(accessTokenNew, u);
        }
    }
    public boolean isTokenPresent(String token) {
        return tokenStore.contains(token);
    }

    public void removeTokenIfPresent(String token) {
        if (tokenStore.contains(token)) {
            tokenStore.remove(token);
        }
    }

    public void updateRefreshTokenAccessTokenMap(String refreshToken, String accessToken) {
        this.refreshTokenAccessTokenMap.put(refreshToken, accessToken);
    }

    public void removeRefreshTokenAccessTokenMap(String refreshToken) {
        if (this.refreshTokenAccessTokenMap.containsKey(refreshToken)) {
            this.refreshTokenAccessTokenMap.remove(refreshToken);
        }
    }

    public String getCurrentAccessTokenFromRefreshToken(String refreshToken) {
        return this.refreshTokenAccessTokenMap.get(refreshToken);
    }

    public void addToken(String token) {
        tokenStore.add(token);
    }

    public void removeRefreshToken(String refreshToken) {
        if (refreshTokenUserMap.containsKey(refreshToken)) {
            refreshTokenUserMap.remove(refreshToken);
        }
    }

    public User getUserfromRefreshToken(String refreshToken) {
        return refreshTokenUserMap.get(refreshToken);
    }


    public String getRefreshTokenForUser(String username) {

        User customerUser = null;
        List<User> customers = getAllUserDetails();
        for (User user : customers) {
            if (user.getUserName().equals(username)) {
                customerUser = user;
                break;
            }
        }

        if (customerUser == null) { //User is not present
            return null;
        }
        for (Map.Entry<String, User> entry : refreshTokenUserMap.entrySet()) {
            if (entry.getValue().equals(customerUser)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void addRefreshToken(String refreshToken, User customer) {
        this.refreshTokenUserMap.put(refreshToken, customer);
    }

    @Override
    public User acceptUserDetails(RegisterRequest registerRequest) throws UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException {

    return null;
    }

    @Override
    public User findByUsername(String username) {
        User customer = userRepository.findByUserName(username);
        if(customer==null)
            new UsernameNotFoundException("Customer not found for username" + username);
        return customer;
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        User customer = userRepository.findById(id).orElseThrow(
                ()->  new UserNotFoundException("Customer not found for username" + id));
        return customer;
    }

    @Override
    public User addUser(RegisterRequest registerRequest) {
        logger.debug("Entered acceptCustomerDetails", registerRequest);
        User user=null;
        if((userRepository.findByUserName(String.valueOf((registerRequest.getUserName()==null))))==null){
            user = new User();
            return  userRepository.save(user);
        }else{
            throw new UsernameNotFoundException("This username already exists please choose another : " + registerRequest.getUserName());
        }
    }

    @Override
    public User saveUser(User user) throws UsernameAlreadyRegisteredException {
        logger.debug(
                "Entered User Details",user);
        if((userRepository.findByUserName(user.getUserName()))==null)
        {
            return userRepository.save(user);
        }
        else
            throw new UsernameAlreadyRegisteredException("This USerName Already Exists");
    }

    @Override
    public User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername());
    }

    @Override
    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }
}
