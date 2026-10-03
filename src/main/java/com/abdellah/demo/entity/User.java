package com.abdellah.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User 
{
    /*===============*/
    /*   Attributs   */
    /*===============*/
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)   
    private Long id;
    
    @NotBlank(message = "Name cannot be blank")
    private String nom;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;


    /*=============================*/
    /*  Construcutor and Methodes  */
    /*=============================*/
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