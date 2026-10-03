package com.abdellah.demo.service;

import com.abdellah.demo.entity.User;
import com.abdellah.demo.repository.UserRepository;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service 
public class UserAuthService 
{
    private final UserRepository userRepository;

    public UserAuthService( UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    public boolean isUserFormValid( User user )
    {
        if( user == null ) 
            return false;

        if( user.getNom() == null || user.getNom().isBlank() ) 
            return false;

        if( user.getEmail() == null || user.getEmail().isBlank() )
            return false;

        if(!EmailValidator.getInstance().isValid(user.getEmail()))
            return false;

        if(this.userRepository.existsByEmail(user.getEmail()))
            return false;

        return true;
    }

    public void createUser( User user )
    {
        this.userRepository.save(user);
    }
}
