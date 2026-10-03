package com.abdellah.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
public class AuthController 
{
    @PostMapping("/auth")
    public String postMethodName(@RequestBody String entity) 
    {
        return entity;
    }
    
}
