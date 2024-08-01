package com.example.demo.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-email")
    public ResponseEntity<Map<String, String>> getUserEmail(Principal principal) {
        String email = principal.getName();
        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        return ResponseEntity.ok(response);
    }
}
