package com.example.demo.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.demo.model.User;
import com.example.demo.web.dto.UserDto;

public interface UserService{
	User save( UserDto userDto);
	
	User findByEmail(String email);
	

}
