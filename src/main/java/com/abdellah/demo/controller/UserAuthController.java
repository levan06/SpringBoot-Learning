package com.abdellah.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abdellah.demo.entity.User;
import com.abdellah.demo.repository.UserRepository;
import com.abdellah.demo.service.UserAuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
public class UserAuthController 
{
    private final UserAuthService userService;

    public UserAuthController( UserAuthService userService )
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) 
    {
        String registerResponse = this.userService.isUserRegisterValid(user); 

        if( ! registerResponse.equals("Valid") )
        {
            return ResponseEntity.badRequest()
                             .body(registerResponse);
        }

        userService.createUser(user);
        return ResponseEntity.ok("Account Created");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody User user )
    {
        String loginResponse = this.userService.isUserLoginValid(user);

        if( ! loginResponse.equals("Valid") )
        {
            return ResponseEntity.badRequest()
                            .body( loginResponse );
        }
        return ResponseEntity.ok("Valid Account");
    }
}
