package com.abdellah.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/error")
    public ResponseEntity<String> error()
    {
        return ResponseEntity
                .status(404)
                .body("Vous n'avez pas le droit d'acceder a cette page");
    }
}