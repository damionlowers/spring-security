package com.example.admin.services;

import com.example.admin.Repository.UserRepository;
import com.example.admin.modules.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void insertUser(User user){
        userRepository.createUser(user);
    }


}
