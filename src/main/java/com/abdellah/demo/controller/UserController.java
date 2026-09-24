package com.abdellah.demo.controller;

import com.abdellah.demo.repository.UserRepository;
import com.abdellah.demo.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController
{
    private JpaRepository userRepo;

    public UserController( UserRepository userRepo )
    {
        this.userRepo = userRepo;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
    
        try 
        {
            userRepo.save(user);
            return ResponseEntity.ok("Votre compte a été créé avec succès");
        
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du compte");
        }
    }

    @GetMapping("/users")
    public List<User> getUsers()
    {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser( @PathVariable Long id )
    {
        return (User) userRepo.findById(id).orElse(null);
    }
}
