package com.abdellah.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abdellah.demo.entity.User;
import com.abdellah.demo.repository.UserRepository;
import com.abdellah.demo.service.UserAuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
public class AuthController 
{
    private final UserAuthService userService;

    public AuthController( UserAuthService userService )
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String auth(@RequestBody User user) 
    {
        if(! this.userService.isUserFormValid(user))
        {
            return "Invalid form";
        }

        userService.createUser(user);
        return "Account Created";
    }
    
}
