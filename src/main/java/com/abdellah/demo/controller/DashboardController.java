package com.abdellah.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller 
public class DashboardController 
{
    @GetMapping("/dashboard")
    public String getDashboardPage( HttpSession session )
    {
        String email = (String) session.getAttribute("userEmail");

        if( email == null ) 
            return "redirect:/login.html";

        return "forward:/dashboard.html";
    }

    @PostMapping ("/logout")
    public ResponseEntity<String> logout( HttpSession session )
    {
        session.invalidate();

        return ResponseEntity.ok("Logged Out");
    }
}
