package com.abdellah.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abdellah.demo.entity.User;
import com.abdellah.demo.service.UserAuthService;

import jakarta.servlet.http.HttpSession;

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
    public ResponseEntity<String> login( 
            @RequestBody User user,
            HttpSession session )
    {
        String loginResponse = this.userService.isUserLoginValid(user);

        if( ! loginResponse.equals("Valid") )
        {
            return ResponseEntity.badRequest()
                            .body( loginResponse );
        }

        session.setAttribute( "userEmail", user.getEmail() );
        return ResponseEntity.ok("Valid Account");
    }
}
