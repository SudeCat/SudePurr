package com.example.demo.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.model.User;

import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserDto;

@Controller
public class UserController {
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "registration";
	}
	
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly!");
		return "redirect:/registration?success";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("/admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "admin";
	}
	
	@GetMapping("/profile")
	public String viewProfile(Model model, Principal principal) {
	    String email = principal.getName();
	    User user = userService.findByEmail(email);
	    
	    if (user == null) {
	        return "redirect:/login";
	    }
	    
	    model.addAttribute("user", user);
	    System.out.println("Email: " + email);
	    System.out.println("User: " + user);

	    
	    return "profile"; // profile.html ile eşleşmeli
	}




}
