package com.edikorce.TravelAgency.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationSystem {


    public static boolean isLogged(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }


    // ...
    // Any another methods, for example, logout
}