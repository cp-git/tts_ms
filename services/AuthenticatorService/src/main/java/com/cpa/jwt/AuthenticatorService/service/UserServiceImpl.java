package com.cpa.jwt.AuthenticatorService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpa.jwt.AuthenticatorService.entity.User;
import com.cpa.jwt.AuthenticatorService.exception.UserNotFoundException;
import com.cpa.jwt.AuthenticatorService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	 
	
	@Autowired
    private UserRepository userRepository;

   
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(name, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }

}