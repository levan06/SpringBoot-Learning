package com.abdellah.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class SearchController 
{
    @GetMapping( "/search" )
    public String getSearch( @RequestParam String name, @RequestParam(required = false) String age )
    {
        return "Your name is " + name + " and your age is " + age;
    }

    @GetMapping("/search2")
    public String getSecondSearch( @RequestParam(name = "country") String pays )
    {
        return "Your country is " + pays;
    }
}