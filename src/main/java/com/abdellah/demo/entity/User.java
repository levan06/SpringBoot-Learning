package com.abdellah.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User 
{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)   
    private Long id;
    
    private String nom;

    private String email;

    public User() {}
    public User( String nom, String email )
    {
        this.nom   = nom;
        this.email = email;
    }

    public String getNom()
    {
        return nom;
    }

    public String getEmail()
    {
        return email;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
}