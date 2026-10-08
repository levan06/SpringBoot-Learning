package com.abdellah.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller 
public class DashboardController 
{
    @GetMapping("/dashboard")
    public String getDashboardPage(HttpServletRequest request)
    {
        /* No session ==> login page */
        HttpSession session = request.getSession(false);
        if (session == null)
            return "redirect:/login.html";

        /* No userId ==> login page */
        Object id = session.getAttribute("userId");
        if (id == null) // If someone has a session but no authenticated user
            return "redirect:/login.html";

        /* Valid session ==> dashboard */
        return "dashboard";
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request,
            HttpServletResponse response)
    {
        /*----------------------*/
        /* Session Verification */
        /*----------------------*/
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            return ResponseEntity.status(401).body("Not logged in");
        }

        /*----------------------------------*/
        /* Session destruction and deletion */
        /*----------------------------------*/
        session.invalidate();

        // Creating a cookie with the same name
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0); //Delete immediately
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(request.isSecure());

        // Browser removes the cookie
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged Out");
    }
}
