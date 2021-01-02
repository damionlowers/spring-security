package com.example.admin.controllers;

import com.example.admin.modules.User;
import com.example.admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("admin/api/v1/users")
public class UserCtrl {

    private UserService userService;

    @Autowired
    public UserCtrl(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/create", method = POST)
    public void addUsers(@RequestBody User user){
        userService.insertUser(user);
    }
}
