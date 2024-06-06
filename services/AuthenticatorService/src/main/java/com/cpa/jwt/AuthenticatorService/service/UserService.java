package com.cpa.jwt.AuthenticatorService.service;

import org.springframework.stereotype.Service;

import com.cpa.jwt.AuthenticatorService.entity.User;
import com.cpa.jwt.AuthenticatorService.exception.UserNotFoundException;

@Service
public interface UserService {
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
}