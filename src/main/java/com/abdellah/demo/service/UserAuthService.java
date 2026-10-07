package com.abdellah.demo.service;

import com.abdellah.demo.entity.User;
import com.abdellah.demo.repository.UserRepository;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service 
public class UserAuthService 
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAuthService( UserRepository userRepository, 
                            BCryptPasswordEncoder passwordEncoder )
    {
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 
     * @param user informations to verify after register
     * @return "Valid" if everything is verified else return the wrong field
     */
    public String isUserRegisterValid( User user )
    {
        if( user == null ) 
            return "User null";

        /* Name Verifications */
        if( user.getNom() == null || user.getNom().isBlank() ) 
            return "Enter Name";

        /* Email Verifications */
        if( user.getEmail() == null || user.getEmail().isBlank() )
            return "Enter Email";

        if(!EmailValidator.getInstance().isValid(user.getEmail()))
            return "Invalid email";

        if(this.userRepository.existsByEmail(user.getEmail()))
            return "Email alreasy exists";

        if(user.getPassword() == null || user.getPassword().length() < 8)
            return "Invalid Passoword";

        return "Valid";
    }

    /**
     * 
     * @param user informations to verify after login
     * 
     * @return  'Valid' if everything is verified; otherwise, return 'Invalid' without 
     * specifying whether the issue is with the email or the login for security reasons.
     */
    public String isUserLoginValid( User user )
    {
        if( user == null ) 
            return "Invalid Login"; 

        if( ! this.userRepository.existsByEmail( user.getEmail() ) )
            return "Invalid Login";


        /* Password Verifications */
        String storedPassword =  getUserPassword( user.getEmail() );
        if( ! passwordEncoder.matches( user.getPassword(), storedPassword ) )
            return "Invalid Login";

        return "Valid";
    }

    private String getUserPassword( String email )
    {
        User userToFind = this.userRepository.findByEmail(email);

        if(userToFind == null) return "User Not Found";
        return userToFind.getPassword();
    }
    

    public void createUser( User user )
    {
        user.setPassword(
            passwordEncoder.encode(user.getPassword())
        );

        this.userRepository.save(user);
    }

}
