package com.abdellah.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController 
{

    @GetMapping("/hello")
    public String hello() 
    {
        return "Bonjour depuis Spring Boot !";
    }

    @GetMapping("/user")
    public Map<String, Object> getUser() {
    
        return Map.of(
                "name", "Abdo",
                "age", 19,
                "city", "Le Havre"
        );
    }
}