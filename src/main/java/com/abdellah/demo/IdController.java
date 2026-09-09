package com.abdellah.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class IdController
{
    @GetMapping("/testID/{id}")
    public String getId( @PathVariable long id )
    {
        return "Your ID is : " + id;
    }
}
