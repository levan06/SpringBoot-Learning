package com.abdellah.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class SearchController 
{
    @GetMapping( "/search" )
    public String getSearch( @RequestParam String name )
    {
        return "Your name is " + name;
    }
}