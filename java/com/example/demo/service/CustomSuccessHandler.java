package com.example.demo.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        boolean isUser = authorities.stream().anyMatch(authority -> authority.getAuthority().equals("USER"));

        System.out.println("Authorities: " + authorities);

        if (isAdmin) {
            response.sendRedirect("/admin-page");
        } else if (isUser) {
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/error");
        }
    }
}
